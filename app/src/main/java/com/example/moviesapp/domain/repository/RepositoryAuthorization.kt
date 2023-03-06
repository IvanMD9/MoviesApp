package com.example.moviesapp.domain.repository

import com.example.moviesapp.domain.model.AuthUser
import com.example.moviesapp.domain.model.auth.*

interface RepositoryAuthorization {

    suspend fun getNewToken() : NewToken
    suspend fun postValidateData(bodyValidateData: BodyValidateData) : ValidateData
    suspend fun postNewSessionId(token : BodySessionId) : SessionId
    suspend fun getDataAccount(sessionId : String) : AuthUser
}