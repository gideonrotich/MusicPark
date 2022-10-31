package com.swayy.musicpark.presentation.screens.artist.components

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swayy.musicpark.domain.use_cases.GetAlbumUseCase
import com.swayy.musicpark.presentation.screens.artist.TopListState
import com.swayy.musicpark.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val getAlbumUseCase: GetAlbumUseCase
) : ViewModel() {
    private val _state = mutableStateOf(AlbumListState())
    val state: State<AlbumListState> = _state

    fun getAlbum(id: String) {
        viewModelScope.launch {
            getAlbumUseCase.invoke(id).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value =
                            AlbumListState(album = result.data ?: emptyList())
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