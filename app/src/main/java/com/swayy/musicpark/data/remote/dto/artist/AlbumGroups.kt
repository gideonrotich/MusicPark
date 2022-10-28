package com.swayy.musicpark.data.remote.dto.artist

data class AlbumGroups(
    val compilations: List<String>,
    val main: List<String>,
    val others: List<String>,
    val singlesAndEPs: List<String>
)