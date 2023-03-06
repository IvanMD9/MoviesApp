package com.example.moviesapp.presentation.list_movies

import androidx.lifecycle.*
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

    private val _state = MutableStateFlow(MoviesState())
    val state: StateFlow<MoviesState> = _state.asStateFlow()

    init {
        getListMovies()
    }
    private fun getListMovies() {
        _state.value = state.value.copy(isLoading = true)
        viewModelScope.launch {
            getListMoviesUseCase()
                .cachedIn(viewModelScope)
                .collect { result ->
                    _state.value = state.value.copy(list = result)
                }
        }
    }
}