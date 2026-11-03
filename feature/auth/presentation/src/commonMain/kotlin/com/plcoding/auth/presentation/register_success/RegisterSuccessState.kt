package com.plcoding.auth.presentation.register_success

data class RegisterSuccessState(
    val registerEmail: String = "",
    val isResendingVerificationEmail: Boolean = false
)