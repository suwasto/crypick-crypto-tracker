package app.id.crypick.di

import app.id.crypick.BuildKonfig.COINGECKO_BASE_URL
import app.id.crypick.BuildKonfig.NEWS_API_KEY
import app.id.crypick.BuildKonfig.NEWS_BASE_URL
import app.id.crypick.data.repository.news.NewsRepositoryImpl
import app.id.crypick.domain.repository.NewsRepository
import app.id.crypick.utils.DateTimeKtx
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.addDefaultResponseValidation
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun commonModule(enableNetworkLogs: Boolean) = module {
    single(named("news")) {
        HttpClient {
            expectSuccess = true
            addDefaultResponseValidation()
            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = NEWS_BASE_URL
                    parameters.append("apiKey", NEWS_API_KEY)
                }
            }

            if (enableNetworkLogs) {
                install(Logging) {
                    level = LogLevel.HEADERS
                    logger = object : Logger {
                        override fun log(message: String) {
                            Napier.i(tag = "Http Client", message = message)
                        }
                    }
                }.also {
                    Napier.base(DebugAntilog())
                }
            }

            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                    }
                )
            }
        }
    }

    single(named("coingecko")) {
        HttpClient {
            expectSuccess = true
            addDefaultResponseValidation()
            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = COINGECKO_BASE_URL
                }
            }

            if (enableNetworkLogs) {
                install(Logging) {
                    level = LogLevel.HEADERS
                    logger = object : Logger {
                        override fun log(message: String) {
                            Napier.i(tag = "Http Client", message = message)
                        }
                    }
                }.also {
                    Napier.base(DebugAntilog())
                }
            }

            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                    }
                )
            }
        }
    }
    singleOf(::DateTimeKtx)
    single<NewsRepository> { NewsRepositoryImpl(
        httpClient = get(named("news")),
        dateTimeKtx = get()
    ) }

}

expect fun platformModule(): Module