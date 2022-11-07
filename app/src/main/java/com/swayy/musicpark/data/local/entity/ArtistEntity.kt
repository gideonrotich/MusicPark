package com.swayy.musicpark.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.swayy.musicpark.data.remote.dto.artist.AlbumGroups
import com.swayy.musicpark.data.remote.dto.artist.Bio
import com.swayy.musicpark.data.remote.dto.artist.Links

@Entity(tableName = "artist_table")
data class ArtistEntity(
//    val albumGroups: AlbumGroups?,
    val amg: String?,
//    val bios: List<Bio>?,
//    val blurbs: List<String>?,
    val href: String?,
    @PrimaryKey(autoGenerate = false)
    val id: String?,
//    val links: Links?,
    val name: String?,
    val shortcut: String?,
    val type: String?
)
