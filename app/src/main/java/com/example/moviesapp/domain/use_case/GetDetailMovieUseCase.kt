package com.example.moviesapp.domain.use_case

import com.example.moviesapp.domain.model.DetailMovie
import com.example.moviesapp.domain.repository.RepositoryDetailMovie
import com.example.moviesapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetDetailMovieUseCase @Inject constructor(
    private val repositoryDetailMovie: RepositoryDetailMovie
) {
    operator fun invoke(id : String) : Flow<Resource<DetailMovie>> = flow {
        try {
            emit(Resource.Loading())
            val detailMovie = repositoryDetailMovie.getDetailMovies(id)
            emit(Resource.Success(detailMovie))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error"))
        } catch (e: IOException) {
            emit(Resource.Error("Check you connection Internet"))
        }
    }
}