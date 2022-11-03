package com.swayy.musicpark.presentation.screens.artist

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.swayy.musicpark.domain.use_cases.GetArtistDetailsUseCase
import com.swayy.musicpark.presentation.screens.artist.components.ArtistDetailsListState
import com.swayy.musicpark.presentation.screens.postdetails.PostDetailsListState
import com.swayy.musicpark.util.Constants
import com.swayy.musicpark.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ArtistDetailsViewModel @Inject constructor(
    private val getArtistDetailsUseCase: GetArtistDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel(){
    private val _state = mutableStateOf(ArtistDetailsListState())
    val state:State<ArtistDetailsListState> = _state

    init {
        savedStateHandle.get<String>(Constants.ARTIST_ID)?.let { artistId ->
            getArtistDetails(artistId)
        }

    }

    private fun getArtistDetails(id:String){
        getArtistDetailsUseCase(id).onEach {result ->
            when(result){
                is Resource.Success -> {
                    _state.value = ArtistDetailsListState(artistDetails = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = ArtistDetailsListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = ArtistDetailsListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
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