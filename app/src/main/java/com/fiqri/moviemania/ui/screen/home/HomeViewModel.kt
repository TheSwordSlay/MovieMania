package com.fiqri.moviemania.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fiqri.moviemania.data.MovieRepository
import com.fiqri.moviemania.model.OrderMovie
import com.fiqri.moviemania.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: MovieRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<OrderMovie>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<OrderMovie>>>
        get() = _uiState

    fun getAllMovies() {
        viewModelScope.launch {
            repository.getAllMovies()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderMovies ->
                    _uiState.value = UiState.Success(orderMovies)
                }

        }
    }

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String) {
        _query.value = newQuery
        viewModelScope.launch {
            repository.searchMovie(_query.value)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderMovies ->
                    _uiState.value = UiState.Success(orderMovies)
                }

        }
    }
}