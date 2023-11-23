package com.fiqri.moviemania.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fiqri.moviemania.data.MovieRepository
import com.fiqri.moviemania.model.OrderMovie
import com.fiqri.moviemania.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: MovieRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<OrderMovie>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<OrderMovie>>
        get() = _uiState

    fun getMovieById(movieId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getOrderMovieById(movieId))
        }
    }
}