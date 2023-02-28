package com.example.moviesapp.data.remote

import com.example.moviesapp.data.model.list_movies.ListMovies
import com.example.moviesapp.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("movie/popular")
    suspend fun getListPopularMovies(
        @Query("page") page : Int? = null,
        @Query("api_key") apiKey : String = Constants.API_KEY
    ) : Response<ListMovies>
}