package com.example.moviesapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.moviesapp.data.remote.MoviesApi
import com.example.moviesapp.domain.model.ListPopularMovies
import com.example.moviesapp.domain.repository.RepositoryMovies
import kotlinx.coroutines.flow.Flow

class RepositoryMoviesImpl(
    private val api: MoviesApi
) : RepositoryMovies {

    override fun getListPopularMovies(): Flow<PagingData<ListPopularMovies>> {
        val data = MoviesPagingSource(api)
        return Pager(
            config = PagingConfig(pageSize = 1),
            pagingSourceFactory = { data }
        ).flow
    }
}