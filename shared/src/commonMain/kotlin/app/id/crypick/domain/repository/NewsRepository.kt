package app.id.crypick.domain.repository

import app.id.crypick.data.network.dto.NewsDto
import app.id.crypick.data.network.utils.NetworkResultState
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun fetchNews(): Flow<NetworkResultState<NewsDto>>

}