package app.id.crypick.data.repository

import app.id.crypick.data.mapper.toDomain
import app.id.crypick.data.network.dto.NewsDto
import app.id.crypick.data.network.utils.NetworkResultState
import app.id.crypick.data.network.utils.safeApiCall
import app.id.crypick.domain.model.News
import app.id.crypick.domain.repository.NewsRepository
import app.id.crypick.utils.DateTimeKtx
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class NewsRepositoryImpl(
    private val httpClient: HttpClient,
    private val dateTimeKtx: DateTimeKtx
) : NewsRepository {
    override suspend fun fetchNews(): Flow<NetworkResultState<List<News>>> {
        return flowOf(safeApiCall {
            val response = httpClient.get(
                urlString = "everything",
                block = {
                    parameter("q", "crypto")
                    parameter("sortBy", "popularity")
                }
            ).body<NewsDto>()
            response.toDomain(dateTimeKtx)
        })
    }

    override suspend fun fetchNewsHeadlines(): Flow<NetworkResultState<List<News>>> {
        return flowOf(safeApiCall {
            val response = httpClient.get(
                urlString = "top-headlines",
                block = {
                    parameter("q", "crypto")
                }
            ).body<NewsDto>()
            response.toDomain(dateTimeKtx)
        })
    }
}
