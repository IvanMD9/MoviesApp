package com.example.moviesapp.presentation.list_movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesapp.databinding.FragmentMoviesBinding
import com.example.moviesapp.presentation.list_movies.adapter.LoadingStateAdapter
import com.example.moviesapp.presentation.list_movies.adapter.MoviesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding: FragmentMoviesBinding
        get() = _binding ?: throw RuntimeException("FragmentWelcomeBinding == null")

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
    }
    private fun observeViewState() {
        lifecycleScope.launch {
            viewModel.state.collect { result ->
                when (result) {
                    is MoviesState.Loading -> {
                        binding.progressMoviesLoading.visibility = View.VISIBLE
                    }
                    is MoviesState.ListMovies -> {
                        adapter.submitData(result.list)
                    }
                    is MoviesState.Initial -> {}
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
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}