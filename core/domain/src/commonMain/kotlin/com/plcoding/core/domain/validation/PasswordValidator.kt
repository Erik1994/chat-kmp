package com.plcoding.core.domain.validation

object PasswordValidator {
    private const val MIN_PASSWORD_LENGTH = 9

    fun validate(password: String): PasswordValidationState = PasswordValidationState(
        hasMinLength = password.length >= MIN_PASSWORD_LENGTH,
        hasUppercase = password.any(Char::isUpperCase),
        hasDigit = password.any(Char::isDigit)
    )
}