package app.id.crypick.features.feeds.detail

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.id.crypick.LocalAppNavigator
import app.id.crypick.domain.model.News
import app.id.crypick.utils.BackHandler
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

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
    val scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    onBack?.let { BackHandler(isEnabled = true, it) }
    val navigator = LocalNavigator.currentOrThrow
    Scaffold(
        modifier = modifier,
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(news.title)
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
    }
}
