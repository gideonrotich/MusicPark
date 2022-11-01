package com.swayy.musicpark.presentation.screens.genre

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swayy.musicpark.domain.use_cases.GetGenreUseCase
import com.swayy.musicpark.presentation.screens.artist.components.AlbumListState
import com.swayy.musicpark.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(
    private val getGenreUseCase: GetGenreUseCase
):ViewModel() {
    private val _state = mutableStateOf(GenreListState())
    val state: State<GenreListState> = _state

    init {
        getGenre()
    }

    private fun getGenre() {
        viewModelScope.launch {
            getGenreUseCase.invoke().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value =
                            GenreListState(genre = result.data ?: emptyList())
                    }
                    is Resource.Error -> {
                        Log.d("DETAILS:: ", result.message.toString())
                    }
                    is Resource.Loading -> {
                        Log.d("DETAILS:: ", "Loading.....")
                    }
                }
            }
        }
    }
}