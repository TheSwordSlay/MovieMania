package com.fiqri.moviemania.ui.screen.home

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fiqri.moviemania.di.Injection
import com.fiqri.moviemania.model.OrderMovie
import com.fiqri.moviemania.ui.ViewModelFactory
import com.fiqri.moviemania.ui.common.UiState
import com.fiqri.moviemania.ui.components.MovieListItem
import com.fiqri.moviemania.ui.components.SearchBar

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
) {
    val query by viewModel.query
    Column() {
        SearchBar(
            query = query,
            onQueryChange = viewModel::search
        )
        viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    viewModel.getAllMovies()
                }
                is UiState.Success -> {
                    HomeContent(
                        orderMovie = uiState.data,
                        modifier = modifier,
                        navigateToDetail = navigateToDetail,
                    )
                }
                is UiState.Error -> {}
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContent(
    orderMovie: List<OrderMovie>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {
    Box(modifier = modifier) {
        LazyColumn {
            items(orderMovie, key = { it.movie.id }) { order ->
                MovieListItem(
                    name = order.movie.name,
                    photoUrl = order.movie.photoUrl,
                    desc = order.movie.desc,
                    modifier = Modifier
                        .clickable {
                            navigateToDetail(order.movie.id)
                        }
                        .animateItemPlacement(tween(durationMillis = 100))
                )
            }
        }
    }
}