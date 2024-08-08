package com.example.moviesapplication.ui.view.movies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.moviesapplication.R
import com.example.moviesapplication.domain.Constants
import com.example.moviesapplication.domain.model.movie.Movie
import com.example.moviesapplication.ui.view.home.ImageFromUrl
import com.example.moviesapplication.ui.view.home.StarRating
import com.example.moviesapplication.ui.viewmodel.DashboardViewModel

@Composable
fun MoviesScreen(dashBoardViewModel: DashboardViewModel) {
    val popularMovies = dashBoardViewModel.popularMovies.observeAsState().value
    val topRatedMovies = dashBoardViewModel.topRatedMovies.observeAsState().value
    val recommendedMovies = dashBoardViewModel.recommendedMovies.observeAsState().value

    Column(Modifier.fillMaxSize()) {
        if (popularMovies != null ) {
            Text(
                modifier = Modifier.padding(top = 12.dp, start = 12.dp),
                text = stringResource(id = R.string.popular_movies),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Black
            )
            MovieHorizontalList(popularMovies.results)
        } else {

        }
        if (topRatedMovies != null) {
            Text(
                modifier = Modifier.padding(top = 12.dp, start = 12.dp),
                text = stringResource(id = R.string.top_movies),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Black
            )
            MovieHorizontalList(topRatedMovies.results)
        } else {

        }
        if (recommendedMovies != null) {
            Text(
                modifier = Modifier.padding(top = 12.dp, start = 12.dp),
                text = stringResource(id = R.string.recommended_movies),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Black
            )
            MovieHorizontalList(recommendedMovies.results)
        } else {

        }
    }
}


@Composable
fun MovieHorizontalList(movies: List<Movie>) {
    LazyRow(contentPadding = PaddingValues(end = 48.dp)) {
        items(count = movies.size) { index ->
            val movie = movies[index]
            MovieCard(movie = movie)
        }
    }
}

@Composable
fun MovieCard(movie: Movie) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(12.dp), elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFf6f6f3))
    ) {
        ConstraintLayout {
            val (image, nameText, overviewText, ratingText) = createRefs()

            ImageFromUrl(
                url = Constants.IMAGE_BASE + movie.posterPath,
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
            Text(
                modifier = Modifier.constrainAs(nameText) {
                    top.linkTo(parent.top, margin = 16.dp)
                    bottom.linkTo(overviewText.top, margin = 12.dp)
                    start.linkTo(image.end)
                    end.linkTo(parent.end)
                },
                text = movie.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Black
            )
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

