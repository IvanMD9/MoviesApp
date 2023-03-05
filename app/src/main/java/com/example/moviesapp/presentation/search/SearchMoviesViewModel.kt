package com.example.moviesapp.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.moviesapp.domain.model.SearchMovies
import com.example.moviesapp.domain.use_case.GetSearchMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchMoviesViewModel @Inject constructor(
    private val getSearchMoviesUseCase: GetSearchMoviesUseCase
) : ViewModel() {

    private val _state = MutableSharedFlow<PagingData<SearchMovies>>()
    val state = _state.asSharedFlow()

    private val _searchQuery: MutableStateFlow<String> = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()
    private var searchJob: Job? = null

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.SearchMovies -> onSearch(event.query)
        }
    }

    init {
        getSearchMovies("")
    }

    private fun getSearchMovies(query: String) {
        getSearchMoviesUseCase(query).onEach {
            _state.emit(it)
        }.launchIn(viewModelScope)
    }

    private fun onSearch(query: String) {
        _searchQuery.value = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(400L)
            getSearchMovies(query)
        }
    }
}