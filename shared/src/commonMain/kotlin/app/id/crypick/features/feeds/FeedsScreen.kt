package app.id.crypick.features.feeds

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.compose.koinInject

@Composable
fun FeedsScreen(
    screenModel: HomeScreenModel = koinInject()
) {
    Scaffold {
        Column(
            modifier = Modifier.padding(it).fillMaxSize()
        ) {
            Text("FEEDS")
        }
    }
}