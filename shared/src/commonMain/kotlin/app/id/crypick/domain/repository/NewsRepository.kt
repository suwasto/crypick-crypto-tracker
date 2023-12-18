package app.id.crypick.domain.repository

import app.id.crypick.data.network.utils.NetworkResultState
import app.id.crypick.domain.model.News
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun fetchNews(): Flow<NetworkResultState<List<News>>>

    suspend fun fetchNewsHeadlines(): Flow<NetworkResultState<List<News>>>

}