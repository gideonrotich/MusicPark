package com.swayy.musicpark.domain.models

import com.swayy.musicpark.data.remote.dto.genre.Links

data class Genre(
    val description: String,
    val href: String,
    val id: String,
    val links: Links,
    val name: String,
    val shortcut: String,
    val type: String
)
