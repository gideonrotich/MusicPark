package com.swayy.musicpark.presentation.screens.genre.genredetail

import com.swayy.musicpark.domain.models.Genre

data class GenreDetailListState(
    val isLoading: Boolean = false,
    val error: String = "",
    val genre: List<Genre> = emptyList()
)
