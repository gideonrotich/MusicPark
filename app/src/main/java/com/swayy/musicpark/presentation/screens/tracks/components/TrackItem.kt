package com.swayy.musicpark.presentation.screens.tracks.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.swayy.musicpark.R
import com.swayy.musicpark.domain.models.Track

@Composable
fun TrackItem(track:Track){
    Row(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = track.name,
            fontSize = 16.sp)
            Text(text = track.artistName,
                fontSize = 16.sp)
            Divider(color = Color.Gray, thickness = 1.dp)
        }
        Icon(painter = painterResource(id = R.drawable.ic_baseline_play_circle_filled_24), contentDescription = "",
        modifier = Modifier.size(40.dp))
    }
}