package com.swayy.musicpark.presentation.screens.tracks.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.swayy.musicpark.R
import com.swayy.musicpark.domain.models.Track

@Composable
fun TrackItem(
    track:Track,
    onItemClicked: (track : Track) -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onItemClicked(track) },

    ) {
        Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = track.name,
            fontSize = 16.sp, fontWeight = FontWeight.Bold
            )
            Text(text = track.artistName,
                fontSize = 16.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = "isStreamable : "+track.isStreamable,
                fontSize = 13.sp, color = Color.Blue)
            Spacer(modifier = Modifier.height(10.dp))
            Divider(color = Color.Gray, thickness = 1.dp)
        }
        Icon(painter = painterResource(id = R.drawable.ic_baseline_play_circle_filled_24),
            contentDescription = "",
        modifier = Modifier.size(40.dp))
    }
}