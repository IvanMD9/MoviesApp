package com.example.moviesapp.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.moviesapp.data.remote.MoviesApi
import com.example.moviesapp.data.repository.auth.RepositoryAuthorizationImpl
import com.example.moviesapp.data.repository.detail_movie.RepositoryDetailMovieDetailImpl
import com.example.moviesapp.data.repository.movies.RepositoryMoviesImpl
import com.example.moviesapp.data.repository.search.RepositorySearchMoviesImpl
import com.example.moviesapp.data.storage.SessionManager
import com.example.moviesapp.domain.repository.RepositoryAuthorization
import com.example.moviesapp.domain.repository.RepositoryDetailMovie
import com.example.moviesapp.domain.repository.RepositoryMovies
import com.example.moviesapp.domain.repository.RepositorySearchMovies
import com.example.moviesapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideMoviesApi(): MoviesApi {
        val logger = HttpLoggingInterceptor()
        logger.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient.Builder = OkHttpClient.Builder()
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
    fun provideRepositoryMovies(api: MoviesApi): RepositoryMovies {
        return RepositoryMoviesImpl(api)
    }

    @Provides
    @Singleton
    fun provideRepositoryDetailMovie(api: MoviesApi): RepositoryDetailMovie {
        return RepositoryDetailMovieDetailImpl(api)
    }

    @Provides
    @Singleton
    fun provideRepositorySearchMovies(api: MoviesApi): RepositorySearchMovies {
        return RepositorySearchMoviesImpl(api)
    }

    @Provides
    @Singleton
    fun provideRepositoryAuthorization(api: MoviesApi): RepositoryAuthorization {
        return RepositoryAuthorizationImpl(api)
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(application: Application) : SharedPreferences? {
        return application.getSharedPreferences(SessionManager.NAME, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideSessionManager(application: Application) : SessionManager {
        return SessionManager(application)
    }
}