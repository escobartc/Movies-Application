package com.example.moviesapplication.data

import com.example.moviesapplication.domain.model.movie.MovieResponse
import com.example.moviesapplication.domain.model.movie.PersonResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): MovieResponse

    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("api_key") apiKey: String): MovieResponse

    @GET("movie/{movie_id}/recommendations")
    suspend fun getRecommendations(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): MovieResponse


    @GET("person/popular")
    suspend fun getPopularPerson(
        @Query("api_key") apiKey: String,
        @Header("Authorization") authorization: String
    ): PersonResponse
}
