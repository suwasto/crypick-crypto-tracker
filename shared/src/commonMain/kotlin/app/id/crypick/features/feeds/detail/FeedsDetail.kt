package app.id.crypick.features.feeds.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import app.id.crypick.LocalAppNavigator
import app.id.crypick.domain.model.News
import app.id.crypick.utils.BackHandler
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.seiko.imageloader.rememberImagePainter

data class FeedsDetailScreen(
    val news: News
) : Screen {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    @Composable
    override fun Content() {
        val windowSizeClass = calculateWindowSizeClass()
        val navigatior = LocalAppNavigator.currentOrThrow
        if (windowSizeClass.widthSizeClass != WindowWidthSizeClass.Compact) {
            navigatior.pop()
        }
        FeedsDetail(
            news = news
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedsDetail(
    modifier: Modifier = Modifier,
    onBack: (() -> Unit)? = null,
    news: News
) {
    val uriHandler = LocalUriHandler.current
    val scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    onBack?.let { BackHandler(isEnabled = true, it) }
    val navigator = LocalNavigator.currentOrThrow
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(
                        text = news.title,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                scrollBehavior = scrollBehavior,
                navigationIcon = {
                    IconButton(onClick = {
                        if (onBack != null) {
                            onBack()
                        } else {
                            navigator.pop()
                        }
                    }) {
                        Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            val painter = rememberImagePainter(news.imgUrl)
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painter,
                contentDescription = "news image"
            )
            Text(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp),
                text = news.description,
                style = MaterialTheme.typography.bodyLarge
            )
            Row(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp)) {
                Text(text = "${news.author} - ${news.publishedAt}", style = MaterialTheme.typography.bodySmall)
            }
            Text(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp),
                text = news.content,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp).clickable {
                    uriHandler.openUri(news.url)
                },
                text = news.url,
                color = Color.Blue
            )
        }
    }
}
