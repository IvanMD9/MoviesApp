package com.example.moviesapp.presentation.list_movies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import com.example.moviesapp.R
import com.example.moviesapp.domain.model.ListPopularMovies

class MoviesAdapter :
    PagingDataAdapter<ListPopularMovies, MoviesAdapter.MoviesViewHolder>(DiffUtilMovies()) {

    var onClickListener: ((ListPopularMovies) -> Unit)? = null

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val listItem = getItem(position)
        val imageLoader = ImageLoader.Builder(holder.itemView.context)
            .components { add(SvgDecoder.Factory()) }
            .build()
        holder.ivItemMovie.load(listItem?.poster_path, imageLoader)
        holder.tvItemMovie.text = listItem?.title
        holder.itemView.setOnClickListener {
            listItem?.let { id ->
                onClickListener?.invoke(id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MoviesViewHolder(view)
    }

    class MoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivItemMovie: ImageView = view.findViewById(R.id.iv_item_movie)
        val tvItemMovie: TextView = view.findViewById(R.id.tv_item_movie)
    }
}