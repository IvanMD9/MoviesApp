package com.example.moviesapp.data.mappers

import com.example.moviesapp.data.model.auth.AuthUserDto
import com.example.moviesapp.domain.model.AuthUser

fun AuthUserDto.toAuthUser() : AuthUser {
    return AuthUser(
        username = username
    )
}