package com.swayy.musicpark.presentation.screens.posts

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swayy.musicpark.data.remote.dto.posts.Post
import com.swayy.musicpark.domain.use_cases.GetPostsUseCase
import com.swayy.musicpark.presentation.screens.tracks.TrackListState
import com.swayy.musicpark.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
) : ViewModel() {
    private val _state = mutableStateOf(PostListState())
    val state:State<PostListState> = _state


    init {
        getPosts()
    }

    private fun getPosts(){
        getPostsUseCase().onEach { result ->
            when(result){
                is Resource.Success -> {
                    _state.value = PostListState(posts = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = PostListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = PostListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}