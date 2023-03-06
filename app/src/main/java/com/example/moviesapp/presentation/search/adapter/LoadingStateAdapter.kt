package com.example.moviesapp.presentation.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.R
import com.example.moviesapp.databinding.LoadingStateItemBinding

class LoadingStateAdapter :
    LoadStateAdapter<LoadingStateAdapter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, loadState: LoadState
    ) = LoadStateViewHolder(parent)

    override fun onBindViewHolder(
        holder: LoadStateViewHolder, loadState: LoadState
    ) = holder.bind(loadState)

    inner class LoadStateViewHolder(
        parent: ViewGroup
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.loading_state_item, parent, false)
    ) {

        private val binding = LoadingStateItemBinding.bind(itemView)
        fun bind(loadState: LoadState) {
            binding.progressBar.isVisible = loadState is LoadState.Loading
        }
    }
}