package com.swayy.musicpark.domain.models

import com.swayy.musicpark.data.remote.dto.Song.Contributors
import com.swayy.musicpark.data.remote.dto.Song.Format
import com.swayy.musicpark.data.remote.dto.Song.Links
import com.swayy.musicpark.data.remote.dto.Song.LosslessFormat

data class Song(
    val albumId: String?,
    val albumName: String?,
    val amg: String?,
    val artistId: String?,
    val artistName: String?,
    val blurbs: List<Any>?,
    val contributors: Contributors?,
    val disc: Int?,
    val formats: List<Format>?,
    val href: String?,
    val id: String?,
    val index: Int?,
    val isAvailableInAtmos: Boolean?,
    val isAvailableInHiRes: Boolean?,
    val isExplicit: Boolean?,
    val isStreamable: Boolean?,
    val isrc: String?,
    val links: Links?,
    val losslessFormats: List<LosslessFormat>?,
    val name: String?,
    val playbackSeconds: Int?,
    val previewURL: String?,
    val shortcut: String?,
    val type: String?
)
