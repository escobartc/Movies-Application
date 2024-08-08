package com.example.moviesapplication.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviesapplication.domain.model.movie.PopularMovieEntity
import com.example.moviesapplication.domain.model.movie.RecommendedMovieEntity
import com.example.moviesapplication.domain.model.movie.TopMovieEntity

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<PopularMovieEntity>)

    @Query("SELECT * FROM popularMovies WHERE id = :movieId")
    suspend fun getMovieById(movieId: Int): PopularMovieEntity?

    @Query("SELECT * FROM popularMovies")
    suspend fun getAllMovies(): List<PopularMovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTopMovies(movies: List<TopMovieEntity>)

    @Query("SELECT * FROM topRatedMovies WHERE id = :movieId")
    suspend fun getTopMovieById(movieId: Int): TopMovieEntity?

    @Query("SELECT * FROM topRatedMovies")
    suspend fun getAllTopMovies(): List<TopMovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRecommendedMovies(movies: List<RecommendedMovieEntity>)

    @Query("SELECT * FROM recommendedMovies WHERE id = :movieId")
    suspend fun getRecommendedMovieById(movieId: Int): RecommendedMovieEntity?

    @Query("SELECT * FROM recommendedMovies")
    suspend fun getAllRecommendedMovies(): List<RecommendedMovieEntity>
}
