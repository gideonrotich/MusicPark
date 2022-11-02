package com.swayy.musicpark.presentation.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.swayy.musicpark.R
import com.swayy.musicpark.presentation.screens.search.SearchHeader

@Composable
fun ProfileScreen(
    navController: NavController,
) {
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
            SearchHeader(text = "Profile")
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.padding(start = 8.dp)) {
                Column(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .align(Alignment.CenterVertically),
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.list),
                        contentDescription = "logo",
                        modifier = Modifier.size(24.dp, 24.dp),
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                }

                Text(
                    text = "Playlists",
                    color = Color.White,
                    fontSize = 17.sp,
                    modifier = Modifier
                        .padding(top = 0.dp, start = 14.dp)
                        .align(Alignment.CenterVertically),
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily.SansSerif
                )
            }
            Spacer(modifier = Modifier.height(26.dp))
            Row(modifier = Modifier.padding(start = 8.dp)) {
                Column(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .align(Alignment.CenterVertically),
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.heart),
                        contentDescription = "logo",
                        modifier = Modifier.size(24.dp, 24.dp),
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                }

                Text(
                    text = "Favorites",
                    color = Color.White,
                    fontSize = 17.sp,
                    modifier = Modifier
                        .padding(top = 0.dp, start = 14.dp)
                        .align(Alignment.CenterVertically),
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily.SansSerif
                )
            }
            Spacer(modifier = Modifier.height(26.dp))
            Row(modifier = Modifier.padding(start = 8.dp)) {
                Column(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .align(Alignment.CenterVertically),
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.music),
                        contentDescription = "logo",
                        modifier = Modifier.size(24.dp, 24.dp),
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                }

                Text(
                    text = "Tracks",
                    color = Color.White,
                    fontSize = 17.sp,
                    modifier = Modifier
                        .padding(top = 0.dp, start = 14.dp)
                        .align(Alignment.CenterVertically),
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily.SansSerif
                )
            }
            Spacer(modifier = Modifier.height(26.dp))
            Row(modifier = Modifier.padding(start = 8.dp)) {
                Column(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .align(Alignment.CenterVertically),
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.albums),
                        contentDescription = "logo",
                        modifier = Modifier.size(24.dp, 24.dp),
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                }

                Text(
                    text = "Albums",
                    color = Color.White,
                    fontSize = 17.sp,
                    modifier = Modifier
                        .padding(top = 0.dp, start = 14.dp)
                        .align(Alignment.CenterVertically),
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily.SansSerif
                )
            }
            Spacer(modifier = Modifier.height(26.dp))
            Row(modifier = Modifier.padding(start = 8.dp)) {
                Column(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .align(Alignment.CenterVertically),
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.art),
                        contentDescription = "logo",
                        modifier = Modifier.size(24.dp, 24.dp),
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                }

                Text(
                    text = "Artists",
                    color = Color.White,
                    fontSize = 17.sp,
                    modifier = Modifier
                        .padding(top = 0.dp, start = 14.dp)
                        .align(Alignment.CenterVertically),
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily.SansSerif
                )
            }
            Spacer(modifier = Modifier.height(26.dp))
            Row(modifier = Modifier.padding(start = 8.dp)) {
                Column(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .align(Alignment.CenterVertically),
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.microphone),
                        contentDescription = "logo",
                        modifier = Modifier.size(24.dp, 24.dp),
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                }

                Text(
                    text = "Radio",
                    color = Color.White,
                    fontSize = 17.sp,
                    modifier = Modifier
                        .padding(top = 0.dp, start = 14.dp)
                        .align(Alignment.CenterVertically),
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily.SansSerif
                )
            }
            Spacer(modifier = Modifier.height(26.dp))
            Row(modifier = Modifier.padding(start = 8.dp)) {
                Column(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .align(Alignment.CenterVertically),
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.vid),
                        contentDescription = "logo",
                        modifier = Modifier.size(24.dp, 24.dp),
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                }

                Text(
                    text = "Videos",
                    color = Color.White,
                    fontSize = 17.sp,
                    modifier = Modifier
                        .padding(top = 0.dp, start = 14.dp)
                        .align(Alignment.CenterVertically),
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily.SansSerif
                )
            }
            Spacer(modifier = Modifier.height(26.dp))
            Row(modifier = Modifier.padding(start = 8.dp)) {
                Column(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .align(Alignment.CenterVertically),
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.top),
                        contentDescription = "logo",
                        modifier = Modifier.size(24.dp, 24.dp),
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                }

                Text(
                    text = "My Top Plays",
                    color = Color.White,
                    fontSize = 17.sp,
                    modifier = Modifier
                        .padding(top = 0.dp, start = 14.dp)
                        .align(Alignment.CenterVertically),
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily.SansSerif
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }

}