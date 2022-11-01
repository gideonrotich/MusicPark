package com.swayy.musicpark.presentation.screens.artist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.swayy.musicpark.R
import com.swayy.musicpark.presentation.Screen
import com.swayy.musicpark.presentation.screens.artist.components.AlbumItem
import com.swayy.musicpark.presentation.screens.artist.components.AlbumViewModel
import com.swayy.musicpark.presentation.screens.artist.components.ArtistItem
import com.swayy.musicpark.presentation.screens.artist.components.SimilarViewModel
import com.swayy.musicpark.presentation.screens.postdetails.PlaylistItem
import com.swayy.musicpark.presentation.screens.postdetails.PostDetailsViewModel
import com.swayy.musicpark.presentation.screens.tracks.components.AllTracksItem
import com.swayy.musicpark.presentation.screens.tracks.components.TrackItem

@Composable
fun ArtistDetailsScreen(
    navController: NavController,
    artistDetailsViewModel: ArtistDetailsViewModel = hiltViewModel(),
    topViewModel: TopViewModel = hiltViewModel(),
    albumViewModel: AlbumViewModel = hiltViewModel(),
    similarViewModel: SimilarViewModel = hiltViewModel()
) {
    val topState = topViewModel.state.value
    val artistState = artistDetailsViewModel.state.value
    val albumState = albumViewModel.state.value
    val similarState = similarViewModel.state.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                colorResource(id = R.color.darkbluetwo)
            )
    ) {

        artistState.artistDetails.let { post ->
            post.forEach {


                Column(modifier = Modifier.fillMaxSize()) {
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
                            model = "https://api.napster.com/imageserver/v2/artists/${it.id}/images/500x500.jpg",
                            contentDescription = null
                        )
                    }

                    Text(
                        text = it.name ?: "",
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
                            painter = painterResource(id = R.drawable.radio),
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(Color.LightGray),
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(11.dp)
                        )

                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = "Top Tracks",
                        color = Color.White,
                        fontSize = 17.sp,
                        modifier = Modifier.padding(top = 0.dp, start = 20.dp),
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    //get playlist tracks
                    topViewModel.getTop(it.id!!)


                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 4.dp, top = 6.dp)
                    ) {
                        items(topState.top.take(3)) { playlist ->
                            AllTracksItem(
                                navController = navController,
                                track = playlist,
                                onItemClicked = {
                                    navController.navigate(Screen.TrackDetails.route + "/${playlist.id}")
                                }
                            )
                            if (topState.isLoading) {
                                CircularProgressIndicator(
                                    modifier = Modifier.align(Alignment.CenterHorizontally),
                                    color = Color.White
                                )
                            }
                        }

                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Albums",
                        color = Color.White,
                        fontSize = 17.sp,
                        modifier = Modifier.padding(top = 0.dp, start = 20.dp),
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    //get albums by an artist
                    albumViewModel.getAlbum(it.id)

                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 4.dp, top = 6.dp)
                    ) {
                        items(albumState.album.take(10)) { playlist ->
                            AlbumItem(
                                navController = navController,
                                album = playlist,
                                onItemClicked = {
                                    navController.navigate(Screen.AlbumDetails.route + "/${playlist.id}")
                                }
                            )
                            if (topState.isLoading) {
                                CircularProgressIndicator(
                                    modifier = Modifier.align(Alignment.CenterHorizontally),
                                    color = Color.White
                                )
                            }
                        }
                    }

                    //get similar artists
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Similar Artists",
                        color = Color.White,
                        fontSize = 17.sp,
                        modifier = Modifier.padding(top = 0.dp, start = 20.dp),
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif
                    )

                    similarViewModel.getSimilar(it.id)

                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 4.dp, top = 6.dp)
                    ) {
                        items(similarState.artists) { artists ->
                            ArtistItem(
                                navController = navController,
                                artist = artists,
                                onItemClicked = {
                                    navController.navigate(Screen.ArtistDetails.route + "/${artists.id}")
                                }
                            )
                        }
                    }

                }


            }
            if (artistState.error.isNotBlank()) {
                Text(
                    text = artistState.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }
            if (artistState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.White
                )
            }
        }


    }
}