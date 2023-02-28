package com.example.moviesapp.data.model.list_movies

data class ListMovies(
    val page: Int,
    val results: List<ResultMovies>,
    val total_pages: Int,
    val total_results: Int
)