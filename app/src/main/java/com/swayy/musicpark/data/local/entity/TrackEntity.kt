package com.swayy.musicpark.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.swayy.musicpark.data.remote.dto.tracks.Contributors
import com.swayy.musicpark.data.remote.dto.tracks.Format
import com.swayy.musicpark.data.remote.dto.tracks.Links
import com.swayy.musicpark.data.remote.dto.tracks.LosslessFormat

@Entity(tableName = "tracks_table")
data class TrackEntity(
    val albumId: String,
    val albumName: String,
    val artistId: String,
    val artistName: String,
    val blurbs: List<String>,
    val contributors: Contributors,
    val disc: Int,
    val formats: List<Format>,
    val href: String,
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val index: Int,
    val isAvailableInAtmos: Boolean,
    val isAvailableInHiRes: Boolean,
    val isExplicit: Boolean,
    val isStreamable: Boolean,
    val isrc: String,
    val links: Links,
    val losslessFormats: List<LosslessFormat>,
    val name: String,
    val playbackSeconds: Int,
    val previewURL: String,
    val shortcut: String,
    val type: String
)
