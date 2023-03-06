package com.example.moviesapp.data.repository.search

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviesapp.data.mappers.toDomainSearch
import com.example.moviesapp.data.remote.MoviesApi
import com.example.moviesapp.domain.model.SearchMovies
import com.example.moviesapp.util.Constants.STARTING_PAGE_INDEX

class SearchMoviesPagingSource(
    private val api: MoviesApi,
    private val query: String
) : PagingSource<Int, SearchMovies>() {

    override fun getRefreshKey(state: PagingState<Int, SearchMovies>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchMovies> {
        return try {
            val currentPage = params.key ?: STARTING_PAGE_INDEX
            val response = api.getSearchMovies(page = currentPage, query = query)
            val responseData = mutableListOf<SearchMovies>()
            val data = response.body()?.results ?: emptyList()
            responseData.addAll(data.map { it.toDomainSearch() })

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == STARTING_PAGE_INDEX) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}