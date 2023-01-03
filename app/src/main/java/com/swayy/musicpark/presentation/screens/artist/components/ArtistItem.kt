package com.swayy.musicpark.presentation.screens.artist.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.swayy.musicpark.R
import com.swayy.musicpark.domain.models.Artist
import com.swayy.musicpark.domain.models.Post
import com.swayy.musicpark.presentation.Screen

@Composable
fun ArtistItem(
    navController: NavController,
    artist: Artist,
    onItemClicked: (artist: Artist) -> Unit
) {
    Box(modifier = Modifier.padding(0.dp)) {
        Column {
            Card(
                modifier = Modifier
                    .size(250.dp, 170.dp)
                    .padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 10.dp)
                    .clickable { onItemClicked(artist) },
                elevation = 2.dp
            ) {
                Box {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("https://api.napster.com/imageserver/v2/artists/${artist.id}/images/500x500.jpg")
                            .crossfade(true)
                            .error(R.drawable.error404)
                            .build(),
                        contentDescription = stringResource(R.string.artist_description),
                        contentScale = ContentScale.Crop
                    )

                    Row(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(start = 10.dp, bottom = 10.dp)
                            .clip(RoundedCornerShape(100.dp))
                            .background(Color.Black.copy(alpha = 0.3f))
                            .clickable { onItemClicked(artist) }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_baseline_play_arrow_24),
                            contentDescription = "play",
                            modifier = Modifier
                                .size(40.dp)
                                .padding(7.dp),
                            colorFilter = ColorFilter.tint(Color.White)
                        )
                    }
                }

            }
            Column(modifier = Modifier.padding(start = 10.dp)) {
                Text(
                    text = artist.name ?: "",
                    fontFamily = FontFamily.SansSerif,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    modifier = Modifier.width(170.dp),
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(2.dp))


                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}