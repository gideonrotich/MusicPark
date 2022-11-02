package com.swayy.musicpark.domain.models

import com.swayy.musicpark.data.remote.dto.artist.test.AlbumGroups
import com.swayy.musicpark.data.remote.dto.artist.test.Bio
import com.swayy.musicpark.data.remote.dto.artist.test.Links

data class Test(
    val albumGroups: AlbumGroups?,
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
