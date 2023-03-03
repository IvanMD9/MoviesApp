package com.example.moviesapp.domain.model.auth

data class NewToken(
    val expires_at: String,
    val request_token: String,
    val success: Boolean
)