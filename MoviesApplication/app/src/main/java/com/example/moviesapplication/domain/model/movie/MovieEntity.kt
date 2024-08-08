package com.example.moviesapplication.domain.model.movie

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "popularMovies")
data class PopularMovieEntity(
    @PrimaryKey @SerializedName("id") val id: Int,
    @SerializedName("overview") val overview: String,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("title") val title: String,
)

@Entity(tableName = "topRatedMovies")
data class TopMovieEntity(
    @PrimaryKey @SerializedName("id") val id: Int,
    @SerializedName("overview") val overview: String,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("title") val title: String,
)

@Entity(tableName = "recommendedMovies")
data class RecommendedMovieEntity(
    @PrimaryKey @SerializedName("id") val id: Int,
    @SerializedName("overview") val overview: String,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("title") val title: String,
)
