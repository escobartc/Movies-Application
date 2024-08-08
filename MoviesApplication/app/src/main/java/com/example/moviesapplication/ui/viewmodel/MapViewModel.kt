package com.example.moviesapplication.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapplication.domain.model.location.LocationData
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(private val firestore: FirebaseFirestore) : ViewModel() {
    private val _locations = MutableLiveData<List<LocationData>>(emptyList())
    val locations: LiveData<List<LocationData>> get() = _locations

    fun fetchLocations() {
        firestore.collection("locations")
            .get()
            .addOnSuccessListener { result ->
                val locationsList = mutableListOf<LocationData>()
                for (document in result) {
                    val latitude = document.getDouble("latitude") ?: 0.0
                    val longitude = document.getDouble("longitude") ?: 0.0
                    val timeStamp = document.getTimestamp("timestamp")
                    val formattedTimeStamp = timeStamp?.toDate()?.let { formatTimestamp(it) } ?: ""
                    locationsList.add(
                        LocationData(
                            latitude = latitude,
                            longitude = longitude,
                            timestamp = formattedTimeStamp
                        )
                    )
                }
                _locations.value = locationsList
            }
            .addOnFailureListener {
                // Handle errors
            }
    }

    fun formatTimestamp(date: Date): String {
        val dateFormat = SimpleDateFormat("dd MMMM yyyy 'at' HH:mm:ss", Locale.getDefault())
        return dateFormat.format(date)
    }
}
