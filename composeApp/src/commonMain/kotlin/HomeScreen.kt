package org.example.project


import MovieSDK
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.coil3.CoilImage
import navigation.HomeScreenComponent
import navigation.HomeScreenEvent

import network.model.Article
import network.model.NewsResponse

@Composable
fun HomeScreen(component: HomeScreenComponent) {
    val movieSDK: MovieSDK = MovieSDK()
    var newsResponse1 by remember { mutableStateOf(NewsResponse()) }

    LaunchedEffect(true) {

        kotlin.runCatching {
            movieSDK.getPopularMovies()
        }.onSuccess { newsResponse ->
            //Toast.makeText(this@MainActivity, "Movie list fetched", Toast.LENGTH_LONG).show()
            // Log.i("TestSuccess", newsResponse.toString())
            newsResponse1 = newsResponse

        }.onFailure {
            // Toast.makeText(this@MainActivity, it.localizedMessage, Toast.LENGTH_SHORT).show()
            //Log.i("Test", it.localizedMessage)
        }


    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.primary
    ) {
        Scaffold(
            topBar = {
                TopAppBar(backgroundColor = Color.Blue) {
                    Row {
                        Text(
                            text = "News App",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    }
                }
            },
            content = {
                newsResponse1.articles?.let { it1 ->
                    GridView(
                        articleList = it1,
                        modifier = Modifier.padding(it),
                        component = component
                    )
                }
            }
        )
    }

}


@Composable
fun GridView(
    articleList: List<Article?>,
    modifier: Modifier = Modifier,
    component: HomeScreenComponent
) {

    LazyVerticalGrid(modifier = modifier.padding(0.dp),
        columns = GridCells.Fixed(1),
        content = {
            items(articleList) { article ->
                ArticleCard(
                    modifier = Modifier,
                    article = article,
                    onClick = {
                        component.onEvent(HomeScreenEvent.OnUpdateArticle(article!!))
                        component.onEvent(HomeScreenEvent.OnNewsItemClicked(article = article))
                    }
                )
            }
        })
}

@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    article: Article?,
    onClick: () -> Unit,
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = onClick),
        elevation = 0.dp,
        backgroundColor = Color.Blue
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            CoilImage(
                modifier = Modifier
                    .size(80.dp, 80.dp)
                    .clip(RoundedCornerShape(16.dp)),
                imageModel = { article?.urlToImage },
            )



            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                Text(
                    text = article?.title!!,
                    modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                    color = MaterialTheme.colors.surface,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.subtitle1
                )
                Spacer(modifier = Modifier.height(8.dp))


            }

        }
    }
}