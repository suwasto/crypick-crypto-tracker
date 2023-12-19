package app.id.crypick.features.feeds.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.id.crypick.domain.model.News
import app.id.crypick.features.components.ShimmerContainer
import app.id.crypick.utils.UiState
import com.seiko.imageloader.rememberImagePainter

@Composable
fun FeedsHeadline(
    modifier: Modifier = Modifier,
    uiState: UiState<List<News>>,
    onSelectedNews: (News) -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp, top = 16.dp),
            text = "Top Headlines",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        BoxWithConstraints(
            modifier = Modifier.fillMaxWidth()
        ) {
            if (uiState.loading) {
                FeedsHeadlineLoading(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp)
                        .width(maxWidth)
                )
            } else {
                LazyRow(
                    contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    uiState.data?.let {
                        if (it.size == 1) {
                            items(it) { news ->
                                FeedsHeadlineItem(
                                    modifier = Modifier
                                        .width(maxWidth - 32.dp)
                                        .clickable {
                                            onSelectedNews(news)
                                        },
                                    news = news
                                )
                            }
                        } else {
                            items(it) { news ->
                                FeedsHeadlineItem(
                                    modifier = Modifier
                                        .width(maxWidth - (maxWidth * 0.2f))
                                        .clickable {
                                            onSelectedNews(news)
                                        },
                                    news = news
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FeedsHeadlineItem(modifier: Modifier = Modifier, news: News) {
    Card(
        modifier = modifier
            .aspectRatio(2f),
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            val painter = rememberImagePainter(news.imgUrl)
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painter,
                contentDescription = "image",
                contentScale = ContentScale.Crop
            )
            Box(modifier = Modifier.alpha(0.5f).fillMaxSize().background(Color.Black))
            Column(
                modifier = Modifier.align(Alignment.BottomStart).padding(16.dp)
            ) {
                Text(
                    text = news.title,
                    color = Color.White,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 16.sp
                )
                if (news.description.isNotEmpty()) {
                    Text(
                        text = news.description,
                        color = Color.White,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Composable
fun FeedsHeadlineLoading(
    modifier: Modifier = Modifier
) {
    ShimmerContainer(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .aspectRatio(2f)
    )
}