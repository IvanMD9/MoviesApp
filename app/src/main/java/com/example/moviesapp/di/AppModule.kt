package com.example.moviesapp.di

import com.example.moviesapp.data.remote.MoviesApi
import com.example.moviesapp.data.repository.RepositoryMoviesImpl
import com.example.moviesapp.domain.repository.RepositoryMovies
import com.example.moviesapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMoviesApi() : MoviesApi {
        val logger = HttpLoggingInterceptor()
        logger.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client : OkHttpClient.Builder = OkHttpClient.Builder()
        client.addInterceptor(logger)

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()
            .create(MoviesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepositoryMovies(api: MoviesApi) : RepositoryMovies {
        return RepositoryMoviesImpl(api)
    }
}