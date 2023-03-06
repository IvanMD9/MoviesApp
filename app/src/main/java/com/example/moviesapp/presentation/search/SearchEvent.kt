package com.example.moviesapp.presentation.search

sealed class SearchEvent {
    data class SearchMovies(val query: String) : SearchEvent()
}