package app.id.crypick.features.feeds

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import app.id.crypick.features.feeds.component.FeedsHeadline
import app.id.crypick.features.feeds.component.FeedsNews
import app.id.crypick.features.feeds.state.FeedsUiState
import org.koin.compose.koinInject

@Composable
fun FeedsScreen(
    screenModel: FeedsScreenModel = koinInject()
) {
    LaunchedEffect(key1 = screenModel) {
//            screenModel.fetchNews()
    }
    FeedsScreenStateless(screenModel.uiState)
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
private fun FeedsScreenStateless(
    uiState: FeedsUiState
) {
    val windowSizeClass = calculateWindowSizeClass()
    val userMediumView = windowSizeClass.widthSizeClass > WindowWidthSizeClass.Compact
    Scaffold {
        Box(
            modifier = Modifier.padding(it).fillMaxSize()
        ) {
            if (userMediumView) {
                FeedsMedium(uiState)
            } else {
                FeedsCompact(uiState)
            }
        }
    }
}

@Composable
private fun FeedsCompact(uiState: FeedsUiState) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        item {
            FeedsHeadline(uiState.headlinesState)
        }
        item {
            Text(
                modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp, top = 16.dp),
                text = "News",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
        }
        items(5) {
            FeedsNews(uiState.newsState)
        }
    }
}

@Composable
private fun FeedsMedium(uiState: FeedsUiState) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(bottom = 16.dp),
        columns = GridCells.Adaptive(250.dp)
    ) {
        item(span = { GridItemSpan(this.maxLineSpan) }) {
            FeedsHeadline(uiState.headlinesState)
        }
        item(span = { GridItemSpan(this.maxLineSpan) }) {
            Text(
                modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp, top = 16.dp),
                text = "News",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
        }
        items(5) {
            FeedsNews(uiState.newsState)
        }
    }
}
