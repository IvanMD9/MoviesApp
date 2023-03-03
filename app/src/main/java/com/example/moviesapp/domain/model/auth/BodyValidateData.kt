package com.example.moviesapp.domain.model.auth

data class BodyValidateData(
    val password: String,
    val request_token: String,
    val username: String
)