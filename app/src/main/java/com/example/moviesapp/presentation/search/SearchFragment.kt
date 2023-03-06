package com.example.moviesapp.presentation.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesapp.R
import com.example.moviesapp.databinding.FragmentSearchBinding
import com.example.moviesapp.presentation.list_movies.adapter.LoadingStateAdapter
import com.example.moviesapp.presentation.search.adapter.SearchMoviesAdapter
import com.example.moviesapp.util.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding
        get() = _binding ?: throw RuntimeException("FragmentWelcomeBinding == null")

    private val viewModel: SearchMoviesViewModel by viewModels()
    private lateinit var adapter: SearchMoviesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRcView()
        observeViewState()
        collectMoviesSearch()
        setupOnClickDetailWindow()
    }
    private fun observeViewState() {
        binding.searchView.setQuery(viewModel.searchQuery.value, true)
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                performSearchEvent(query)
                return false
            }
        })
    }
    private fun performSearchEvent(query: String) {
        viewModel.onEvent(SearchEvent.SearchMovies(query))
    }
    private fun collectMoviesSearch() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collectLatest {
                    adapter.submitData(it)
                }
            }
        }
    }
    private fun setupRcView() {
        adapter = SearchMoviesAdapter()
        binding.rcViewSearch.layoutManager = GridLayoutManager(context, 2)
        binding.rcViewSearch.adapter = adapter
    }
    private fun setupOnClickDetailWindow() {
        adapter.onClickListener = {
            val bundle = Bundle()
            bundle.putString(Constants.KEY_ID_DETAIL, it.id.toString())
            findNavController().navigate(
                R.id.action_searchFragment_to_detailMovieFragment, bundle
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}