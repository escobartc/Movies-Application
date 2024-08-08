package com.example.moviesapplication.ui.view.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.example.moviesapplication.ui.viewmodel.MapViewModel
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState

@Composable
fun MapScreen(viewModel: MapViewModel) {
    val locations by viewModel.locations.observeAsState()

    GoogleMap(modifier = Modifier.fillMaxSize()) {
        locations?.forEach {
            Marker(
                state = MarkerState(position = LatLng(it.latitude, it.longitude)),
                title = it.timestamp,
                snippet = "Lat: ${it.latitude} Long: ${it.longitude}"
            )
        }
    }
}