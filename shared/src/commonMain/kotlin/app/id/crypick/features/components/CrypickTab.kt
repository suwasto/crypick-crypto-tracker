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
                val icon = painterResource("home_outlined.xml")
                return remember {
                    TabOptions(
                        index = 0u,
                        title = title,
                        icon = icon,
                    )
                }
            }
    }

    internal object OtherTab : Tab {
        @Composable
        override fun Content() {
            FeedsScreen()
        }

        @OptIn(ExperimentalResourceApi::class)
        override val options: TabOptions
            @Composable
            get() {
                val title = "Other"
                val icon = painterResource("home_outlined.xml")
                return remember {
                    TabOptions(
                        index = 0u,
                        title = title,
                        icon = icon,
                    )
                }
            }
    }

}