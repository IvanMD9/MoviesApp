package com.example.moviesapp.data.repository.auth

import com.example.moviesapp.data.mappers.toAuthUser
import com.example.moviesapp.data.remote.MoviesApi
import com.example.moviesapp.domain.model.AuthUser
import com.example.moviesapp.domain.model.auth.*
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

    override suspend fun postNewSessionId(token: BodySessionId): SessionId {
        return api.postNewSessionId(requestToken = token)
    }

    override suspend fun getDataAccount(sessionId: String): AuthUser {
        return api.getDataAccount(sessionId = sessionId).toAuthUser()
    }
}