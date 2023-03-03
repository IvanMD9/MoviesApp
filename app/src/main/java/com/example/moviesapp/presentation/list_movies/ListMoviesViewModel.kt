package com.example.moviesapp.presentation.list_movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.moviesapp.domain.use_case.GetListMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListMoviesViewModel @Inject constructor(
    private val getListMoviesUseCase: GetListMoviesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ListUIState())
    val state: StateFlow<ListUIState> = _state.asStateFlow()

    init {
        //_state.value = MoviesState.Loading
        getListMovies()
    }
    private fun getListMovies() {
        viewModelScope.launch {
            getListMoviesUseCase()
                .cachedIn(viewModelScope)
                .collect { result ->
                    _state.update { it.copy(list = result) }
                    //_state.value = MoviesState.ListMovies(list = result)
                }
        }
    }
}