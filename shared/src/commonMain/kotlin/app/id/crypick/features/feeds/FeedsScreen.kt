package app.id.crypick.features.feeds

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import app.id.crypick.features.components.CrypickLoading
import org.koin.compose.koinInject

@Composable
fun FeedsScreen(
    screenModel: FeedsScreenModel = koinInject()
) {
    Scaffold {
        Box(
            modifier = Modifier.padding(it).fillMaxSize()
        ) {
            CrypickLoading(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}