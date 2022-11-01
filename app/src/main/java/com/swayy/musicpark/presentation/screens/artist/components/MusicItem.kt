package com.swayy.musicpark.presentation.screens.artist.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.swayy.musicpark.R
import com.swayy.musicpark.domain.models.Album
import com.swayy.musicpark.domain.models.Track

@Composable
fun MusicItem(
    navController: NavController,
    track: Track,
    onItemClicked: (track: Track) -> Unit
){
    Box(
        modifier = Modifier
            .height(56.dp)
            .padding(start = 14.dp, top = 6.dp, bottom = 12.dp, end = 10.dp)
    ) {
        Row {
            Row(modifier = Modifier
                .fillMaxSize()
                .clickable { onItemClicked(track) }) {

                Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                    Text(
                        text = track.name,
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        maxLines = 1,
                        modifier = Modifier.width(150.dp)

                    )
                    Spacer(modifier = Modifier.height(2.dp))

                }
                Spacer(modifier = Modifier.weight(3.6f))

                Image(
                    painter = painterResource(id = R.drawable.dots),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(Color.LightGray),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(10.dp)
                )
            }
        }
    }
}