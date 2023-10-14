package com.corcida.recipe.ui.screens.map

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.corcida.recipe.ui.screens.common.RecipesAppComposable
import com.corcida.recipe.ui.screens.common.components.ArrowBackIcon
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalFoundationApi
@Composable
fun MapScreen(
    onBackPressed: () -> Unit,
    id: Int,
    mapViewModel: MapViewModel
){
    val location= mapViewModel.location.collectAsState()
    mapViewModel.findLocationFromRecipe(id)

    var uiSettings by remember { mutableStateOf(MapUiSettings()) }
    var properties by remember {
        mutableStateOf(MapProperties(mapType = MapType.NORMAL))
    }

    RecipesAppComposable {
        location.value?.let { location ->

            val place = LatLng(location.latitude, location.longitude)
            val cameraPositionState = CameraPositionState(CameraPosition.fromLatLngZoom(place, 5f))

            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = location.title) },
                        navigationIcon = { ArrowBackIcon(onBackPressed) }
                    )
                }
            ) { paddingValues ->
                Box(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                ) {
                    GoogleMap(
                        cameraPositionState = cameraPositionState,
                        modifier = Modifier.matchParentSize(),
                        properties = properties,
                        uiSettings = uiSettings
                    ){
                        Marker(
                            state = MarkerState(position = place),
                            title = location.title,
                            snippet = "Origen localizado en ${location.title}"
                        )
                    }
                }
            }

        }

    }
}