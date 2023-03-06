package com.example.moviesapp.presentation.list_movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesapp.R
import com.example.moviesapp.databinding.FragmentMoviesBinding
import com.example.moviesapp.presentation.list_movies.adapter.LoadingStateAdapter
import com.example.moviesapp.presentation.list_movies.adapter.MoviesAdapter
import com.example.moviesapp.util.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding: FragmentMoviesBinding
        get() = _binding ?: throw RuntimeException("FragmentMoviesBinding == null")

    private val viewModel: ListMoviesViewModel by viewModels()
    private lateinit var adapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRcView()
        observeViewState()
        setupOnClickDetailWindow()
    }

    private fun observeViewState() {
        lifecycleScope.launch {
            viewModel.state.collect { result ->
                when (result) {
                    is ListUIState.Loading -> {
                        binding.shimmerLayout.startShimmer()
                    }
                    is ListUIState.ListMovies -> {
                        binding.shimmerLayout.stopShimmer()
                        binding.shimmerLayout.isVisible = false

                        result.list?.let { adapter.submitData(it) }
                    }
                    is ListUIState.Initial -> {}
                }
            }
        }
    }

    private fun setupRcView() {
        adapter = MoviesAdapter()
        binding.rcViewMovies.layoutManager = GridLayoutManager(context, 3)
        binding.rcViewMovies.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter()
        )
    }

    private fun setupOnClickDetailWindow() {
        adapter.onClickListener = {
            val bundle = Bundle()
            bundle.putString(Constants.KEY_ID_DETAIL, it.id.toString())
            findNavController().navigate(
                R.id.action_moviesFragment_to_detailMovieFragment, bundle
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}