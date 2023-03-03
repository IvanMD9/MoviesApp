package com.example.moviesapp.domain.repository

import com.example.moviesapp.domain.model.auth.BodyValidateData
import com.example.moviesapp.domain.model.auth.NewToken
import com.example.moviesapp.domain.model.auth.SessionId
import com.example.moviesapp.domain.model.auth.ValidateData

interface RepositoryAuthorization {

    suspend fun getNewToken() : NewToken
    suspend fun postValidateData(bodyValidateData: BodyValidateData) : ValidateData
    suspend fun postNewSessionId(token : String) : SessionId
}