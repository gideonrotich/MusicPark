package com.swayy.musicpark.presentation.screens.tracks.components

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.swayy.musicpark.domain.models.Track

@Composable
fun AllTracksItem(
    navController: NavController,
    track: Track,
    onItemClicked: (track: Track) -> Unit
) {
    Box(
        modifier = Modifier
            .height(86.dp)
            .padding(start = 12.dp, top = 12.dp, bottom = 12.dp, end = 0.dp)
    ) {
        Row {
            Row(modifier = Modifier.fillMaxSize().clickable { onItemClicked(track) }) {
                Text(
                    text = track.index.toString(),
                    color = Color.LightGray,
                    fontSize = 14.sp,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Card(elevation = 2.dp) {
                    AsyncImage(
                        model = "https://api.napster.com/imageserver/v2/albums/${track.albumId}/images/500x500.jpg",
                        contentDescription = "",
                        modifier = Modifier
                            .size(66.dp,70.dp),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))
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
                    Text(
                        text = track.artistName,
                        color = Color.Gray,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily.Serif,
                        maxLines = 1,
                        modifier = Modifier.width(150.dp)
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = track.albumName,
                        color = Color.Gray,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily.Serif,
                        maxLines = 1,
                        modifier = Modifier.width(150.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(3.6f))
                Image(
                    painter = painterResource(id = R.drawable.dots),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(Color.LightGray),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(22.dp)

                )
            }
        }
    }
}