package com.example.moviesapp.presentation.list_movies

import androidx.paging.PagingData
import com.example.moviesapp.domain.model.ListPopularMovies

sealed class MoviesState {
    object Initial : MoviesState()
    object Loading : MoviesState()
    data class ListMovies(val list : PagingData<ListPopularMovies>) : MoviesState()
}
