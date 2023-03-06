package com.example.moviesapp.data.mappers

import com.example.moviesapp.data.model.detail.DetailMovieDto
import com.example.moviesapp.domain.model.DetailMovie
import com.example.moviesapp.domain.model.GenresMovie
import com.example.moviesapp.domain.model.ListActors

fun DetailMovieDto.toDetailMovie(): DetailMovie {
    return DetailMovie(
        genres = genres.map {
            GenresMovie(
                id = it.id,
                name = it.name
            )
        },
        id = id,
        poster_path = poster_path,
        release_date = release_date,
        overview = overview,
        runtime = runtime,
        title = title,
        credits = credits.cast.map {
            ListActors(
                id = it.id,
                name = it.name,
                profile_path = it.profile_path
            )
        }
    )
}