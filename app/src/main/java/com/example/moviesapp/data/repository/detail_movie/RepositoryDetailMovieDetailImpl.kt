package com.example.moviesapp.data.repository.detail_movie

import com.example.moviesapp.data.mappers.toDetailMovie
import com.example.moviesapp.data.remote.MoviesApi
import com.example.moviesapp.domain.model.DetailMovie
import com.example.moviesapp.domain.repository.RepositoryDetailMovie
import javax.inject.Inject

class RepositoryDetailMovieDetailImpl @Inject constructor(
    private val api: MoviesApi
) : RepositoryDetailMovie {

    override suspend fun getDetailMovies(id : String): DetailMovie {
        return api.getDetailMovies(id = id).toDetailMovie()
    }
}