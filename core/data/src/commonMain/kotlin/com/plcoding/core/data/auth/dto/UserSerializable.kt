package com.plcoding.core.data.auth.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserSerializable(
    val id: String,
    val email: String,
    val username: String,
    val hasVerifiedEmail: Boolean,
    val profilePictureUrl: String? = null
)
