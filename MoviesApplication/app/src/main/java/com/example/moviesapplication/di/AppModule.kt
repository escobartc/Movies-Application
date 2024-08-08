package com.example.moviesapplication.di

import com.example.moviesapplication.domain.repository.MovieRepository
import com.example.moviesapplication.domain.use_case.GetPopularActorUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideGetPopularActorUseCase(
        repository: MovieRepository
    ): GetPopularActorUseCase {
        return GetPopularActorUseCase(repository)
    }
}
