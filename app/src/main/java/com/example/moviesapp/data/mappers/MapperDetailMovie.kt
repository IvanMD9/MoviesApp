package com.example.moviesapp.data.mappers

import com.example.moviesapp.data.model.detail.MovieDetailDto
import com.example.moviesapp.domain.model.DetailMovie

fun MovieDetailDto.toDetailMovie() : DetailMovie {
    return DetailMovie(
        genres = genres.map { it.name },
        id = id,
        poster_path = poster_path,
        release_date = release_date,
        overview = overview,
        runtime = runtime,
        title = title
    )
}