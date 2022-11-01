package com.swayy.musicpark.presentation.screens.genre

import com.swayy.musicpark.domain.models.Genre
import com.swayy.musicpark.domain.models.Post

data class GenreListState(
    val isLoading: Boolean = false,
    val error: String = "",
    val genre: List<Genre> = emptyList()
)
