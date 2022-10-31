package com.swayy.musicpark.data.remote.dto.album

data class Meta(
    val limit: Int,
    val offset: Int,
    val returnedCount: Int,
    val totalCount: Int
)