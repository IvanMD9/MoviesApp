package com.example.moviesapp.data.remote

import com.example.moviesapp.data.model.auth.AuthUserDto
import com.example.moviesapp.data.model.detail.MovieDetailDto
import com.example.moviesapp.data.model.list_movies.ListMoviesDto
import com.example.moviesapp.data.model.search.SearchMoviesDto
import com.example.moviesapp.domain.model.auth.*
import com.example.moviesapp.util.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {
    @GET("movie/popular")
    suspend fun getListPopularMovies(
        @Query("page") page : Int? = null,
        @Query("api_key") apiKey : String = Constants.API_KEY
    ) : Response<ListMoviesDto>

    @GET("movie/{movie_id}")
    suspend fun getDetailMovies(
        @Path("movie_id") id : String,
        @Query("api_key") apiKey : String = Constants.API_KEY
    ) : MovieDetailDto

    @GET("search/movie")
    suspend fun getSearchMovies(
        @Query("query") query : String,
        @Query("page") page : Int? = null,
        @Query("api_key") apiKey : String = Constants.API_KEY
    ) : Response<SearchMoviesDto>

    @GET("authentication/token/new")
    suspend fun getNewToken(
        @Query("api_key") apiKey : String = Constants.API_KEY
    ) : NewToken

    @POST("authentication/token/validate_with_login")
    suspend fun postValidateData(
        @Query("api_key") apiKey : String = Constants.API_KEY,
        @Body body : BodyValidateData
    ) : ValidateData

    @POST("authentication/session/new")
    suspend fun postNewSessionId(
        @Query("api_key") apiKey : String = Constants.API_KEY,
        @Body requestToken : BodySessionId
    ) : SessionId

    @GET("account")
    suspend fun getDataAccount(
        @Query("api_key") apiKey : String = Constants.API_KEY,
        @Query("session_id") sessionId : String
    ) : AuthUserDto
}