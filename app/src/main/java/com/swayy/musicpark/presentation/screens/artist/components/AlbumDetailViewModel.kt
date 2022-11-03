package com.swayy.musicpark.presentation.screens.artist.components

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.swayy.musicpark.domain.use_cases.GetAlbumDetailUseCase
import com.swayy.musicpark.presentation.screens.posts.PostListState
import com.swayy.musicpark.presentation.screens.tracksDetails.TrackDetailState
import com.swayy.musicpark.util.Constants
import com.swayy.musicpark.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumDetailViewModel @Inject constructor(
    private val getAlbumDetailUseCase: GetAlbumDetailUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _state = mutableStateOf(AlbumDetailListState())
    val state:State<AlbumDetailListState> = _state

    init {
        savedStateHandle.get<String>(Constants.ALBUM_ID)?.let { postId ->
            getAlbumDetails(postId)
        }

    }


    private fun getAlbumDetails(id: String) {
        viewModelScope.launch {
            getAlbumDetailUseCase.invoke(id).collect { result ->
                when (result) {

                    is Resource.Success -> {
                        _state.value =
                            AlbumDetailListState(album = result.data ?: emptyList())
                    }

                    is Resource.Error -> {
                        Log.d("DETAILS:: ", result.message.toString())
                    }
                    is Resource.Loading -> {
                        _state.value = AlbumDetailListState(isLoading = true)
                    }
                }
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