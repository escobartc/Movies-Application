package com.example.moviesapplication.ui.view.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.example.moviesapplication.R
import com.example.moviesapplication.domain.Constants.IMAGE_BASE
import com.example.moviesapplication.domain.model.movie.KnownFor
import com.example.moviesapplication.ui.viewmodel.HomeViewModel


@Composable
fun ProfileScreen(viewModel: HomeViewModel) {
    val actor = viewModel.actor.observeAsState()
    val movies = actor.value?.results?.first()?.knownFor ?: emptyList()
    val loading = viewModel.loading.observeAsState(initial = false)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        if (loading.value) {
            CircularProgressIndicator()
        } else {
            actor.value?.results?.maxByOrNull { it.popularity }?.let {
                val actor = it
                PersonCard(
                    actor.profilePath!!,
                    actor.name,
                    actor.knownForDepartment,
                    actor.popularity
                )
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.DarkGray,
                    thickness = 1.dp
                )

                MovieList(movies = movies)
            }
        }
    }
}

@Composable
fun MovieList(movies: List<KnownFor>) {
    LazyColumn(contentPadding = PaddingValues(bottom = 48.dp)) {
        item {
            Text(
                modifier = Modifier.padding(top = 12.dp, start = 12.dp),
                text = stringResource(id = R.string.featured_movies),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Black
            )
        }
        items(count = movies.size) { index ->
            val movie = movies[index]
            MovieCard(movie = movie)
        }
    }
}

@Composable
fun PersonCard(imageUrl: String, name: String, profession: String, rating: Double) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(12.dp), elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        ConstraintLayout {
            val (image, nameText, professionText, ratingText) = createRefs()

            ImageFromUrl(
                url = IMAGE_BASE + imageUrl,
                modifier = Modifier
                    .width(120.dp)
                    .fillMaxHeight()
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(nameText.start)
                    }
            )
            Text(
                modifier = Modifier.constrainAs(nameText) {
                    top.linkTo(parent.top, margin = 16.dp)
                    bottom.linkTo(professionText.top, margin = 20.dp)
                    start.linkTo(image.end, margin = 64.dp)
                    end.linkTo(parent.end)
                },
                text = name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Black
            )
            Text(modifier = Modifier.constrainAs(professionText) {
                top.linkTo(nameText.bottom)
                bottom.linkTo(ratingText.top, margin = 20.dp)
                start.linkTo(nameText.start)
                end.linkTo(nameText.end)
            }, text = profession, style = MaterialTheme.typography.titleMedium)
            StarRating(rating = rating, person = true, modifier = Modifier.constrainAs(ratingText) {
                top.linkTo(professionText.bottom)
                bottom.linkTo(parent.bottom, margin = 16.dp)
                start.linkTo(nameText.start)
                end.linkTo(nameText.end)
            })

        }
    }
}

@Composable
fun MovieCard(movie: KnownFor) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(12.dp), elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        ConstraintLayout {
            val (image, nameText, overviewText, ratingText) = createRefs()

            ImageFromUrl(
                url = IMAGE_BASE + movie.posterPath,
                modifier = Modifier
                    .width(120.dp)
                    .fillMaxHeight()
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(overviewText.start)
                    }
            )
            movie.title?.let {
                Text(
                    modifier = Modifier.constrainAs(nameText) {
                        top.linkTo(parent.top, margin = 16.dp)
                        bottom.linkTo(overviewText.top, margin = 12.dp)
                        start.linkTo(image.end)
                        end.linkTo(parent.end)
                    },
                    text = it,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Black
                )
            }
            Text(
                modifier = Modifier
                    .width(250.dp)
                    .constrainAs(overviewText) {
                        top.linkTo(nameText.bottom)
                        bottom.linkTo(ratingText.top, margin = 12.dp)
                        start.linkTo(image.end, margin = 8.dp)
                        end.linkTo(parent.end, margin = 8.dp)
                    },
                text = movie.overview,
                style = MaterialTheme.typography.bodyMedium,
                overflow = TextOverflow.Ellipsis,
                maxLines = 3,
            )
            StarRating(
                rating = movie.voteAverage,
                person = false,
                modifier = Modifier.constrainAs(ratingText) {
                    top.linkTo(overviewText.bottom)
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                    start.linkTo(nameText.start)
                    end.linkTo(nameText.end)
                })

        }
    }
}

@Composable
fun ImageFromUrl(url: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model = url,
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}

@Composable
fun StarRating(rating: Double, modifier: Modifier = Modifier, person: Boolean) {
    Row(modifier = modifier) {
        if (person) {
            Image(
                painter = painterResource(id = R.drawable.popularity),
                contentDescription = "Star Icon",
                modifier = Modifier.size(24.dp)
            )
        } else {
            Icon(
                imageVector = Icons.Filled.Star,
                modifier = Modifier.size(24.dp),
                tint = Color.Yellow,
                contentDescription = "Star"
            )
        }
        // Numeric value
        Text(
            text = rating.toString(),
            fontSize = 20.sp,
            modifier = if (person) Modifier.padding(start = 4.dp, top = 6.dp) else Modifier.padding(
                start = 4.dp
            )
        )
    }
}
