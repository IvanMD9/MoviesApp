package com.example.moviesapp.data.repository.search

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.moviesapp.data.remote.MoviesApi
import com.example.moviesapp.domain.model.SearchMovies
import com.example.moviesapp.domain.repository.RepositorySearchMovies
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositorySearchMoviesImpl @Inject constructor(
    private val api: MoviesApi
) : RepositorySearchMovies {

    override fun getSearchMovies(query: String): Flow<PagingData<SearchMovies>> {
        val data = SearchMoviesPagingSource(api, query)
        return Pager(
            config = PagingConfig(pageSize = 1),
            pagingSourceFactory = { data }
        ).flow
    }
}