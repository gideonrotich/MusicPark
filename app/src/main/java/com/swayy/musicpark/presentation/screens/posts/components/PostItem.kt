package com.swayy.musicpark.presentation.screens.posts.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.swayy.musicpark.R
import com.swayy.musicpark.data.remote.dto.posts.Content
import com.swayy.musicpark.domain.models.Post
import com.swayy.musicpark.domain.models.Track
import com.swayy.musicpark.presentation.Screen

@Composable
fun PostItem(
    navController: NavController,
    post: Post,
    onItemClicked: (post: Post) -> Unit
){
    Box(modifier = Modifier.padding(0.dp)) {
        Column {
            Card(
                modifier = Modifier
                    .size(250.dp, 170.dp)
                    .padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 10.dp)
                    .clickable { onItemClicked(post) },
                elevation = 2.dp
            ) {
                Box {
                    AsyncImage(
                        model = "https://api.napster.com/imageserver/v2/imagesets/${post.image}/images/500x500.jpg",
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                    )
                    Row(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(start = 10.dp, bottom = 10.dp)
                            .clip(RoundedCornerShape(100.dp))
                            .background(Color.Black.copy(alpha = 0.3f))
                            .clickable { onItemClicked(post) }
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
                    text = post.name,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    modifier = Modifier.width(170.dp),
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(2.dp))
                LazyColumn(){
                    items(items = post.content.take(1)){
                        Text(
                            text = it.content,
                            fontFamily = FontFamily.Serif,
                            color = Color.LightGray,
                            fontWeight = FontWeight.Normal,
                            fontSize = 13.sp,
                            modifier = Modifier.width(190.dp),
                            maxLines = 2
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}
