package com.swayy.musicpark.data.remote.dto.album

data class ContributingArtists(
    val featuredPerformer: String,
    val guestVocals: String,
    val nonPrimary: String,
    val primaryArtist: String,
    val producer: String
)