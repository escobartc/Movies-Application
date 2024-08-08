package com.example.moviesapplication.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor() : ViewModel()  {

    private val _text = MutableLiveData<String>().apply {
        value = "This is camera Fragment"
    }
    val text: LiveData<String> = _text
}