package com.swayy.musicpark.presentation.screens.artist.components

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swayy.musicpark.domain.use_cases.GetMusicUseCase
import com.swayy.musicpark.presentation.screens.tracksDetails.TrackDetailState
import com.swayy.musicpark.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MusicViewModel @Inject constructor(
    private val getMusicUseCase: GetMusicUseCase
) : ViewModel() {
    private val _state = mutableStateOf(MusicListState())
    val state: State<MusicListState> = _state

    fun getMusic(id: String) {
        viewModelScope.launch {
            getMusicUseCase.invoke(id).collect { result ->
                when (result) {

                    is Resource.Success -> {
                        _state.value =
                            MusicListState(music = result.data ?: emptyList())
                    }

                    is Resource.Error -> {
                        Log.d("DETAILS:: ", result.message.toString())
                    }
                    is Resource.Loading -> {
                        MusicListState(isLoading = true)
                    }
                }
            }
        }
    }
}