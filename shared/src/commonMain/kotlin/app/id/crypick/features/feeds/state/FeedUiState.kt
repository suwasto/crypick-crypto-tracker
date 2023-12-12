package app.id.crypick.features.feeds.state

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import app.id.crypick.utils.UiState

@Stable
interface FeedsUiState {
    val newsState: UiState<String>
    val headlinesState: UiState<String>
}

class MutableFeedsUiState : FeedsUiState {
    override var newsState: UiState<String> by mutableStateOf(UiState())
    override var headlinesState: UiState<String> by mutableStateOf(UiState())
}