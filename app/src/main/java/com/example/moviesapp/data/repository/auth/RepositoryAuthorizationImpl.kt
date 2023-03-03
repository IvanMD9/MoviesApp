package com.example.moviesapp.data.repository.auth

import com.example.moviesapp.data.remote.MoviesApi
import com.example.moviesapp.domain.model.auth.BodyValidateData
import com.example.moviesapp.domain.model.auth.NewToken
import com.example.moviesapp.domain.model.auth.SessionId
import com.example.moviesapp.domain.model.auth.ValidateData
import com.example.moviesapp.domain.repository.RepositoryAuthorization
import javax.inject.Inject

class RepositoryAuthorizationImpl @Inject constructor(
    private val api: MoviesApi
) : RepositoryAuthorization {

    override suspend fun getNewToken(): NewToken {
        return api.getNewToken()
    }

    override suspend fun postValidateData(bodyValidateData: BodyValidateData): ValidateData {
        return api.postValidateData(body = bodyValidateData)
    }

    override suspend fun postNewSessionId(token: String): SessionId {
        return api.postNewSessionId(token = token)
    }
}