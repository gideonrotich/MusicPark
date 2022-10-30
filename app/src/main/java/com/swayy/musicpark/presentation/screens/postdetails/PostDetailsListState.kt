package com.swayy.musicpark.presentation.screens.postdetails

import com.swayy.musicpark.domain.models.Post
import com.swayy.musicpark.domain.models.Track

data class PostDetailsListState(
    val isLoading: Boolean = false,
    val error: String = "",
    val postDetails: List<Post> = emptyList()
)
