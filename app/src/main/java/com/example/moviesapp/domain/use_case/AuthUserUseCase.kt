package com.example.moviesapp.domain.use_case

import com.example.moviesapp.domain.model.AuthUser
import com.example.moviesapp.domain.repository.RepositoryAuthorization
import com.example.moviesapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AuthUserUseCase @Inject constructor(
    private val repositoryAuthorization: RepositoryAuthorization
) {

    operator fun invoke(sessionId : String) : Flow<Resource<AuthUser>> = flow {
        try {
            emit(Resource.Loading())
            val getAccount = repositoryAuthorization.getDataAccount(sessionId)
            emit(Resource.Success(getAccount))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error"))
        } catch (e: IOException) {
            emit(Resource.Error("Check you connection Internet"))
        }
    }
}