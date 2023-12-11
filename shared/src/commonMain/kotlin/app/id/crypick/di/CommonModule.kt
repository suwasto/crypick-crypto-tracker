package app.id.crypick.di

import app.id.crypick.BuildKonfig.COINGECKO_BASE_URL
import app.id.crypick.BuildKonfig.NEWS_API_KEY
import app.id.crypick.BuildKonfig.NEWS_BASE_URL
import app.id.crypick.data.local.PreferenceManager
import app.id.crypick.data.repository.NewsRepositoryImpl
import app.id.crypick.data.repository.SettingRepositoryImpl
import app.id.crypick.di.httpclient.coinGeckoHttpClient
import app.id.crypick.di.httpclient.newsHttpClient
import app.id.crypick.domain.repository.NewsRepository
import app.id.crypick.domain.repository.SettingRepository
import app.id.crypick.features.feeds.HomeScreenModel
import app.id.crypick.features.main.MainViewModel
import app.id.crypick.utils.DateTimeKtx
import com.russhwolf.settings.Settings
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
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun commonModule(enableNetworkLogs: Boolean) = module {
    // networks
    single(named("news")) {
        newsHttpClient(enableNetworkLogs)
    }
    single(named("coingecko")) {
        coinGeckoHttpClient(enableNetworkLogs)
    }

    single<Settings> { Settings() }
    single { PreferenceManager(get()) }
    singleOf(::DateTimeKtx)

    // repositories
    single<NewsRepository> {
        NewsRepositoryImpl(
            httpClient = get(named("news")),
            dateTimeKtx = get()
        )
    }
    single<SettingRepository> {
        SettingRepositoryImpl(get())
    }

    single { MainViewModel(settingsRepository = get()) }
    single { HomeScreenModel() }
}

expect fun platformModule(): Module