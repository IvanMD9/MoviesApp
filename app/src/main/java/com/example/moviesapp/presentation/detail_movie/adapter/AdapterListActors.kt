package com.example.moviesapp.presentation.detail_movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.moviesapp.R
import com.example.moviesapp.domain.model.ListActors
import com.example.moviesapp.util.Constants

class AdapterListActors :
    ListAdapter<ListActors, AdapterListActors.ListActorsViewHolder>(DiffUtilActors()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListActorsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_actor, parent, false)
        return ListActorsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListActorsViewHolder, position: Int) {
        val itemActor = getItem(position)
        holder.ivActor.load(Constants.IMAGE_URL_185 + itemActor.profile_path)
        holder.tvActor.text = itemActor.name
    }

    class ListActorsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivActor: ImageView = view.findViewById(R.id.iv_item_actor)
        val tvActor: TextView = view.findViewById(R.id.tv_item_actor)
    }
}