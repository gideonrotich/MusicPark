package com.swayy.musicpark.presentation.screens.tracksDetails

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.swayy.musicpark.domain.use_cases.GetTrackDetailsUseCase
import com.swayy.musicpark.util.Constants
import com.swayy.musicpark.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
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
        savedStateHandle.get<String>(Constants.TRACK_ID)?.let { trackId ->
            getTrackDetails(trackId)
        }

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

    fun calculateColorPalette(drawable: Bitmap, onFinish: (Color) -> Unit) {

        Palette.from(drawable).generate { palette ->
            palette?.dominantSwatch?.rgb?.let { colorValue ->
                onFinish(Color(colorValue))
            }
        }
    }

    fun getImageDominantSwatch(drawable: Drawable, onGenerated: (Palette.Swatch) -> Unit) {
        val bitmap = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)
        Palette.from(bitmap).generate { palette ->
            palette?.dominantSwatch?.let {
                onGenerated(it)
            }
        }
    }

}