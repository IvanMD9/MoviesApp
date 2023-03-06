package com.example.moviesapp.domain.repository

import androidx.paging.PagingData
import com.example.moviesapp.domain.model.ListPopularMovies
import kotlinx.coroutines.flow.Flow

interface RepositoryMovies {
    fun getListPopularMovies() : Flow<PagingData<ListPopularMovies>>
}