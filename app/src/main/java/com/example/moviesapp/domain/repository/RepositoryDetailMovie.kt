package com.example.moviesapp.domain.repository

import com.example.moviesapp.domain.model.DetailMovie

interface RepositoryDetailMovie {
    suspend fun getDetailMovies(id : String) : DetailMovie
}