package com.example.moviesapp.data.mappers

import com.example.moviesapp.data.model.list_movies.ListMovies
import com.example.moviesapp.data.model.list_movies.ResultMovies
import com.example.moviesapp.domain.model.ListPopularMovies

fun ResultMovies.toDomainListMovies() : ListPopularMovies {
    return ListPopularMovies(
        id = id,
        poster_path = poster_path,
        title = title
    )
}

fun ListMovies.toListMovies() : ListPopularMovies {
    return ListPopularMovies(
        id = results.map { it.id }.toString().toInt(),
        poster_path = results.map { it.poster_path }.toString(),
        title = results.map { it.title }.toString()
    )
}