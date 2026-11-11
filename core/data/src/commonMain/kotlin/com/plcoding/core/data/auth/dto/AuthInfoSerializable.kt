package com.plcoding.core.data.auth.dto

import kotlinx.serialization.Serializable

@Serializable
data class AuthInfoSerializable(
    val accessToken: String,
    val refreshToken: String,
    val user: UserSerializable
)
