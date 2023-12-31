package app.id.crypick.features.feeds.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.id.crypick.domain.model.News
import app.id.crypick.features.components.ShimmerContainer
import app.id.crypick.utils.UiState
import com.seiko.imageloader.rememberImagePainter

@Composable
fun NewsItem(
    news: News,
    onSelectedNews: (News) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth().clickable {
            onSelectedNews(news)
        },
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        NewsItemContent(news = news)
    }
}

@Composable
private fun NewsItemContent(
    modifier: Modifier = Modifier,
    news: News
) {
    val painter = rememberImagePainter(news.imgUrl)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            modifier = Modifier.size(100.dp).clip(RoundedCornerShape(8.dp)),
            painter = painter,
            contentDescription = "thumbnail",
            contentScale = ContentScale.Crop
        )
        Box(modifier = Modifier.weight(1f).height(100.dp).padding(top = 2.dp, bottom = 2.dp)) {
            Column {
                Text(
                    text = news.title,
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 16.sp,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Text(
                text = "${news.author}, ${news.publishedAt}",
                modifier = Modifier.fillMaxWidth().align(
                    Alignment.BottomStart
                ),
                fontSize = 12.sp,
                maxLines = 1
            )
        }
    }
}

@Composable
fun NewsLoading(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ShimmerContainer(modifier = Modifier.size(100.dp).clip(RoundedCornerShape(8.dp)))
        Box(modifier = Modifier.weight(1f).height(100.dp).padding(top = 2.dp, bottom = 2.dp)) {
            Column {
                ShimmerContainer(
                    modifier = Modifier.fillMaxWidth().height(18.dp).clip(
                        RoundedCornerShape(8.dp)
                    )
                )
                Spacer(modifier = Modifier.height(2.dp))
                ShimmerContainer(
                    modifier = Modifier.fillMaxWidth().height(18.dp).clip(
                        RoundedCornerShape(8.dp)
                    )
                )
            }
            ShimmerContainer(
                modifier = Modifier.fillMaxWidth(0.5f).height(14.dp).align(Alignment.BottomStart)
                    .clip(
                        RoundedCornerShape(8.dp)
                    )
            )
        }
    }
}