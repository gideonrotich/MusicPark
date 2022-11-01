package com.swayy.musicpark.data.remote.dto.Song

data class Search(
    val data: Data,
    val order: List<String>,
    val query: String
)