package com.example.moviesapplication.ui.viewmodel

import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val storage: FirebaseStorage,
) : ViewModel() {

    private val _uploadStatus = MutableLiveData<String>()
    val uploadStatus: LiveData<String> get() = _uploadStatus
    fun uploadImage(uri: Uri, imageName: String) {
        viewModelScope.launch {
            val downloadUrl = uploadImageToFirebase(uri, imageName)
            if (downloadUrl != null) {
                _uploadStatus.value = "Image uploaded successfully!"
            } else {
                _uploadStatus.value = "Failed to upload image."
            }
        }
    }

    suspend fun uploadImageToFirebase(uri: Uri, imageName: String): String? {
        val storageRef =storage.reference
        val imageRef = storageRef.child("images/$imageName")

        return try {
            // Upload the file to Firebase Storage
            val uploadTask = imageRef.putFile(uri)
            uploadTask.await() // Wait for the upload to complete

            // Get the download URL
            val downloadUrl = imageRef.downloadUrl.await()
            downloadUrl.toString()
        } catch (e: Exception) {
            // Handle any errors
            e.printStackTrace()
            null
        }
    }

}