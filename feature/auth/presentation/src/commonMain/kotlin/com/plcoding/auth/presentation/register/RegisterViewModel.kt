package com.plcoding.auth.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import chirp.feature.auth.presentation.generated.resources.Res
import chirp.feature.auth.presentation.generated.resources.error_invalid_email
import chirp.feature.auth.presentation.generated.resources.error_invalid_password
import chirp.feature.auth.presentation.generated.resources.error_invalid_username
import com.plcoding.auth.domain.EmailValidator
import com.plcoding.core.domain.validation.PasswordValidator
import com.plcoding.core.presentation.util.UiText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class RegisterViewModel: ViewModel() {
    private var hasLoadedIntitalData = false

    private val _state = MutableStateFlow(RegisterState())
    val state = _state
        .onStart {
            if (!hasLoadedIntitalData) {

                hasLoadedIntitalData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = RegisterState()
        )

    fun onAction(action: RegisterAction) {
        when (action) {
            RegisterAction.OnLoginClick -> validateFormInputs()
            RegisterAction.OnInputTextFocusGained -> clearAllTextFieldErrors()
            else -> Unit
        }
    }

    private fun clearAllTextFieldErrors() {
        _state.update {
            it.copy(
                emailError = null,
                usernameError = null,
                passwordError = null,
                registrationError = null
            )
        }
    }

    private fun validateFormInputs(): Boolean {
        clearAllTextFieldErrors()

        val currentState = state.value
        val email = currentState.emailTextState.text.toString()
        val username = currentState.usernameTextState.text.toString()
        val password = currentState.passwordTextState.text.toString()

        val isEmailValid = EmailValidator.validate(email)
        val isPasswordValidationState = PasswordValidator.validate(password)
        val isUsernameValid = username.length in 3..20

        val emailError = takeIf { !isEmailValid }?.let {
            UiText.Resource(Res.string.error_invalid_email)
        }

        val usernameError = takeIf { !isUsernameValid }?.let {
            UiText.Resource(Res.string.error_invalid_username)
        }

        val passwordError = takeIf { !isPasswordValidationState.isValidPassword }?.let {
            UiText.Resource(Res.string.error_invalid_password)
        }

        _state.update {
            it.copy(
                emailError = emailError,
                usernameError = usernameError,
                passwordError = passwordError,
                canRegister = isEmailValid && isPasswordValidationState.isValidPassword && isUsernameValid
            )
        }

        return isEmailValid && isPasswordValidationState.isValidPassword && isUsernameValid
    }
}