package com.example.moviesapp.domain.model

data class DetailMovie(
    val genres: List<String>,
    val id: Int,
    val poster_path: String,
    val release_date: String,
    val overview: String,
    val runtime: Int,
    val title: String,
)
