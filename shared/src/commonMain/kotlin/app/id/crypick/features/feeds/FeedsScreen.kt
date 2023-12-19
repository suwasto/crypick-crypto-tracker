package app.id.crypick.features.feeds

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import app.id.crypick.LocalAppNavigator
import app.id.crypick.domain.model.News
import app.id.crypick.domain.model.newsSaver
import app.id.crypick.features.components.TwoPanelScaffold
import app.id.crypick.features.components.TwoPanelScaffoldAnimationSpec
import app.id.crypick.features.feeds.component.FeedsHeadline
import app.id.crypick.features.feeds.component.NewsItem
import app.id.crypick.features.feeds.component.NewsLoading
import app.id.crypick.features.feeds.detail.FeedsDetail
import app.id.crypick.features.feeds.detail.FeedsDetailScreen
import app.id.crypick.features.feeds.state.FeedsUiState
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.compose.koinInject

@Composable
fun FeedsScreen(
    screenModel: FeedsScreenModel = koinInject()
) {
    FeedsScreenStateless(screenModel.uiState)
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
private fun FeedsScreenStateless(
    uiState: FeedsUiState
) {
    val windowSizeClass = calculateWindowSizeClass()
    val useCompactScreen = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact
    val detail = rememberSaveable(stateSaver = newsSaver) {
        mutableStateOf(News("", "", "", "", "", "", ""))
    }
    var showPanel: Boolean by rememberSaveable {
        mutableStateOf(false)
    }
    val navigator = LocalAppNavigator.currentOrThrow
    TwoPanelScaffold(
        modifier = Modifier.fillMaxSize().background(
            MaterialTheme.colorScheme.background
        ),
        panelVisibility = showPanel && useCompactScreen.not(),
        animationSpec = TwoPanelScaffoldAnimationSpec(
            finishedListener = { fraction -> if (fraction == 1f) showPanel = false }
        ),
        body = {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Feeds(
                    uiState = uiState
                ) {
                    if (useCompactScreen) {
                        navigator.push(FeedsDetailScreen(it))
                        return@Feeds
                    }
                    detail.value = it
                    showPanel = true
                }
            }
        },
        panel = {
            FeedsDetail(
                onBack = {
                    showPanel = false
                },
                news = detail.value
            )

        }
    )
}


@Composable
private fun Feeds(
    uiState: FeedsUiState,
    onSelectedNews: (News) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(bottom = 16.dp),
        columns = GridCells.Adaptive(250.dp)
    ) {
        item(span = { GridItemSpan(this.maxLineSpan) }) {
            FeedsHeadline(
                uiState = uiState.headlinesState,
                onSelectedNews = onSelectedNews
            )
        }
        item(span = { GridItemSpan(this.maxLineSpan) }) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                text = "News",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
        }
        if (uiState.newsState.loading) {
            items(5) {
                NewsLoading()
            }
        } else {
            uiState.newsState.data?.let {
                items(it) { news ->
                    NewsItem(news, onSelectedNews = { selectedNews ->
                        onSelectedNews(selectedNews)
                    })
                }
            }
        }
    }
}
