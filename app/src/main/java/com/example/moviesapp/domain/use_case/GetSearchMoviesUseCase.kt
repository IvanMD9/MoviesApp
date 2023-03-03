package com.example.moviesapp.domain.use_case

import androidx.paging.PagingData
import com.example.moviesapp.domain.model.SearchMovies
import com.example.moviesapp.domain.repository.RepositorySearchMovies
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchMoviesUseCase @Inject constructor(
    private val repository: RepositorySearchMovies
) {
    operator fun invoke(query: String): Flow<PagingData<SearchMovies>> =
        repository.getSearchMovies(query)
}