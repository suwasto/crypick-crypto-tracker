package app.id.crypick.features.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.id.crypick.features.components.CrypickNavigationRailBar
import app.id.crypick.features.components.CrypickTab
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

class MainScreen : Screen {

    @Composable
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun Content() {
        val windowSizeClass = calculateWindowSizeClass()
        val useNavRail = windowSizeClass.widthSizeClass > WindowWidthSizeClass.Compact
        TabNavigator(
            CrypickTab.FeedTab,
        ) {
            if (useNavRail) {
                Row {
                    CrypickNavigationRailBar(
                        tabNavigator = it,
                        navRailItems = listOf(
                            CrypickTab.FeedTab,
                            CrypickTab.OtherTab,
                        ),
                    )
                    CurrentScreen()
                }
            } else {
                Scaffold(
                    content = { innerPadding ->
                        Box(
                            modifier = Modifier
                                .padding(innerPadding),
                        ) {
                            CurrentScreen()
                        }
                    },
                    bottomBar = {
                        BottomNavigation(
                            backgroundColor = MaterialTheme.colorScheme.background,
                        ) {
                            TabNavigationItem(CrypickTab.FeedTab)
                            TabNavigationItem(CrypickTab.OtherTab)
                        }
                    },
                )
            }
        }
    }
}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current
    val isSelected = tabNavigator.current == tab

    BottomNavigationItem(
        modifier = Modifier.offset(
            x = when (tab.options.index) {
                (0u).toUShort() -> 0.dp
                (1u).toUShort() -> (-24).dp
                (2u).toUShort() -> 24.dp
                (3u).toUShort() -> 0.dp
                else -> 0.dp
            },
        ),
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        icon = {
            tab.options.icon?.let {
                Icon(
                    painter = it,
                    contentDescription = tab.options.title,
                    tint = if (isSelected) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.onBackground
                    },
                )
            }
        },
    )
}
