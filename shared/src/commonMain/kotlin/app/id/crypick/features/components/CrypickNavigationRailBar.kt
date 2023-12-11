package app.id.crypick.features.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import io.ktor.websocket.Frame

@Composable
fun CrypickNavigationRailBar(
    modifier: Modifier = Modifier,
    tabNavigator: TabNavigator,
    navRailItems: List<Tab>,
) {
    NavigationRail(
        modifier = modifier.fillMaxHeight().alpha(0.95F),
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
    ) {
        navRailItems.forEach { item ->
            val isSelected = tabNavigator.current == item
            NavigationRailItem(
                modifier = Modifier.padding(vertical = 12.dp),
                icon = {
                    item.options.icon?.let {
                        Icon(
                            painter = it,
                            contentDescription = item.options.title,
                        )
                    }
                },
                label = { Frame.Text(text = item.options.title) },
                alwaysShowLabel = true,
                selected = tabNavigator.current == item,
                onClick = { tabNavigator.current = item },
            )
        }
    }
}
