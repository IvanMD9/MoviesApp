package com.example.moviesapp.presentation.list_movies

import androidx.paging.PagingData
import com.example.moviesapp.domain.model.ListPopularMovies

data class MoviesState(
    val list : PagingData<ListPopularMovies>? = null,
    val isLoading : Boolean = false
)
//sealed class MoviesState {
//    object Initial : MoviesState()
//    object Loading : MoviesState()
//    data class ListMovies(val list: PagingData<ListPopularMovies>) : MoviesState()
//}