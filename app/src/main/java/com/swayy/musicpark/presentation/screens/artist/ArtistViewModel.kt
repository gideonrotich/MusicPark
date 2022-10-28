package com.swayy.musicpark.presentation.screens.artist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swayy.musicpark.domain.use_cases.GetArtistUseCase
import com.swayy.musicpark.presentation.screens.posts.PostListState
import com.swayy.musicpark.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ArtistViewModel @Inject constructor(
    private val getArtistUseCase: GetArtistUseCase
):ViewModel(){
    private val _state = mutableStateOf(ArtistListState())
    val state:State<ArtistListState> = _state

    init {
        getArtists()
    }

    private fun getArtists(){
        getArtistUseCase().onEach { result ->
            when(result){
                is Resource.Success -> {
                    _state.value = ArtistListState(artists = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = ArtistListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = ArtistListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}