package app.id.crypick.features.feeds.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import app.id.crypick.features.components.ShimmerContainer
import app.id.crypick.utils.UiState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FeedsHeadline(uiState: UiState<String>) {
    val pagerState = rememberPagerState {
        if (uiState.loading) {
            1
        } else {
            2
        }
    }
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp, top = 16.dp),
            text = "Top Headlines",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        HorizontalPager(
            modifier = Modifier.wrapContentWidth().onSizeChanged {

            },
            state = pagerState,
            userScrollEnabled = uiState.loading.not()
        ) {
//            Box(Modifier.fillMaxWidth().aspectRatio(2f)) {
//
//            }
            FeedsHeadlineLoading()
        }
    }
}

@Composable
fun FeedsHeadlineLoading(
    modifier: Modifier = Modifier
) {
    ShimmerContainer(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
            .clip(RoundedCornerShape(8.dp))
            .aspectRatio(2f)
    )
}