package com.example.moviesapp.presentation.list_movies.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.moviesapp.domain.model.ListPopularMovies

class DiffUtilMovies : DiffUtil.ItemCallback<ListPopularMovies>() {
    override fun areItemsTheSame(oldItem: ListPopularMovies, newItem: ListPopularMovies): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: ListPopularMovies,
        newItem: ListPopularMovies
    ): Boolean {
        return oldItem == newItem
    }
}