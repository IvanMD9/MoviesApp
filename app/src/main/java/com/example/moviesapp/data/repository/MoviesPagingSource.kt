package com.example.moviesapp.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviesapp.data.mappers.toDomainListMovies
import com.example.moviesapp.data.remote.MoviesApi
import com.example.moviesapp.domain.model.ListPopularMovies
import com.example.moviesapp.util.Constants

class MoviesPagingSource(
    private val api: MoviesApi
) : PagingSource<Int, ListPopularMovies>() {

    override fun getRefreshKey(state: PagingState<Int, ListPopularMovies>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ListPopularMovies> {
        return try {
            val currentPage = params.key ?: Constants.STARTING_PAGE_INDEX
            val response = api.getListPopularMovies(page = currentPage)
            val responseData = mutableListOf<ListPopularMovies>()
            val data = response.body()?.results ?: emptyList()
            responseData.addAll(data.map { it.toDomainListMovies() })

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == Constants.STARTING_PAGE_INDEX) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}