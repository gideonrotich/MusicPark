package com.swayy.musicpark.presentation.screens.search

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
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
import com.kanyideveloper.dlight.presentation.components.MainAppBar
import com.kanyideveloper.dlight.presentation.components.SearchWidgetState
import com.swayy.musicpark.R
import com.swayy.musicpark.domain.models.Genre
import com.swayy.musicpark.domain.models.Song
import com.swayy.musicpark.domain.models.Test
import com.swayy.musicpark.presentation.Screen
import com.swayy.musicpark.presentation.screens.artist.ArtistViewModel
import com.swayy.musicpark.presentation.screens.artist.components.ArtistItem
import com.swayy.musicpark.presentation.screens.genre.GenreViewModel
import com.swayy.musicpark.presentation.screens.search.components.ArtistSearchViewModel
import com.swayy.musicpark.presentation.screens.tracks.TrackViewModel
import com.swayy.musicpark.presentation.screens.tracks.components.TopBar
import com.swayy.musicpark.presentation.screens.tracks.components.TrackItem
import com.swayy.musicpark.presentation.screens.tracks.components.getGreetingMessage

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: ArtistSearchViewModel = hiltViewModel(),
    trackViewModel: TrackViewModel = hiltViewModel(),
    artistViewModel: ArtistViewModel = hiltViewModel(),
    genreViewModel: GenreViewModel = hiltViewModel()
) {

    val state by viewModel.userDataState
    val songState by viewModel.songDataState
    val searchWidgetState by viewModel.searchWidgetState
    val searchString by viewModel.searchString
    val scaffoldState = rememberScaffoldState()
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current
    val trackState = trackViewModel.state.value
    val artistState = artistViewModel.state.value
    val genreState = genreViewModel.state.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.darkbluetwo))

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.darkbluetwo))
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.darkbluetwo))

            ) {

                //
                SearchHeader(text = "Search")

                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = {
                        MainAppBar(
                            searchWidgetState = searchWidgetState,
                            searchStringState = searchString,
                            onTextChange = {
                                viewModel.setSearchString(it)
                            },
                            onCloseClicked = {
                                viewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)
                            },
                            onSearchClicked = {
                                viewModel.searchArtist(it.trim())
                                viewModel.searchSong(it.trim())
                                keyboardController?.hide()
                            },
                            onSearchTriggered = {
                                viewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
                            },
                            modifier = Modifier
                        )
                    },
                    modifier = Modifier
                        .background(colorResource(id = R.color.darkbluetwo))
                        .padding(start = 0.dp, end = 0.dp, top = 14.dp),
                    backgroundColor = colorResource(id = R.color.darkbluetwo)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(colorResource(id = R.color.darkbluetwo))
                    ) {
                        when (searchWidgetState) {
                            SearchWidgetState.CLOSED -> {
                                Column {
                                    Spacer(modifier = Modifier.height(20.dp))
                                    Row() {
                                        Text(
                                            text = "Genres",
                                            color = Color.White,
                                            fontSize = 18.sp,
                                            modifier = Modifier.padding(top = 0.dp, start = 12.dp),
                                            fontWeight = FontWeight.Bold,
                                            fontFamily = FontFamily.SansSerif
                                        )

                                    }

                                    LazyHorizontalGrid(GridCells.Fixed(2),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 2.dp, top = 6.dp)
                                            .height(200.dp)
                                    ) {
                                        items(genreState.genre) { tracks ->
                                            GenreItem(
                                                navController = navController,
                                                artist = tracks,
                                                onItemClicked = {
                                                    navController.navigate(Screen.TrackDetails.route + "/${tracks.id}")
                                                }
                                            )
                                        }
                                    }



                                    Spacer(modifier = Modifier.height(24.dp))

                                    Row(horizontalArrangement = Arrangement.SpaceBetween) {
                                        Text(
                                            text = "Popular Pop Artists",
                                            color = Color.White,
                                            fontSize = 18.sp,
                                            modifier = Modifier.padding(top = 0.dp, start = 12.dp),
                                            fontWeight = FontWeight.Bold,
                                            fontFamily = FontFamily.SansSerif
                                        )
                                        Spacer(modifier = Modifier.weight(2.4f))
                                        Image(
                                            painter = painterResource(id = R.drawable.ic_baseline_keyboard_arrow_right_24),
                                            contentDescription = "arrow",
                                            modifier = Modifier
                                                .align(Alignment.CenterVertically)
                                                .size(28.dp)
                                                .clickable { navController.navigate(Screen.AllTracks.route) },
                                            colorFilter = ColorFilter.tint(Color.Gray)

                                        )
                                        Spacer(modifier = Modifier.weight(0.3f))
                                    }
                                    LazyRow(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 2.dp, top = 6.dp)
                                            .height(210.dp)
                                    ) {
                                        items(artistState.artists.takeLast(10)) { artists ->
                                            ArtistItem(
                                                navController = navController,
                                                artist = artists,
                                                onItemClicked = {
                                                    navController.navigate(Screen.ArtistDetails.route + "/${artists.id}")
                                                }
                                            )
                                        }
                                    }

                                    ////
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Row(horizontalArrangement = Arrangement.SpaceBetween) {
                                        Text(
                                            text = "Popular Pop Tracks",
                                            color = Color.White,
                                            fontSize = 18.sp,
                                            modifier = Modifier.padding(top = 0.dp, start = 12.dp),
                                            fontWeight = FontWeight.Bold,
                                            fontFamily = FontFamily.SansSerif
                                        )
                                        Spacer(modifier = Modifier.weight(2.4f))
                                        Image(
                                            painter = painterResource(id = R.drawable.ic_baseline_keyboard_arrow_right_24),
                                            contentDescription = "arrow",
                                            modifier = Modifier
                                                .align(Alignment.CenterVertically)
                                                .size(28.dp)
                                                .clickable { navController.navigate(Screen.AllTracks.route) },
                                            colorFilter = ColorFilter.tint(Color.Gray)

                                        )
                                        Spacer(modifier = Modifier.weight(0.3f))

                                    }
                                    LazyRow(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 2.dp, top = 6.dp)
                                            .height(220.dp)
                                    ) {
                                        items(trackState.tracks.takeLast(10)) { tracks ->
                                            TrackItem(
                                                navController = navController,
                                                track = tracks,
                                                onItemClicked = {
                                                    navController.navigate(Screen.TrackDetails.route + "/${tracks.id}")
                                                }
                                            )
                                        }
                                    }

                                }


                            }
                            SearchWidgetState.OPENED -> {
                                Column {
                                    Spacer(modifier = Modifier.height(12.dp))

                                    LazyColumn(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 0.dp, top = 6.dp)

                                    ) {
                                        items(songState.song.take(3)) { song ->
                                            SongItem(
                                                navController = navController,
                                                artist = song,
                                                onItemClicked = {
                                                    navController.navigate(Screen.TrackDetails.route + "/${song.id}")
                                                }
                                            )

                                        }

                                    }
                                    Spacer(modifier = Modifier.height(0.dp))
                                    LazyColumn(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 0.dp, top = 6.dp)

                                    ) {
                                        items(state.artist.take(3)) { playlist ->
                                            ArtistSearchItem(
                                                navController = navController,
                                                artist = playlist,
                                                onItemClicked = {
                                                    navController.navigate(Screen.ArtistDetails.route + "/${playlist.id}")
                                                }
                                            )

                                        }

                                    }
                                }

                            }
                        }
                    }


                }
                //

            }

        }
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = Color.White
            )
        }

    }

}

@Composable
fun SearchHeader(text:String) {
    Box(
        modifier = Modifier
            .height(70.dp)
            .background(colorResource(id = R.color.darkbluetwo))

    ) {
        Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceAround) {
            Column(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .align(Alignment.CenterVertically),
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logotwo),
                    contentDescription = "logo",
                    modifier = Modifier.size(30.dp, 30.dp)
                )
            }

            Text(
                text = text,
                color = Color.White,
                fontSize = 22.sp,
                modifier = Modifier
                    .padding(top = 0.dp, start = 10.dp)
                    .align(Alignment.CenterVertically),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif
            )

            Spacer(modifier = Modifier.weight(3.2f))
            Row(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.settings),
                    contentDescription = "settings",
                    modifier = Modifier
                        .size(24.dp, 24.dp)
                        .align(Alignment.CenterVertically)
                        .clickable {},
                    colorFilter = ColorFilter.tint(Color.White)
                )
                Spacer(modifier = Modifier.width(20.dp))
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "settings",
                    modifier = Modifier
                        .size(28.dp, 28.dp)
                        .align(Alignment.CenterVertically),
                    colorFilter = ColorFilter.tint(Color.White)
                )
            }
        }
    }
}


@Composable
fun ArtistSearchItem(
    navController: NavController,
    artist: Test,
    onItemClicked: (artist: Test) -> Unit
) {
    Box(modifier = Modifier.padding(start = 18.dp)) {
        Row {
            Card(
                modifier = Modifier
                    .size(80.dp, 80.dp)
                    .padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 10.dp)
                    .clickable { onItemClicked(artist) }
                    .clip(RoundedCornerShape(100.dp)),
                elevation = 2.dp
            ) {
                Box {
                    AsyncImage(
                        model = "https://api.napster.com/imageserver/v2/artists/${artist.id}/images/500x500.jpg",
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )

                }

            }

            Row(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .align(Alignment.CenterVertically)
                    .clickable { onItemClicked(artist) }
            ) {
                Text(
                    text = artist.name ?: "",
                    fontFamily = FontFamily.SansSerif,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    modifier = Modifier.width(170.dp),
                    maxLines = 1
                )

            }
        }
    }
}

@Composable
fun SongItem(
    navController: NavController,
    artist: Song,
    onItemClicked: (artist: Song) -> Unit
) {
    Box(modifier = Modifier.padding(start = 18.dp)) {
        Row {
            Card(
                modifier = Modifier
                    .size(115.dp, 80.dp)
                    .padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 10.dp)
                    .clickable { onItemClicked(artist) },
                elevation = 2.dp
            ) {
                Box {
                    AsyncImage(
                        model = "https://api.napster.com/imageserver/v2/albums/${artist.albumId}/images/500x500.jpg",
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )

                }

            }

            Row(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = artist.name ?: "",
                    fontFamily = FontFamily.SansSerif,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    modifier = Modifier.width(170.dp)
                        .clickable { onItemClicked(artist) },
                    maxLines = 1
                )

            }
        }
    }
}


@Composable
fun GenreItem(
    navController: NavController,
    artist: Genre,
    onItemClicked: (artist: Genre) -> Unit
) {
    Box(modifier = Modifier.padding(0.dp)) {

        Card(
            modifier = Modifier
                .size(115.dp, 80.dp)
                .padding(start = 10.dp, top = 10.dp, end = 10.dp)
                .clickable { onItemClicked(artist) },
            elevation = 2.dp
        ) {
            Box {
                AsyncImage(
                    model = "https://api.napster.com/imageserver/images/${artist.id}/500x500.jpg",
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )

            }

        }

        Text(
            text = artist.name,
            fontFamily = FontFamily.SansSerif,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 10.dp, top = 2.dp),
            maxLines = 1
        )


    }
}