package com.example.moviesapp.domain.model.auth

data class ValidateData(
    val expires_at: String,
    val request_token: String,
    val success: Boolean
)