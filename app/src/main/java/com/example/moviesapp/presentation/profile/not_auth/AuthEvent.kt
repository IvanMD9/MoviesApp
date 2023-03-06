package com.example.moviesapp.presentation.profile.not_auth

sealed class AuthEvent {
    data class ChangedLogin(val login: String) : AuthEvent()
    data class ChangedPassword(val password: String) : AuthEvent()
    object ClickEnter : AuthEvent()
}