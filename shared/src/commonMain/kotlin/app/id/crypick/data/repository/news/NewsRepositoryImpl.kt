package app.id.crypick.data.repository.news

import app.id.crypick.data.network.dto.NewsDto
import app.id.crypick.data.network.utils.NetworkResultState
import app.id.crypick.data.network.utils.safeApiCall
import app.id.crypick.domain.repository.NewsRepository
import app.id.crypick.utils.DateTimeKtx
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NewsRepositoryImpl(
    private val httpClient: HttpClient,
    private val dateTimeKtx: DateTimeKtx
) : NewsRepository {

    override fun fetchNews(): Flow<NetworkResultState<NewsDto>> {
        return flow {
            emit(
                safeApiCall {
                    val response = httpClient.get(
                        urlString = "everything",
                        block = {
                            parameter("q", "crypto")
                            parameter("from", dateTimeKtx.getCurrentDate())
                            parameter("to", dateTimeKtx.getCurrentDate())
                            parameter("sortBy", "popularity")
                        }
                    ).body<NewsDto>()
                    response
                }
            )
        }
    }
}