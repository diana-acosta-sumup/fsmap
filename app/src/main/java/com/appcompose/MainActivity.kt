package com.appcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.appcompose.domain.entities.Merchant
import com.appcompose.domain.viewmodel.MapViewModel
import com.appcompose.ui.theme.AppComposeTheme
import com.google.android.libraries.maps.CameraUpdateFactory
import com.google.android.libraries.maps.MapView
import com.google.android.libraries.maps.model.LatLng
import com.google.maps.android.ktx.addMarker
import com.google.maps.android.ktx.awaitMap
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoadMapView("37.6633333321", "-122.410052877")
        }
    }
}

@Composable
private fun LoadMapView(latitude: String, longitude: String) {
    // The MapView lifecycle is handled by this composable. As the MapView also needs to be updated
    // with input from Compose UI, those updates are encapsulated into the MapViewContainer
    // composable. In this way, when an update to the MapView happens, this composable won't
    // recompose and the MapView won't need to be recreated.
    val mapView = MapUtils().rememberMapViewWithLifecycle()
    MapViewContainer(mapView, latitude, longitude, MapViewModel())
}

@Composable
private fun MapViewContainer(
    map: MapView,
    latitude: String,
    longitude: String,
    viewModel: MapViewModel
) {
    val cameraPosition = remember(latitude, longitude) {
        LatLng(latitude.toDouble(), longitude.toDouble())
    }
    var merchants = viewModel.getMerchantList()

    LaunchedEffect(map) {
        val googleMap = map.awaitMap()
        merchants.forEach{
                item -> googleMap.addMarker { position(item.location)}
        }
        //googleMap.addMarker { position(cameraPosition)}
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(cameraPosition))
        googleMap.setMaxZoomPreference(MaxZoom)
        googleMap.setMinZoomPreference(MinZoom)
    }

    var zoom by rememberSaveable(map) { mutableStateOf(InitialZoom) }

    val coroutineScope = rememberCoroutineScope()
    AndroidView({ map }) { mapView ->
        // Reading zoom so that AndroidView recomposes when it changes. The getMapAsync lambda
        // is stored for later, Compose doesn't recognize state reads
        val mapZoom = zoom
        coroutineScope.launch {
            val googleMap = mapView.awaitMap()
            //googleMap.setZoom(mapZoom)
            // Move camera to the same place to trigger the zoom update
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(cameraPosition))
        }
    }
}

private const val InitialZoom = 5f
const val MinZoom = 2f
const val MaxZoom = 20f