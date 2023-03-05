package com.example.moviesapp.presentation.profile.not_auth

sealed class StateAuth {
    object Auth : StateAuth()
    object NotAuth : StateAuth()
    object Initial : StateAuth()
}
