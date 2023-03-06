package com.example.moviesapp.presentation.list_movies

import androidx.paging.PagingData
import com.example.moviesapp.domain.model.ListPopularMovies

sealed class ListUIState {
    data class ListMovies(val list: PagingData<ListPopularMovies>? = null) : ListUIState()
    object Loading : ListUIState()
    object Initial : ListUIState()
}
