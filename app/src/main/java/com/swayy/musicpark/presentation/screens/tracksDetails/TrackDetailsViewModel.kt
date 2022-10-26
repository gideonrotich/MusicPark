package com.swayy.musicpark.presentation.screens.tracksDetails

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swayy.musicpark.domain.use_cases.GetTrackDetailsUseCase
import com.swayy.musicpark.util.Constants
import com.swayy.musicpark.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrackDetailsViewModel @Inject constructor(
    private val trackDetailsUseCase: GetTrackDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(TrackDetailState())
    val state: State<TrackDetailState> = _state

    init {
        getTrackDetails("tra.704687851")

    }

    private fun getTrackDetails(trackId: String) {
        viewModelScope.launch {
            trackDetailsUseCase.invoke(trackId).collect { result ->
                when (result) {

                    is Resource.Success -> {
                        _state.value =
                            TrackDetailState(trackDetails = result.data ?: emptyList())
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