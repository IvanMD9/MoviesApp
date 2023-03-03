package com.example.moviesapp.data.model.search

data class SearchMoviesDto(
    val page: Int,
    val results: List<ResultSearch>,
    val total_pages: Int,
    val total_results: Int
)