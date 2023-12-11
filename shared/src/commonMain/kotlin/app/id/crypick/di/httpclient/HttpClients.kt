package app.id.crypick.di.httpclient

import app.id.crypick.BuildKonfig
import app.id.crypick.BuildKonfig.NEWS_BASE_URL
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

fun coinGeckoHttpClient(enableNetworkLogs: Boolean): HttpClient {
    return HttpClient {
        expectSuccess = true
        addDefaultResponseValidation()
        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = BuildKonfig.COINGECKO_BASE_URL
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

fun newsHttpClient(enableNetworkLogs: Boolean): HttpClient {
    return HttpClient {
        expectSuccess = true
        addDefaultResponseValidation()
        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = NEWS_BASE_URL
                path("v2/")
                parameters.append("apiKey", BuildKonfig.NEWS_API_KEY)
            }
        }

        if (enableNetworkLogs) {
            install(Logging) {
                level = LogLevel.BODY
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