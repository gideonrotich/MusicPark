package com.swayy.musicpark.presentation.screens.postdetails

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swayy.musicpark.domain.use_cases.GetPlaylistsUseCase
import com.swayy.musicpark.presentation.screens.tracksDetails.TrackDetailState
import com.swayy.musicpark.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaylistViewModel @Inject constructor(
    private val getPlaylistsUseCase: GetPlaylistsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(PlaylistLIstState())
    val state: State<PlaylistLIstState> = _state


    fun getPlaylists(id: String) {
        viewModelScope.launch {
            getPlaylistsUseCase.invoke(id).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value =
                            PlaylistLIstState(playlist = result.data ?: emptyList())
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