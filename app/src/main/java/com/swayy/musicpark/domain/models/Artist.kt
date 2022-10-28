package com.swayy.musicpark.domain.models

import com.swayy.musicpark.data.remote.dto.artist.AlbumGroups
import com.swayy.musicpark.data.remote.dto.artist.Bio
import com.swayy.musicpark.data.remote.dto.artist.Links

data class Artist(
    val albumGroups: AlbumGroups,
    val amg: String?,
    val bios: List<Bio>?,
    val blurbs: List<String>?,
    val href: String?,
    val id: String?,
    val links: Links?,
    val name: String?,
    val shortcut: String?,
    val type: String?
)
