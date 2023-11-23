package com.fiqri.moviemania.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun MovieListItem(
    name: String,
    photoUrl: String,
    desc: String,
    modifier: Modifier = Modifier
) {
    Card (
        shape = MaterialTheme.shapes.medium,
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = photoUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(97.dp)
                    .height(200.dp)
            )
            Column () {
                Text(
                    text = name,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 16.dp, start = 16.dp)
                )
                Text(
                    text = desc.substring(0, 100) + "...",
                    fontWeight = FontWeight.Light,
                    modifier = Modifier
                        .padding(start = 16.dp, bottom = 10.dp, end = 10.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieListItemPreview() {
    com.fiqri.moviemania.ui.theme.MovieManiaTheme {
        MovieListItem(
            name = "THE HUNGER GAMES: THE BALLAD OF SONGBIRDS & SNAKES",
            photoUrl = "https://resizing.flixster.com/ELzEGuXyU443d5jXoJqzl5CF57k=/fit-in/180x240/v2/https://resizing.flixster.com/cli2xj07iMBQ-KDgwpiVxGPlt0I=/ems.cHJkLWVtcy1hc3NldHMvbW92aWVzL2I4MDI3YjY5LTY1OGEtNDgzYi1iMzIxLWE1MWRkMTdlMzU1Ni5qcGc=",
            desc = "Experience the story of THE HUNGER GAMES -- 64 years before Katniss Everdeen volunteered as tribute, and decades before Coriolanus Snow became the tyrannical President of Panem. THE HUNGER GAMES: THE BALLAD OF SONGBIRDS & SNAKES follows a young Coriolanus (Tom Blyth) who is the last hope for his failing lineage, the once-proud Snow family that has fallen from grace in a post-war Capitol. With his livelihood threatened, Snow is reluctantly assigned to mentor Lucy Gray Baird (Rachel Zegler), a tribute from the impoverished District 12. But after Lucy Gray's charm captivates the audience of Panem, Snow sees an opportunity to shift their fates. With everything he has worked for hanging in the balance, Snow unites with Lucy Gray to turn the odds in their favor. Battling his instincts for both good and evil, Snow sets out on a race against time to survive and reveal if he will ultimately become a songbird or a snake."
        )
    }
}