package com.example.moviesapp.domain.model

data class DetailMovie(
    val genres: List<GenresMovie>,
    val credits : List<ListActors>,
    val id: Int,
    val poster_path: String,
    val release_date: String,
    val overview: String,
    val runtime: Int,
    val title: String,
)
data class ListActors(
    val id: Int,
    val name: String,
    val profile_path: String?
)

data class GenresMovie(
    val id: Int,
    val name: String
)
