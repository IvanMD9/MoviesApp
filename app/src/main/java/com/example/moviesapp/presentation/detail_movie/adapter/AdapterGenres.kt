package com.example.moviesapp.presentation.detail_movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.R
import com.example.moviesapp.domain.model.GenresMovie

class AdapterGenres :
    ListAdapter<GenresMovie, AdapterGenres.GenresViewHolder>(DiffUtilGenres()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_genre, parent, false)
        return GenresViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenresViewHolder, position: Int) {
        val itemGenre = getItem(position)
        if (position == itemCount - 1) {
            holder.tvGenre.text = itemGenre.name
        } else {
            holder.tvGenre.text = "${itemGenre.name}, "
        }
    }

    class GenresViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvGenre: TextView = view.findViewById(R.id.tv_genre)
    }
}