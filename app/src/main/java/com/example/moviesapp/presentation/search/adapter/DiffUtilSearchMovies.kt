package com.example.moviesapp.presentation.search.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.moviesapp.domain.model.ListPopularMovies
import com.example.moviesapp.domain.model.SearchMovies

class DiffUtilSearchMovies : DiffUtil.ItemCallback<SearchMovies>() {
    override fun areItemsTheSame(oldItem: SearchMovies, newItem: SearchMovies): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: SearchMovies,
        newItem: SearchMovies
    ): Boolean {
        return oldItem == newItem
    }
}