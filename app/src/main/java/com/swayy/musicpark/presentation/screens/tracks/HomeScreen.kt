package com.swayy.musicpark.presentation.screens.tracks

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.swayy.musicpark.R
import com.swayy.musicpark.presentation.Screen
import com.swayy.musicpark.presentation.screens.tracks.components.TrackItem

@Composable
fun HomeScreen(
    navController: NavController,
    trackViewModel: TrackViewModel = hiltViewModel()
){
    val trackState = trackViewModel.state.value
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
        ){
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(trackState.tracks) { tracks ->
                    TrackItem(
                        track = tracks,
                        onItemClicked = {
                            navController.navigate(Screen.TrackDetails.route + "/${tracks.id}")
                        }
                    )
                }
            }
        }
        if (trackState.error.isNotBlank()) {
            Text(
                text = trackState.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (trackState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = colorResource(
                    id = R.color.purple
                )
            )
        }
    }



}