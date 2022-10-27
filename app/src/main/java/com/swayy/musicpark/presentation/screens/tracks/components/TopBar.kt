package com.swayy.musicpark.presentation.screens.tracks.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.swayy.musicpark.R

@Composable
fun TopBar() {
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
            Spacer(modifier = Modifier.width(200.dp))
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
                        .align(Alignment.CenterVertically).clickable {},
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