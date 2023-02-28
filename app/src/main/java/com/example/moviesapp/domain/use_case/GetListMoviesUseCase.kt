package com.example.moviesapp.domain.use_case

import androidx.paging.PagingData
import com.example.moviesapp.domain.model.ListPopularMovies
import com.example.moviesapp.domain.repository.RepositoryMovies
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetListMoviesUseCase @Inject constructor(
    private val repository: RepositoryMovies
) {
    operator fun invoke(): Flow<PagingData<ListPopularMovies>> = repository.getListPopularMovies()
}