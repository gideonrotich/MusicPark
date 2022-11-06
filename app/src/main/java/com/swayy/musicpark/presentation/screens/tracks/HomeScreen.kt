package com.swayy.musicpark.presentation.screens.tracks

import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
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
import com.swayy.musicpark.presentation.screens.artist.ArtistViewModel
import com.swayy.musicpark.presentation.screens.artist.components.ArtistItem
import com.swayy.musicpark.presentation.screens.posts.PostViewModel
import com.swayy.musicpark.presentation.screens.posts.components.PostItem
import com.swayy.musicpark.presentation.screens.tracks.components.TopBar
import com.swayy.musicpark.presentation.screens.tracks.components.TrackItem

@Composable
fun HomeScreen(
    navController: NavController,
    trackViewModel: TrackViewModel = hiltViewModel(),
    postViewModel: PostViewModel = hiltViewModel(),
    artistViewModel: ArtistViewModel = hiltViewModel()
) {
    val trackState = trackViewModel.state.value
    val postState = postViewModel.state.value
    val artistState = artistViewModel.state.value
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.darkbluetwo))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            TopBar()
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = "Recommended",
                    color = Color.White,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(top = 0.dp, start = 20.dp),
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
                        .clickable { navController.navigate(Screen.LoginScreen.route) },
                    colorFilter = ColorFilter.tint(Color.Gray)

                )
                Spacer(modifier = Modifier.weight(0.3f))
            }

            if (postState.isLoading) {
                Row {
                  repeat(4){
                      AnimatedShimmer()
                  }
                }

            }

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp, top = 6.dp)
            ) {
                items(postState.posts) { posts ->
                    PostItem(
                        navController = navController,
                        post = posts,
                        onItemClicked = {
                            navController.navigate(Screen.PostDetails.route + "/${posts.id}")
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = "Popular Pop Tracks",
                    color = Color.White,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(top = 0.dp, start = 20.dp),
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
            if (trackState.isLoading){
                Row() {
                    repeat(4){
                        AnimatedShimmertwo()
                    }
                }
            }

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp, top = 6.dp)
            ) {
                items(trackState.tracks) { tracks ->
                    TrackItem(
                        navController = navController,
                        track = tracks,
                        onItemClicked = {
                            navController.navigate(Screen.TrackDetails.route + "/${tracks.id}")
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = "Popular Pop Artists",
                    color = Color.White,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(top = 0.dp, start = 20.dp),
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
            if (artistState.isLoading) {
                Row {
                    repeat(4){
                        AnimatedShimmer()
                    }
                }

            }
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp, top = 6.dp)
            ) {
                items(artistState.artists) { artists ->
                    ArtistItem(
                        navController = navController,
                        artist = artists,
                        onItemClicked = {
                            navController.navigate(Screen.ArtistDetails.route + "/${artists.id}")
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(80.dp))

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

    }


}


@Composable
fun AnimatedShimmer() {
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.1f),
        Color.LightGray.copy(alpha = 0.2f),
    )

    val transition = rememberInfiniteTransition()
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )

    ShimmerGridItem(brush = brush)
}

@Composable
fun ShimmerGridItem(brush: Brush) {
    Column(
        modifier = Modifier
            .padding(all = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier
                .height(120.dp)
                .width(210.dp)
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth(fraction = 0.7f)
                .background(brush)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Column(verticalArrangement = Arrangement.Center) {
            Spacer(
                modifier = Modifier
                    .height(18.dp)
                    .width(210.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .fillMaxWidth(fraction = 0.9f)
                    .background(brush)
            )


        }
    }
}

@Composable
fun AnimatedShimmertwo() {
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.1f),
        Color.LightGray.copy(alpha = 0.2f),
    )

    val transition = rememberInfiniteTransition()
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )

    ShimmerGridItemtwo(brush = brush)
}

@Composable
fun ShimmerGridItemtwo(brush: Brush) {
    Column(
        modifier = Modifier
            .padding(all = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier
                .height(120.dp)
                .width(120.dp)
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth(fraction = 0.7f)
                .background(brush)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Column(verticalArrangement = Arrangement.Center) {
            Spacer(
                modifier = Modifier
                    .height(16.dp)
                    .width(120.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .fillMaxWidth(fraction = 0.9f)
                    .background(brush)
            )


        }
    }
}