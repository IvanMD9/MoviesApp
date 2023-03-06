package com.example.moviesapp.presentation.detail_movie.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.moviesapp.domain.model.DetailMovie
import com.example.moviesapp.domain.model.ListActors
import com.example.moviesapp.domain.model.ListPopularMovies

class DiffUtilActors : DiffUtil.ItemCallback<ListActors>() {
    override fun areItemsTheSame(oldItem: ListActors, newItem: ListActors): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: ListActors,
        newItem: ListActors
    ): Boolean {
        return oldItem == newItem
    }
}