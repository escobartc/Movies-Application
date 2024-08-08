package com.example.moviesapplication.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapplication.domain.model.movie.MovieResponse
import com.example.moviesapplication.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val repository: MovieRepository) :
    ViewModel() {

    private val _popularMovies = MutableLiveData<MovieResponse>()
    val popularMovies: LiveData<MovieResponse> get() = _popularMovies

    private val _topRatedMovies = MutableLiveData<MovieResponse>()
    val topRatedMovies: LiveData<MovieResponse> get() = _topRatedMovies

    private val _recommendedMovies = MutableLiveData<MovieResponse>()
    val recommendedMovies: LiveData<MovieResponse> get() = _recommendedMovies

    private val _loadingPopular = MutableLiveData<Boolean>()
    val loadingPopular: LiveData<Boolean> get() = _loadingPopular

    private val _loadingTop = MutableLiveData<Boolean>()
    val loadingTop: LiveData<Boolean> get() = _loadingTop

    private val _loadingRecommendation = MutableLiveData<Boolean>()
    val loadingRecommendation: LiveData<Boolean> get() = _loadingRecommendation

    private val _isNetworkAvailable = MutableLiveData<Boolean>()
    val isNetworkAvailable: LiveData<Boolean> get() = _isNetworkAvailable

    fun fetchPopularMovies(apiKey: String) {
        viewModelScope.launch {
            _loadingPopular.value = true
            try {
                val response = repository.getPopularMovies(apiKey)
                _popularMovies.value = response
            } catch (e: Exception) {
                // Handle the exception
            } finally {
                _loadingPopular.value = false
            }
        }
    }

    fun fetchTopMovies(apiKey: String) {
        viewModelScope.launch {
            _loadingTop.value = true
            try {
                val response = repository.getTopRatedMovies(apiKey)
                _topRatedMovies.value = response
            } catch (e: Exception) {
                // Handle the exception
            } finally {
                _loadingTop.value = false
            }
        }
    }

    fun fetchRecommendedMovies(apiKey: String, movieId: Int) {
        viewModelScope.launch {
            _loadingRecommendation.value = true
            try {
                val response = repository.getRecommendations(apiKey = apiKey, movieId = movieId)
                _recommendedMovies.value = response
            } catch (e: Exception) {
                // Handle the exception
            } finally {
                _loadingRecommendation.value = false
            }
        }
    }
}