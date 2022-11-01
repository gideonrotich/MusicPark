package com.swayy.musicpark.data.remote.dto.artist.test

data class Artist(
    val albumGroups: AlbumGroups,
    val amg: String,
    val bios: List<Bio>,
    val blurbs: List<String>,
    val href: String,
    val id: String,
    val links: Links,
    val name: String,
    val shortcut: String,
    val type: String
)