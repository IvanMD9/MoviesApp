package com.example.moviesapp.presentation.detail_movie

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.domain.use_case.GetDetailMovieUseCase
import com.example.moviesapp.util.Constants
import com.example.moviesapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val getDetailMovieUseCase: GetDetailMovieUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state: StateFlow<DetailState> = _state.asStateFlow()

    init {
        savedStateHandle.get<String>(Constants.KEY_ID_DETAIL)?.let { id ->
            getDetailMovie(id)
        }
    }

    private fun getDetailMovie(id: String) {
        getDetailMovieUseCase.invoke(id).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = DetailState(loading = true)
                }
                is Resource.Success -> {
                    _state.value = DetailState(detail = result.data)
                }
                is Resource.Error -> {
                    _state.value = DetailState(error = result.message ?: "An unexpected error")
                }
            }
        }.launchIn(viewModelScope)
    }
}