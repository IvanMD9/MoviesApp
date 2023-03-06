package com.example.moviesapp.presentation.detail_movie.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.moviesapp.domain.model.DetailMovie
import com.example.moviesapp.domain.model.GenresMovie
import com.example.moviesapp.domain.model.ListActors
import com.example.moviesapp.domain.model.ListPopularMovies

class DiffUtilGenres : DiffUtil.ItemCallback<GenresMovie>() {
    override fun areItemsTheSame(oldItem: GenresMovie, newItem: GenresMovie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: GenresMovie,
        newItem: GenresMovie
    ): Boolean {
        return oldItem == newItem
    }
}