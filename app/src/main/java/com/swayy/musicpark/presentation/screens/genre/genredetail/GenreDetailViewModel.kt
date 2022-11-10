package com.swayy.musicpark.presentation.screens.genre.genredetail

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swayy.musicpark.domain.use_cases.GetGenreDetailsUseCase
import com.swayy.musicpark.presentation.screens.genre.GenreListState
import com.swayy.musicpark.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenreDetailViewModel @Inject constructor(
    private val getGenreDetailsUseCase: GetGenreDetailsUseCase
) : ViewModel() {
    private val _state = mutableStateOf(GenreDetailListState())
    val state: State<GenreDetailListState> = _state

    private fun getGenreDetail(id: String) {
        viewModelScope.launch {
            getGenreDetailsUseCase.invoke(id).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value =
                            GenreDetailListState(genre = result.data ?: emptyList())
                    }
                    is Resource.Error -> {
                        Log.d("DETAILS:: ", result.message.toString())
                    }
                    is Resource.Loading -> {
                        _state.value = GenreDetailListState(isLoading = true)
                    }
                }
            }
        }
    }
}