package com.swayy.musicpark.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.swayy.musicpark.data.remote.dto.genre.Links

@Entity(tableName = "genre_table")
data class GenreEntity(
    val description: String,
    val href: String,
    @PrimaryKey(autoGenerate = false)
    val id: String,
//    val links: Links,
    val name: String,
    val shortcut: String,
    val type: String
)



