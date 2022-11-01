package com.swayy.musicpark.presentation.screens.search.components

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kanyideveloper.dlight.presentation.components.SearchWidgetState
import com.swayy.musicpark.domain.use_cases.GetArtistSearchUseCase
import com.swayy.musicpark.domain.use_cases.GetSongUseCase
import com.swayy.musicpark.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistSearchViewModel @Inject constructor(
    private val getArtistSearchUseCase: GetArtistSearchUseCase,
    private val getSongUseCase: GetSongUseCase
) : ViewModel(){

    private val _searchWidgetState: MutableState<SearchWidgetState> =
        mutableStateOf(value = SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState

    fun updateSearchWidgetState(newValue: SearchWidgetState) {
        _searchWidgetState.value = newValue
    }

    private val _searchString = mutableStateOf("")
    val searchString: State<String> = _searchString

    fun setSearchString(value: String) {
        _searchString.value = value
    }

    private val _userDataState = mutableStateOf(ArtistSearchListState())
    val userDataState: State<ArtistSearchListState> = _userDataState

    private val _songDataState = mutableStateOf(SongListState())
    val songDataState: State<SongListState> = _songDataState

    fun searchArtist(artist: String) {
        viewModelScope.launch {
            getArtistSearchUseCase.invoke(artist).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _userDataState.value =
                            ArtistSearchListState(artist = result.data ?: emptyList())
                    }
                    is Resource.Error -> {
                        Log.d("DETAILS:: ", result.message.toString())
                    }
                    is Resource.Loading -> {
                        _userDataState.value = ArtistSearchListState(isLoading = true)
                    }
                }
            }
        }
    }

     fun searchSong(song: String) {
        viewModelScope.launch {
            getSongUseCase.invoke(song).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _songDataState.value =
                            SongListState(song = result.data ?: emptyList())
                    }
                    is Resource.Error -> {
                        Log.d("DETAILS:: ", result.message.toString())
                    }
                    is Resource.Loading -> {
                        _songDataState.value = SongListState(isLoading = true)
                    }
                }
            }
        }
    }
}