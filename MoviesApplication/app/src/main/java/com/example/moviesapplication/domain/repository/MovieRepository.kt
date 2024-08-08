package com.example.moviesapplication.domain.repository

import com.example.moviesapplication.data.MovieApi
import com.example.moviesapplication.data.MovieDao
import com.example.moviesapplication.domain.model.movie.MovieResponse
import com.example.moviesapplication.domain.model.movie.PersonResponse
import com.example.moviesapplication.domain.model.movie.PopularMovieEntity
import com.example.moviesapplication.domain.model.movie.RecommendedMovieEntity
import com.example.moviesapplication.domain.model.movie.TopMovieEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val movieApi: MovieApi,
    private val movieDao: MovieDao,
) {
    suspend fun getPopularMovies(apiKey: String): MovieResponse {
        val response = movieApi.getPopularMovies(apiKey = apiKey)
        // Save movies to the database
        response.results.map { movie ->
            // Convert API model to Room entity
            PopularMovieEntity(
                id = movie.id,
                overview = movie.overview,
                popularity = movie.popularity,
                posterPath = movie.posterPath,
                title = movie.title
            )
        }.also { movieEntities ->
            movieDao.insertAll(movieEntities)
        }
        return response
    }

    suspend fun getTopRatedMovies(apiKey: String): MovieResponse {
        val response = movieApi.getTopRated(apiKey = apiKey)
        // Save movies to the database
        response.results.map { movie ->
            TopMovieEntity(
                id = movie.id,
                overview = movie.overview,
                popularity = movie.popularity,
                posterPath = movie.posterPath,
                title = movie.title
            )
        }.also { movieEntities ->
            movieDao.insertAllTopMovies(movieEntities)
        }
        return response
    }

    suspend fun getRecommendations(movieId: Int, apiKey: String): MovieResponse {
        val response = movieApi.getRecommendations(movieId = movieId, apiKey = apiKey)
        // Save movies to the database
        response.results.map { movie ->
            RecommendedMovieEntity(
                id = movie.id,
                overview = movie.overview,
                popularity = movie.popularity,
                posterPath = movie.posterPath,
                title = movie.title
            )
        }.also { movieEntities ->
            movieDao.insertAllRecommendedMovies(movieEntities)
        }
        return response
    }

    suspend fun getPopularActor(apiKey: String, token: String): PersonResponse {
        return movieApi.getPopularPerson(apiKey, token)
    }

}
