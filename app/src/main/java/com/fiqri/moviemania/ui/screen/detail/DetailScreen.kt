package com.fiqri.moviemania.ui.screen.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.fiqri.moviemania.di.Injection
import com.fiqri.moviemania.ui.ViewModelFactory
import com.fiqri.moviemania.ui.common.UiState

@Composable
fun DetailScreen(
    movieId: Long,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateBack: () -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getMovieById(movieId)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    data.movie.name,
                    data.movie.photoUrl,
                    data.movie.desc,
                    data.movie.genre,
                    data.movie.runtime,
                    data.movie.release,
                    onBackClick = navigateBack,
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun DetailContent(
    name: String,
    photoUrl: String,
    desc: String,
    genre: String,
    runtime: String,
    releaseDate: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column (modifier = Modifier.verticalScroll(rememberScrollState())) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Tombol kembali",
            modifier = Modifier.padding(16.dp).clickable { onBackClick() }
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.clickable {}
        ) {
            AsyncImage(
                model = photoUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(142.dp)
                    .height(220.dp)
                    .padding(start = 10.dp)
            )
            Column () {
                Text(
                    text = name,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 16.dp, start = 16.dp, end = 10.dp)
                )

                Text(
                    text = "Genre",
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 10.dp),
                    fontSize = 15.sp
                )

                Text(
                    text = genre,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier
                        .padding(start = 16.dp, bottom = 10.dp),
                    fontSize = 15.sp
                )

                Text(
                    text = "Runtime",
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .padding(start = 16.dp),
                    fontSize = 15.sp
                )

                Text(
                    text = runtime,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier
                        .padding(start = 16.dp, bottom = 10.dp),
                    fontSize = 15.sp
                )

                Text(
                    text = "Release date",
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .padding(start = 16.dp),
                    fontSize = 15.sp
                )

                Text(
                    text = releaseDate,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier
                        .padding(start = 16.dp, bottom = 10.dp),
                    fontSize = 15.sp
                )
            }


        }

        Text(
            text = "Synopsis",
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .padding(start = 16.dp, bottom = 10.dp, end = 16.dp, top = 16.dp)
        )

        Text(
            text = desc,
            fontWeight = FontWeight.Light,
            modifier = Modifier
                .padding(start = 16.dp, bottom = 10.dp, end = 16.dp)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailPreview() {
    com.fiqri.moviemania.ui.theme.MovieManiaTheme {
        DetailContent(
            name = "THE HUNGER GAMES: THE BALLAD OF SONGBIRDS & SNAKES",
            photoUrl = "https://resizing.flixster.com/ELzEGuXyU443d5jXoJqzl5CF57k=/fit-in/180x240/v2/https://resizing.flixster.com/cli2xj07iMBQ-KDgwpiVxGPlt0I=/ems.cHJkLWVtcy1hc3NldHMvbW92aWVzL2I4MDI3YjY5LTY1OGEtNDgzYi1iMzIxLWE1MWRkMTdlMzU1Ni5qcGc=",
            desc = "Experience the story of THE HUNGER GAMES -- 64 years before Katniss Everdeen volunteered as tribute, and decades before Coriolanus Snow became the tyrannical President of Panem. THE HUNGER GAMES: THE BALLAD OF SONGBIRDS & SNAKES follows a young Coriolanus (Tom Blyth) who is the last hope for his failing lineage, the once-proud Snow family that has fallen from grace in a post-war Capitol. With his livelihood threatened, Snow is reluctantly assigned to mentor Lucy Gray Baird (Rachel Zegler), a tribute from the impoverished District 12. But after Lucy Gray's charm captivates the audience of Panem, Snow sees an opportunity to shift their fates. With everything he has worked for hanging in the balance, Snow unites with Lucy Gray to turn the odds in their favor. Battling his instincts for both good and evil, Snow sets out on a race against time to survive and reveal if he will ultimately become a songbird or a snake.",
            genre = "Action, Adventure, Sci-fi",
            runtime = "2h 37m",
            releaseDate = "Nov 17, 2023",
            onBackClick = {}
        )
    }
}