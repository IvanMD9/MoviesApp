package com.example.moviesapp.presentation.detail_movie

import com.example.moviesapp.domain.model.DetailMovie

data class DetailState(
    val detail : DetailMovie? = null,
    val loading : Boolean = false,
    val error : String = ""
)
