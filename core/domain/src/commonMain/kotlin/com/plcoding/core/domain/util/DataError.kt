package com.plcoding.core.domain.util

sealed interface DataError: Error {
    enum class Remote: DataError {
        BAD_REQUEST,
        UNAUTHORIZED,
        FORBIDDEN,
        NOT_FOUND,
        SERVER_ERROR,
        SERVICE_UNAVAILABLE,
        CONFLICT,
        TOO_MANY_REQUESTS,
        NO_INTERNET_CONNECTION,
        PAYLOAD_TOO_LARGE,
        REQUEST_TIMEOUT,
        SERIALIZATION,
        UNKNOWN
    }

    enum class Local: DataError {
        DISK_FULL,
        NOT_FOUND,
        UNKNOWN
    }
}