package com.swayy.musicpark.presentation.screens.artist

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swayy.musicpark.domain.use_cases.GetTopUseCase
import com.swayy.musicpark.presentation.screens.postdetails.PlaylistLIstState
import com.swayy.musicpark.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopViewModel @Inject constructor(
    private val getTopUseCase: GetTopUseCase
) : ViewModel() {

    private val _state = mutableStateOf(TopListState())
    val state: State<TopListState> = _state


    fun getTop(id: String) {
        viewModelScope.launch {
            getTopUseCase.invoke(id).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value =
                            TopListState(top = result.data ?: emptyList())
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