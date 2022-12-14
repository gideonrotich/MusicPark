package com.swayy.musicpark.presentation.screens.tracksDetails

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.media.AudioManager
import android.media.MediaPlayer
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import coil.transform.Transformation
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.google.accompanist.coil.rememberCoilPainter
import com.swayy.musicpark.R
import com.swayy.musicpark.ui.theme.DarkBlue
import kotlinx.coroutines.launch

@Composable
fun TrackDetailsScreen(
    trackDetailsViewModel: TrackDetailsViewModel = hiltViewModel()
) {


    val trackDetailsState = trackDetailsViewModel.state.value

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        trackDetailsState.trackDetails.let { track ->

            track.forEach {
                val context = LocalContext.current
                val backgroundColor = MaterialTheme.colors.background

                var dominantColor by remember {
                    mutableStateOf(backgroundColor)
                }

                val image =
                    "https://api.napster.com/imageserver/v2/albums/${it.albumId}/images/500x500.jpg"


                //test
                val painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = image).apply(block = fun ImageRequest.Builder.() {
                            crossfade(true)
                        }).build()
                )

                LaunchedEffect(key1 = painter) {
                    launch {
                        val imageDrawable = painter.imageLoader.execute(painter.request).drawable
                        trackDetailsViewModel.getImageDominantSwatch(imageDrawable!!) {
                            dominantColor = Color(it.rgb)

                        }
                    }
                }

                SongScreenContent(
                    songName = it.name,
                    albumName = it.artistName,
                    id = it.albumId,
                    url = it.previewURL,
                    isSongPlaying = true,
                    imagePainter = painterResource(id = R.drawable.heart),
                    dominantColor = dominantColor,
                    playbackProgress = 1F,
                    currentTime = "0:53",
                    totalTime = "5:14",
                    // playPauseIcon =,
                    yOffset = 2,
                    playOrToggleSong = { /*TODO*/ },
                    playNextSong = { /*TODO*/ },
                    playPreviousSong = { /*TODO*/ },
                    onSliderChange = {},
                    onSliderChangeFinished = { /*TODO*/ },
                    onRewind = { /*TODO*/ },
                    onForward = { /*TODO*/ }) {
                }

            }
        }

    }


}


@Composable
fun SongScreenContent(
    songName: String,
    albumName: String,
    id: String,
    url: String,
    isSongPlaying: Boolean,
    imagePainter: Painter,
    dominantColor: Color,
    playbackProgress: Float,
    currentTime: String,
    totalTime: String,
    // @DrawableRes playPauseIcon: Int,
    yOffset: Int,
    playOrToggleSong: () -> Unit,
    playNextSong: () -> Unit,
    playPreviousSong: () -> Unit,
    onSliderChange: (Float) -> Unit,
    onSliderChangeFinished: () -> Unit,
    onRewind: () -> Unit,
    onForward: () -> Unit,
    onClose: () -> Unit

) {


    val gradientColors = if (isSystemInDarkTheme()) {
        listOf(
            dominantColor,
            dominantColor.copy(alpha = 0.9F),
            dominantColor.copy(alpha = 0.8F),
            dominantColor.copy(alpha = 0.7F),
            dominantColor.copy(alpha = 0.6F),
            dominantColor.copy(alpha = 0.5F),
            dominantColor.copy(alpha = 0.4F),
            dominantColor.copy(alpha = 0.3F),
            dominantColor.copy(alpha = 0.2F),
            dominantColor.copy(alpha = 0.1F),
        )
    } else {
        listOf(
            MaterialTheme.colors.background,
            MaterialTheme.colors.background
        )
    }

    val sliderColors = if (isSystemInDarkTheme()) {
        SliderDefaults.colors(
            thumbColor = MaterialTheme.colors.onBackground,
            activeTrackColor = MaterialTheme.colors.onBackground,
            inactiveTrackColor = MaterialTheme.colors.onBackground.copy(
                alpha = ProgressIndicatorDefaults.IndicatorBackgroundOpacity
            ),
        )
    } else SliderDefaults.colors(
        thumbColor = dominantColor,
        activeTrackColor = dominantColor,
        inactiveTrackColor = dominantColor.copy(
            alpha = ProgressIndicatorDefaults.IndicatorBackgroundOpacity
        ),
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Surface {
            Box(
                modifier = Modifier
                    .background(
                        Brush.verticalGradient(
                            colors = gradientColors,
                            endY = LocalConfiguration.current.screenHeightDp.toFloat() *
                                    LocalDensity.current.density
                        )
                    )
                    .fillMaxSize()
                    .systemBarsPadding()
            ) {
                Column {
                    IconButton(
                        onClick = onClose
                    ) {
                        Image(
                            imageVector = Icons.Rounded.KeyboardArrowDown,
                            contentDescription = "Close",
                            colorFilter = ColorFilter.tint(LocalContentColor.current)
                        )
                    }
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(vertical = 32.dp)
                                .clip(MaterialTheme.shapes.medium)
                                .weight(1f, fill = false)
                                .aspectRatio(1f)

                        ) {

                            VinylAnimation(
                                painter = imagePainter,
                                isSongPlaying = isSongPlaying,
                                id = id
                            )
                        }

                        Text(
                            text = songName,
                            style = MaterialTheme.typography.h5,
                            color = MaterialTheme.colors.onBackground,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        Text(
                            text = albumName,
                            style = MaterialTheme.typography.subtitle1,
                            color = MaterialTheme.colors.onBackground,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.graphicsLayer {
                                alpha = 0.60f
                            }
                        )

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 24.dp)
                        ) {
                            Slider(
                                value = playbackProgress,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                colors = sliderColors,
                                onValueChange = onSliderChange,
                                onValueChangeFinished = onSliderChangeFinished
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                                    Text(
                                        currentTime,
                                        style = MaterialTheme.typography.body2
                                    )
                                }
                                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                                    Text(
                                        totalTime,
                                        style = MaterialTheme.typography.body2
                                    )
                                }
                            }
                        }

                        Row(
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.SkipPrevious,
                                contentDescription = "Skip Previous",
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .clickable(onClick = playPreviousSong)
                                    .padding(12.dp)
                                    .size(32.dp)
                            )
                            Icon(
                                imageVector = Icons.Rounded.Replay10,
                                contentDescription = "Replay 10 seconds",
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .clickable(onClick = onRewind)
                                    .padding(12.dp)
                                    .size(32.dp)
                            )

                            //testing
                            val ctx = LocalContext.current
                            val mediaPlayer = MediaPlayer()

                            var audioUrl = url
                            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)


                            Icon(
                                imageVector = Icons.Default.Pause,
                                contentDescription = "Play",
                                tint = MaterialTheme.colors.background,
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colors.onBackground)
                                    .clickable(onClick =
                                    {
                                        mediaPlayer.setDataSource(audioUrl)

                                        mediaPlayer.prepare()

                                        mediaPlayer.start()
                                    })
                                    .size(64.dp)
                                    .padding(8.dp)
                            )
                            Icon(
                                imageVector = Icons.Rounded.Forward10,
                                contentDescription = "Forward 10 seconds",
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .clickable(onClick = onForward)
                                    .padding(12.dp)
                                    .size(32.dp)
                            )
                            Icon(
                                imageVector = Icons.Rounded.SkipNext,
                                contentDescription = "Skip Next",
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .clickable(onClick = playNextSong)
                                    .padding(12.dp)
                                    .size(32.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun VinylAnimation(
    id: String,
    modifier: Modifier = Modifier,
    isSongPlaying: Boolean = true,
    painter: Painter
) {
    var currentRotation by remember {
        mutableStateOf(0f)
    }

    val rotation = remember {
        Animatable(currentRotation)
    }

    LaunchedEffect(isSongPlaying) {
        if (isSongPlaying) {
            rotation.animateTo(
                targetValue = currentRotation + 360f,
                animationSpec = infiniteRepeatable(
                    animation = tween(3000, easing = LinearEasing),
                    repeatMode = RepeatMode.Restart
                )
            ) {
                currentRotation = value
            }
        } else {
            if (currentRotation > 0f) {
                rotation.animateTo(
                    targetValue = currentRotation + 50,
                    animationSpec = tween(
                        1250,
                        easing = LinearOutSlowInEasing
                    )
                ) {
                    currentRotation = value
                }
            }
        }
    }

    Vinyl(painter = painter, rotationDegrees = rotation.value, id = id)
}


@Composable
fun Vinyl(
    modifier: Modifier = Modifier,
    rotationDegrees: Float = 0f,
    painter: Painter,
    id: String
) {
    Box(
        modifier = modifier
            .aspectRatio(1.0f)
            .clip(CircleShape)
    ) {
        // Vinyl background
        Image(
            modifier = Modifier
                .fillMaxSize()
                .rotate(rotationDegrees),
            painter = painterResource(id = R.drawable.vinyl_background),
            contentDescription = "Vinyl Background"
        )

        // Vinyl song cover
        AsyncImage(
            modifier = Modifier
                .fillMaxSize(0.5f)
                .rotate(rotationDegrees)
                .aspectRatio(1.0f)
                .align(Alignment.Center)
                .clip(CircleShape),
            model = "https://api.napster.com/imageserver/v2/albums/${id}/images/500x500.jpg",
            contentDescription = "Song cover",
            contentScale = ContentScale.FillBounds
        )
    }
}