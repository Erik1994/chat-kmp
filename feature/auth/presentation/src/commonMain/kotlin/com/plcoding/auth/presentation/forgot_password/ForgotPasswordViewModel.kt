package com.plcoding.auth.presentation.forgot_password

import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.auth.domain.EmailValidator
import com.plcoding.core.domain.auth.AuthService
import com.plcoding.core.domain.util.onError
import com.plcoding.core.domain.util.onSuccess
import com.plcoding.core.presentation.util.toUiText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ForgotPasswordViewModel(
    private val authService: AuthService
) : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(ForgotPasswordState())

    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                observeValidationState()
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = ForgotPasswordState()
        )

    private val isEmailValidFlow = snapshotFlow { state.value.emailTextFieldState.text.toString() }
        .map { email ->
            EmailValidator.validate(email)
        }.distinctUntilChanged()

    fun onAction(action: ForgotPasswordAction) {
        when (action) {
            is ForgotPasswordAction.OnSubmitClick -> submitForgotPassword()
        }
    }

    private fun submitForgotPassword() {
        if (state.value.isLoading || !state.value.canSubmit) return

        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true,
                    isEmailSentSuccessfully = false,
                    errorText = null
                )
            }
            val email = state.value.emailTextFieldState.text.toString()
            authService
                .forgotPassword(email = email)
                .onSuccess {
                    _state.update {
                        it.copy(
                            isEmailSentSuccessfully = true,
                            isLoading = false
                        )
                    }
                }.onError { error ->
                    _state.update {
                        it.copy(
                            errorText = error.toUiText(),
                            isLoading = false
                        )
                    }
                }
        }
    }

    private fun observeValidationState() {
        isEmailValidFlow
            .onEach { isEmailValid ->
                _state.update {
                    it.copy(
                        canSubmit = isEmailValid
                    )
                }
        }.launchIn(viewModelScope)
    }

}