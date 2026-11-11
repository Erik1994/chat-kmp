package com.plcoding.core.data.auth.dto.requests

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)
