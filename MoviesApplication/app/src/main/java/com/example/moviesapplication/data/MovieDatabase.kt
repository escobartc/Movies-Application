package com.example.moviesapplication.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.example.moviesapplication.domain.model.movie.PopularMovieEntity
import com.example.moviesapplication.domain.model.movie.RecommendedMovieEntity
import com.example.moviesapplication.domain.model.movie.TopMovieEntity

@Database(
    entities = [PopularMovieEntity::class, TopMovieEntity::class, RecommendedMovieEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "movies_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
