package app.id.crypick.features.feeds

import app.id.crypick.data.network.utils.onFailure
import app.id.crypick.data.network.utils.onSuccess
import app.id.crypick.domain.repository.NewsRepository
import app.id.crypick.features.feeds.state.FeedsUiState
import app.id.crypick.features.feeds.state.MutableFeedsUiState
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FeedsScreenModel(
    private val newsRepository: NewsRepository
) : ScreenModel {

    private val _uiState = MutableFeedsUiState()
    val uiState: FeedsUiState = _uiState

    fun fetchNews() {
        _uiState.newsState = _uiState.newsState.copy(loading = true)
        screenModelScope.launch {
            newsRepository.fetchNews().collectLatest { newsResult ->
                newsResult.onSuccess {
                    _uiState.newsState = _uiState.newsState.copy(
                        loading = false,
                        data = it.toString(),
                        errorMsg = null
                    )
                }.onFailure {
                    _uiState.newsState = _uiState.newsState.copy(
                        loading = false,
                        errorMsg = it.message
                    )
                }
            }
        }
    }

    fun fetchHeadlines() {
        _uiState.newsState = _uiState.headlinesState.copy(loading = true)
        screenModelScope.launch {
            newsRepository.fetchNews().collectLatest { newsResult ->
                newsResult.onSuccess {
                    _uiState.newsState = _uiState.headlinesState.copy(
                        data = it.toString(),
                        errorMsg = null
                    )
                }.onFailure {
                    _uiState.newsState = _uiState.headlinesState.copy(
                        errorMsg = it.message
                    )
                }
            }
            _uiState.newsState = _uiState.headlinesState.copy(loading = false)
        }
    }

}