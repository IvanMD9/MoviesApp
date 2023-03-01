package com.example.moviesapp.presentation.detail_movie

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet.Constraint
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.moviesapp.databinding.FragmentDetailMovieBinding
import com.example.moviesapp.domain.model.DetailMovie
import com.example.moviesapp.util.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.List

@AndroidEntryPoint
class DetailMovieFragment : Fragment() {

    private var _binding: FragmentDetailMovieBinding? = null
    private val binding: FragmentDetailMovieBinding
        get() = _binding ?: throw RuntimeException("FragmentWelcomeBinding == null")

    private val viewModel: DetailMovieViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewState()
    }

    private fun observeViewState() {
        lifecycleScope.launch {
            viewModel.state.collect { result ->
                result.detail?.let { initDetailView(it) }
                if (result.loading) {
                    binding.progressBarDetail.visibility = View.VISIBLE
                } else {
                    binding.progressBarDetail.visibility = View.INVISIBLE
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initDetailView(detailMovie: DetailMovie) {
        binding.ivDetailImage.load(Constants.IMAGE_URL + detailMovie.poster_path)
        binding.tvDetailTitle.text = detailMovie.title
        binding.tvDetailOverview.text = detailMovie.overview
        binding.tvDateDetail.text = dateFormatter(detailMovie.release_date)
        binding.tvDetailRuntime.text = "${detailMovie.runtime} мин"
        binding.tvDetailGenre.text = listGenre(detailMovie.genres).toString()
    }

    private fun listGenre(genre : List<String>) {
        for (el in genre) {
            print("$el ")
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun dateFormatter(date: String): String {
        val initDate: Date? = SimpleDateFormat("yyyy-MM-dd").parse(date)
        val formatter = SimpleDateFormat("dd.MM.yyyy")
        return initDate?.let { formatter.format(it) }.toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}