package com.swayy.musicpark.presentation.screens.tracks

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.swayy.musicpark.R
import com.swayy.musicpark.presentation.Screen
import com.swayy.musicpark.presentation.screens.tracks.components.AllTracksItem
import com.swayy.musicpark.presentation.screens.tracks.components.TrackItem

@Composable
fun AllTracksScreen(
    navController: NavController,
    trackViewModel: TrackViewModel = hiltViewModel()
) {
    val trackState = trackViewModel.state.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.darkbluetwo))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp)
        ) {
            Spacer(modifier = Modifier.height(31.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_keyboard_backspace_24),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .clickable { navController.popBackStack() }
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Popular Pop Tracks",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontFamily = FontFamily.SansSerif
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .padding(start = 16.dp, top = 6.dp)
                    .height(50.dp)
            ) {
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(100.dp))
                        .background(colorResource(id = R.color.basicblue))
                        .align(Alignment.CenterVertically)

                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_baseline_play_arrow_24),
                        contentDescription = "play",
                        modifier = Modifier
                            .size(50.dp)
                            .padding(6.dp),
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    painter = painterResource(id = R.drawable.shuffle),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(Color.LightGray),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(12.dp)
                )
                Spacer(modifier = Modifier.weight(2.2f))
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.repeat),
                        contentDescription = "",
                        colorFilter = ColorFilter.tint(Color.LightGray),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(12.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.dots),
                        contentDescription = "",
                        colorFilter = ColorFilter.tint(Color.LightGray),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(12.dp)
                    )
                }
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp, top = 6.dp)
            ) {
                items(trackState.tracks) { tracks ->
                    AllTracksItem(
                        navController = navController,
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
                color = Color.White

            )
        }
    }
}