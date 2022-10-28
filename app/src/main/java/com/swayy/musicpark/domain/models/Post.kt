package com.swayy.musicpark.domain.models

import com.swayy.musicpark.data.remote.dto.posts.Content
import com.swayy.musicpark.data.remote.dto.posts.Links

data class Post(
    val author: String,
    val content: List<Content>,
    val date: String,
    val href: String,
    val id: String,
    val image: String,
    val language: String,
    val links: Links,
    val name: String,
    val primaryMedia: String,
    val shortcut: String,
    val subType: String,
    val type: String
)
