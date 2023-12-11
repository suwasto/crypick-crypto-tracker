package app.id.crypick.features.feeds

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import app.id.crypick.data.network.utils.NetworkResultState
import app.id.crypick.domain.repository.NewsRepository
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Stable
interface FeedsUiState {
    val apiResult: String?
    val loading: Boolean
}

private class MutableFeedsUiState: FeedsUiState {
    override var apiResult: String? by mutableStateOf(null)
    override var loading: Boolean by mutableStateOf(false)
}

class FeedsScreenModel(
    private val newsRepository: NewsRepository
): ScreenModel {

    private val _uiState = MutableFeedsUiState()
    val uiState: FeedsUiState = _uiState

    fun updateValue() {
        _uiState.loading = true
        screenModelScope.launch {
            newsRepository.fetchNews().collectLatest {
                when (it) {
                    is NetworkResultState.Loading -> {
                    }
                    is NetworkResultState.Success -> {
                        _uiState.apiResult = it.data.toString()
                    }
                    is NetworkResultState.Failure -> {
                        _uiState.apiResult = it.exception.toString()
                    }
                }
            }
            _uiState.loading = false
        }
    }

}