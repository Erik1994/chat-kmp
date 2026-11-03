package com.plcoding.core.data.auth

import com.plcoding.core.data.auth.dto.requests.EmailRequest
import com.plcoding.core.data.auth.dto.requests.RegisterRequest
import com.plcoding.core.data.network.post
import com.plcoding.core.domain.auth.AuthService
import com.plcoding.core.domain.util.DataError
import com.plcoding.core.domain.util.EmptyResult
import io.ktor.client.HttpClient

class KtorAuthService(
    private val httpClient: HttpClient
): AuthService {
    override suspend fun register(
        email: String,
        username: String,
        password: String
    ): EmptyResult<DataError.Remote> = httpClient.post(
        route = "/auth/register",
        body = RegisterRequest(
            email = email,
            username = username,
            password = password
        )
    )

    override suspend fun resendVerificationEmail(email: String): EmptyResult<DataError.Remote> = httpClient.post(
        route = "/auth/resend-verification",
        body = EmailRequest(email = email)
    )
}