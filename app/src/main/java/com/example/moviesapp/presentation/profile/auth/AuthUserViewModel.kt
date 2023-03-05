package com.example.moviesapp.presentation.profile.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.storage.SessionManager
import com.example.moviesapp.domain.use_case.AuthUserUseCase
import com.example.moviesapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class AuthUserViewModel @Inject constructor(
    private val authUserUseCase: AuthUserUseCase,
    sessionManager: SessionManager
) : ViewModel() {

    private val _state = MutableStateFlow(AuthUserState())
    val state: StateFlow<AuthUserState> = _state.asStateFlow()

    init {
        getDataAccount(sessionManager.getSessionId().toString())
    }

    private fun getDataAccount(sessionId: String) {
        authUserUseCase.invoke(sessionId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = AuthUserState(loading = true)
                }
                is Resource.Success -> {
                    _state.value = AuthUserState(authData = result.data)
                }
                is Resource.Error -> {
                    _state.value = AuthUserState(error = result.message ?: "An unexpected error")
                }
            }
        }.launchIn(viewModelScope)
    }
}