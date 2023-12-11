package app.id.crypick.features.feeds

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.compose.koinInject

@Composable
fun FeedsScreen(
    screenModel: FeedsScreenModel = koinInject()
) {
    Scaffold {
        Column(
            modifier = Modifier.padding(it).fillMaxSize()
        ) {
            if (screenModel.uiState.loading) {
                CircularProgressIndicator()
            }
            Text("${screenModel.uiState.apiResult}", maxLines = 5)
            Button(onClick = {
                screenModel.updateValue()
            }) {
                Text("Update")
            }
        }
    }
}