package com.plcoding.core.data.network

import com.plcoding.core.domain.util.DataError
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import com.plcoding.core.domain.util.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.request.url

expect suspend fun <T> platformSafeCall(
    execute: suspend () -> HttpResponse,
    handleResponse: suspend (HttpResponse) -> Result<T, DataError.Remote>
): Result<T, DataError.Remote>

suspend inline fun <reified REQUEST, reified RESPONSE> HttpClient.post(
    route: String,
    queryParams: Map<String, Any> = mapOf(),
    body: REQUEST,
    crossinline builder: HttpRequestBuilder.() -> Unit = {}
): Result<RESPONSE, DataError.Remote> {
    return safeCall {
        post {
            url(constructRoute(route))
            queryParams.forEach { (key, value) ->
                parameter(key = key, value = value)
            }
            setBody(body)
            builder()
        }
    }
}

suspend inline fun <reified REQUEST, reified RESPONSE> HttpClient.put(
    route: String,
    queryParams: Map<String, Any> = mapOf(),
    body: REQUEST,
    crossinline builder: HttpRequestBuilder.() -> Unit = {}
): Result<RESPONSE, DataError.Remote> {
    return safeCall {
        put {
            url(constructRoute(route))
            queryParams.forEach { (key, value) ->
                parameter(key = key, value = value)
            }
            setBody(body)
            builder()
        }
    }
}

suspend inline fun <reified RESPONSE> HttpClient.get(
    route: String,
    queryParams: Map<String, Any> = mapOf(),
    crossinline builder: HttpRequestBuilder.() -> Unit = {}
): Result<RESPONSE, DataError.Remote> {
    return safeCall {
        get {
            url(constructRoute(route))
            queryParams.forEach { (key, value) ->
                parameter(key = key, value = value)
            }
            builder()
        }
    }
}

suspend inline fun <reified RESPONSE> HttpClient.delete(
    route: String,
    queryParams: Map<String, Any> = mapOf(),
    crossinline builder: HttpRequestBuilder.() -> Unit = {}
): Result<RESPONSE, DataError.Remote> {
    return safeCall {
        delete {
            url(constructRoute(route))
            queryParams.forEach { (key, value) ->
                parameter(key = key, value = value)
            }
            builder()
        }
    }
}

suspend inline fun <reified T> safeCall(
    noinline execute: suspend () -> HttpResponse
): Result<T, DataError.Remote> = platformSafeCall(
    execute = execute
) { response ->
    responseToResult(response)
}

suspend inline fun <reified T> responseToResult(response: HttpResponse): Result<T, DataError.Remote> = when(response.status.value) {
    in 200..299 -> {
        try {
            Result.Success(response.body<T>())
        } catch(e: NoTransformationFoundException) {
            Result.Failure(DataError.Remote.SERIALIZATION)
        }
    }
    400 -> Result.Failure(DataError.Remote.BAD_REQUEST)
    401 -> Result.Failure(DataError.Remote.UNAUTHORIZED)
    403 -> Result.Failure(DataError.Remote.FORBIDDEN)
    404 -> Result.Failure(DataError.Remote.NOT_FOUND)
    408 -> Result.Failure(DataError.Remote.REQUEST_TIMEOUT)
    413 -> Result.Failure(DataError.Remote.PAYLOAD_TOO_LARGE)
    429 -> Result.Failure(DataError.Remote.TOO_MANY_REQUESTS)
    500 -> Result.Failure(DataError.Remote.SERVER_ERROR)
    503 -> Result.Failure(DataError.Remote.SERVICE_UNAVAILABLE)
    else -> Result.Failure(DataError.Remote.UNKNOWN)
}


fun constructRoute(route: String): String = when {
    route.contains(UrlConstants.BASE_URL_HTTP) -> route
    route.startsWith("/") -> "${UrlConstants.BASE_URL_HTTP}$route"
    else -> "${UrlConstants.BASE_URL_HTTP}/$route"
}