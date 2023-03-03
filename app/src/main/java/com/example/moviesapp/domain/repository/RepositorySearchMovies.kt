package com.example.moviesapp.domain.repository

import androidx.paging.PagingData
import com.example.moviesapp.domain.model.SearchMovies
import kotlinx.coroutines.flow.Flow

interface RepositorySearchMovies {
    fun getSearchMovies(query : String): Flow<PagingData<SearchMovies>>
}