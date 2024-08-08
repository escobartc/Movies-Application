package com.example.moviesapplication.domain.use_case

import com.example.moviesapplication.domain.model.movie.PersonResponse
import com.example.moviesapplication.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPopularActorUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    fun execute(apiKey: String, token: String): Flow<PersonResponse> = flow {
        emit(repository.getPopularActor(apiKey, token))
    }
}
