package com.example.moviesapp.presentation.profile.auth

import com.example.moviesapp.domain.model.AuthUser

data class AuthUserState(
    val authData : AuthUser? = null,
    val loading : Boolean = false,
    val error : String = ""
)
