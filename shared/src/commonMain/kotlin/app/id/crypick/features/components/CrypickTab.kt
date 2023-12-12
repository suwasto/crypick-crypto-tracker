package app.id.crypick.features.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import app.id.crypick.features.feeds.FeedsScreen
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

internal sealed class CrypickTab {
    internal object FeedTab : Tab {
        @Composable
        override fun Content() {
            FeedsScreen()
        }

        @OptIn(ExperimentalResourceApi::class)
        override val options: TabOptions
            @Composable
            get() {
                val title = "Feeds"
                val icon = painterResource("news_outlined.xml")
                return remember {
                    TabOptions(
                        index = 0u,
                        title = title,
                        icon = icon,
                    )
                }
            }
    }

    internal object CoinTab : Tab {
        @Composable
        override fun Content() {
            FeedsScreen()
        }

        @OptIn(ExperimentalResourceApi::class)
        override val options: TabOptions
            @Composable
            get() {
                val title = "Coins"
                val icon = painterResource("coin_outlined.xml")
                return remember {
                    TabOptions(
                        index = 1u,
                        title = title,
                        icon = icon,
                    )
                }
            }
    }

    internal object BookmarksTab : Tab {
        @Composable
        override fun Content() {
            FeedsScreen()
        }

        @OptIn(ExperimentalResourceApi::class)
        override val options: TabOptions
            @Composable
            get() {
                val title = "Bookmarks"
                val icon = painterResource("bookmark_outlined.xml")
                return remember {
                    TabOptions(
                        index = 2u,
                        title = title,
                        icon = icon,
                    )
                }
            }
    }

    internal object SettingsTab : Tab {
        @Composable
        override fun Content() {
            FeedsScreen()
        }

        @OptIn(ExperimentalResourceApi::class)
        override val options: TabOptions
            @Composable
            get() {
                val title = "Settings"
                val icon = painterResource("settings_outlined.xml")
                return remember {
                    TabOptions(
                        index = 3u,
                        title = title,
                        icon = icon,
                    )
                }
            }
    }

}

@Composable
@OptIn(ExperimentalResourceApi::class)
fun FilledIcon(item: Tab) = when (item.options.index) {
    (0u).toUShort() -> painterResource("news_filled.xml")
    (1u).toUShort() -> painterResource("coin_filled.xml")
    (2u).toUShort() -> painterResource("bookmark_filled.xml")
    (3u).toUShort() -> painterResource("settings_filled.xml")
    else -> painterResource("news_filled.xml")
}
