package com.example.moviesapp.presentation.profile.not_auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.storage.SessionManager
import com.example.moviesapp.domain.model.auth.BodySessionId
import com.example.moviesapp.domain.model.auth.BodyValidateData
import com.example.moviesapp.domain.repository.RepositoryAuthorization
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repositoryAuthorization: RepositoryAuthorization,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _stateAuth = MutableStateFlow<StateAuth>(StateAuth.Initial)
    val stateAuth: StateFlow<StateAuth> = _stateAuth.asStateFlow()

    var stateData by mutableStateOf(AuthState())

    init {
        getSessionId()
    }

    private fun getSessionId() {
        val sessionId = sessionManager.getSessionId()
        if (sessionId == null) {
            _stateAuth.value = StateAuth.NotAuth
        } else {
            _stateAuth.value = StateAuth.Auth
        }
    }

    fun onAuthEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.ChangedLogin -> {
                stateData = stateData.copy(signUpLogin = event.login)
            }
            is AuthEvent.ChangedPassword -> {
                stateData = stateData.copy(signUpPassword = event.password)
            }
            is AuthEvent.ClickEnter -> {
                authInSystem()
            }
        }
    }

    private fun authInSystem(
        login: String = stateData.signUpLogin,
        password: String = stateData.signUpPassword
    ) {
        viewModelScope.launch {
            val newToken = repositoryAuthorization.getNewToken().request_token
            sessionManager.saveNewToken(newToken = newToken)

            val validaData = repositoryAuthorization.postValidateData(
                bodyValidateData = BodyValidateData(
                    password = password,
                    request_token = sessionManager.getNewToken().toString(),
                    username = login
                )
            )

            val sessionId = repositoryAuthorization.postNewSessionId(
                token = BodySessionId(
                    request_token = validaData.request_token
                )
            )
            sessionManager.saveSessionId(sessionId.session_id)
        }
    }
}