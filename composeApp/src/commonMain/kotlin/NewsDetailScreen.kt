package org.example.project


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.coil3.CoilImage
import navigation.NewsDetailScreenComponentt
import network.model.Article

@Composable
fun NewsDetailScreen(
    article: Article,
    component: NewsDetailScreenComponentt
) {
    Column(modifier = Modifier.fillMaxSize()) {


        LazyColumn(
            modifier = Modifier.fillMaxWidth(),


            ) {
            item {
                CoilImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(248.dp)
                        .clip(MaterialTheme.shapes.medium),
                    imageModel = { article.urlToImage },
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = article.title!!,
                    style = MaterialTheme.typography.body1,

                    )
                Text(
                    text = article.content!!,
                    style = MaterialTheme.typography.body2,
                )
            }
        }
    }


}