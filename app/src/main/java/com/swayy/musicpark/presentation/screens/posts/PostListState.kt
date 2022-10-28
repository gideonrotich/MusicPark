package com.swayy.musicpark.presentation.screens.posts

import com.swayy.musicpark.domain.models.Post
import com.swayy.musicpark.domain.models.Track

data class PostListState(
    val isLoading: Boolean = false,
    val error: String = "",
    val posts: List<Post> = emptyList()
)
