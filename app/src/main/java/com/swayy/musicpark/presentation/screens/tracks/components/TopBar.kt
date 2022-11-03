package com.swayy.musicpark.presentation.screens.tracks.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.swayy.musicpark.R
import java.util.*

@Composable
fun TopBar() {
    Spacer(modifier = Modifier.height(31.dp))
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

            //display greeting message based on time
            Text(
                text = getGreetingMessage(),
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

//use this or the one below
fun getGreetingMessage():String{
    val c = Calendar.getInstance()
    val timeOfDay = c.get(Calendar.HOUR_OF_DAY)

    return when (timeOfDay) {
        in 0..11 -> "Good morning"
        in 12..15 -> "Good afternoon"
        in 16..20 -> "Good evening"
        in 21..23 -> "Good night"
        else -> "Hello"
    }
}

//alternative to the code above
private fun showDayMessage():String {
    val c: Calendar = Calendar.getInstance()
    var message:String ?=null
    val timeOfDay: Int = c.get(Calendar.HOUR_OF_DAY)
    if (timeOfDay >= 0 && timeOfDay < 12) {
        message =  "Good morning"
    } else if (timeOfDay >= 12 && timeOfDay < 16) {
        message =  "Good afternoon"
    } else if (timeOfDay >= 16 && timeOfDay < 21) {
        message =  "Good evening"
    } else if (timeOfDay >= 21 && timeOfDay < 24) {
        message =  "Good night"
    }
    return  message!!
}