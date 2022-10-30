package com.swayy.musicpark.presentation.screens.postdetails

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.swayy.musicpark.domain.use_cases.GetPostDetailsUseCase
import com.swayy.musicpark.presentation.screens.artist.ArtistListState
import com.swayy.musicpark.util.Constants
import com.swayy.musicpark.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PostDetailsViewModel @Inject constructor(
    private val getPostDetailsUseCase: GetPostDetailsUseCase,
    savedStateHandle: SavedStateHandle
):ViewModel(){
    private val _state = mutableStateOf(PostDetailsListState())
    val state:State<PostDetailsListState> = _state

    init {
        savedStateHandle.get<String>(Constants.POST_ID)?.let { postId ->
            getPostDetails(postId)
        }

    }

    private fun getPostDetails(id:String){
        getPostDetailsUseCase(id).onEach { result ->
            when(result){
                is Resource.Success -> {
                    _state.value = PostDetailsListState(postDetails = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = PostDetailsListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = PostDetailsListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }



}