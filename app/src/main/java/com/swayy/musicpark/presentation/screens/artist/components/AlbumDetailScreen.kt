package com.swayy.musicpark.presentation.screens.artist.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.swayy.musicpark.R
import com.swayy.musicpark.presentation.Screen
import com.swayy.musicpark.presentation.screens.postdetails.PlaylistItem
import com.swayy.musicpark.presentation.screens.postdetails.PostDetailsViewModel
import kotlinx.coroutines.launch

@Composable
fun AlbumDetailScreen(
    navController: NavController,
    albumDetailViewModel: AlbumDetailViewModel = hiltViewModel(),
    musicViewModel: MusicViewModel = hiltViewModel()
) {
    val albumlistState = albumDetailViewModel.state.value
    val musicState = musicViewModel.state.value

    val backgroundColor = MaterialTheme.colors.background

    var dominantColor by remember {
        mutableStateOf(backgroundColor)
    }

    val gradientColors = if (isSystemInDarkTheme()) {
        listOf(
            dominantColor,
            colorResource(id = R.color.darkbluetwo),
            colorResource(id = R.color.darkbluetwo),
            colorResource(id = R.color.darkbluetwo)
        )
    } else {
        listOf(
            MaterialTheme.colors.background,
            MaterialTheme.colors.background
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = gradientColors,
                    endY = LocalConfiguration.current.screenHeightDp.toFloat() *
                            LocalDensity.current.density
                )
            )
            .verticalScroll(rememberScrollState())
    ) {

        albumlistState.album.let { post ->
            post.forEach {

                val image = "https://api.napster.com/imageserver/v2/albums/${it.id}/images/500x500.jpg"


                val painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = image).apply(block = fun ImageRequest.Builder.() {
                            crossfade(true)
                        }).build()
                )

                LaunchedEffect(key1 = painter) {
                    launch {
                        val imageDrawable = painter.imageLoader.execute(painter.request).drawable
                        albumDetailViewModel.getImageDominantSwatch(imageDrawable!!) {
                            dominantColor = Color(it.rgb)

                        }
                    }
                }

                Column(modifier = Modifier.fillMaxSize()) {
                    Spacer(modifier = Modifier.height(31.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_baseline_keyboard_backspace_24),
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(Color.White),
                            modifier = Modifier
                                .padding(start = 4.dp)
                                .clickable { navController.popBackStack() }
                        )
                        Spacer(modifier = Modifier.weight(3.6f))
                        Image(
                            painter = painterResource(id = R.drawable.ic_baseline_share_24),
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(Color.White),
                            modifier = Modifier
                                .padding(start = 4.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(14.dp))

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .padding(start = 20.dp, end = 20.dp),
                        elevation = 4.dp
                    ) {
                        //"https://api.napster.com/imageserver/v2/imagesets/${it.image}/images/500x500.jpg"

                        AsyncImage(
                            alignment = Alignment.Center,
                            contentScale = ContentScale.FillBounds,
                            model = "https://api.napster.com/imageserver/v2/albums/${it.id}/images/500x500.jpg",
                            contentDescription = null
                        )
                    }

                    Text(
                        text = it.name,
                        modifier = Modifier.padding(start = 17.dp, top = 16.dp),
                        fontSize = 32.sp,
                        color = Color.White,

                        fontWeight = FontWeight.Bold
                    )

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

                    musicViewModel.getMusic(it.id)

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 4.dp, top = 6.dp)
                            .height(500.dp)
                    ) {
                        items(musicState.music) { playlist ->
                            MusicItem(
                                navController = navController,
                                track = playlist,
                                onItemClicked = {
                                    navController.navigate(Screen.TrackDetails.route + "/${playlist.id}")
                                }
                            )
                            if (musicState.isLoading) {
                                CircularProgressIndicator(
                                    modifier = Modifier.align(Alignment.CenterHorizontally),
                                    color = Color.White
                                )
                            }
                        }
                    }

                }


            }

            if (albumlistState.error.isNotBlank()) {
                Text(
                    text = albumlistState.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }

            if (albumlistState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.White
                )
            }

        }


    }


}