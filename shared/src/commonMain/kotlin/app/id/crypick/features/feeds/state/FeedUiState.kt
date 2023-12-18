package app.id.crypick.features.feeds.state

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import app.id.crypick.domain.model.News
import app.id.crypick.utils.UiState

@Stable
interface FeedsUiState {
    val newsState: UiState<List<News>>
    val headlinesState: UiState<List<News>>
}

class MutableFeedsUiState : FeedsUiState {
    override var newsState: UiState<List<News>> by mutableStateOf(UiState())
    override var headlinesState: UiState<List<News>> by mutableStateOf(UiState())
}