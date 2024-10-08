package com.example.moviesapplication.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapplication.domain.model.movie.MovieResponse
import com.example.moviesapplication.domain.model.movie.PersonResponse
import com.example.moviesapplication.domain.repository.MovieRepository
import com.example.moviesapplication.domain.use_case.GetPopularActorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getPopularActorUseCase: GetPopularActorUseCase) :
    ViewModel() {

    private val _actor = MutableLiveData<PersonResponse>()
    val actor: LiveData<PersonResponse> get() = _actor

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    fun fetchPopularActor(apiKey: String, token: String) {
        viewModelScope.launch {
            _loading.value = true
            try {
                getPopularActorUseCase.execute(apiKey, token).collect { response ->
                    _actor.value = response
                }
            } catch (e: Exception) {
                // Handle the exception
            } finally {
                _loading.value = false
            }
        }
    }
}