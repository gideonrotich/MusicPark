package com.swayy.musicpark.presentation.screens.tracks

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swayy.musicpark.domain.use_cases.GetTracksUseCase
import com.swayy.musicpark.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TrackViewModel @Inject constructor(
    private val getTracksUseCase: GetTracksUseCase
) : ViewModel() {

    private val _state = mutableStateOf(TrackListState())
    val state:State<TrackListState> = _state

    init {
        getTracks()
    }

    fun getTracks(){
        getTracksUseCase().onEach { result ->
            when(result){
                is Resource.Success -> {
                    _state.value = TrackListState(tracks = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = TrackListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = TrackListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}