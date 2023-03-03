package com.example.moviesapp.data.mappers

import com.example.moviesapp.data.model.search.ResultSearch
import com.example.moviesapp.domain.model.SearchMovies

fun ResultSearch.toDomainSearch(): SearchMovies {
    return SearchMovies(
        id = id,
        poster_path = poster_path,
        title = title
    )
}