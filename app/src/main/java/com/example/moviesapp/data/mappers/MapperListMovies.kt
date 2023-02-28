package com.example.moviesapp.data.mappers

import com.example.moviesapp.data.model.list_movies.ResultMovies
import com.example.moviesapp.domain.model.ListPopularMovies

fun ResultMovies.toDomainListMovies() : ListPopularMovies {
    return ListPopularMovies(
        id = id,
        poster_path = backdrop_path,
        title = title
    )
}