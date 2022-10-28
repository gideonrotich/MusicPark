package com.swayy.musicpark.data.remote.dto.artist

data class Links(
    val albums: Albums,
    val contemporaries: Contemporaries,
    val followers: Followers,
    val genres: Genres,
    val images: Images,
    val influences: Influences,
    val posts: Posts,
    val relatedProjects: RelatedProjects,
    val stations: Stations,
    val topTracks: TopTracks
)