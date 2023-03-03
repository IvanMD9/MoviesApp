package com.example.moviesapp.presentation.search

import androidx.paging.PagingData
import com.example.moviesapp.domain.model.SearchMovies

sealed class SearchState {
    data class Success(val charactersPaged: PagingData<SearchMovies>) : SearchState()
    data class Error(val message: String) : SearchState()
    object Loading : SearchState()
}