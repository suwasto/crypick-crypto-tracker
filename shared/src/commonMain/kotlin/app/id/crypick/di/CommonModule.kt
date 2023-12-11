package app.id.crypick.di

import app.id.crypick.data.local.PreferenceManager
import app.id.crypick.data.repository.NewsRepositoryImpl
import app.id.crypick.data.repository.SettingRepositoryImpl
import app.id.crypick.di.httpclient.coinGeckoHttpClient
import app.id.crypick.di.httpclient.newsHttpClient
import app.id.crypick.domain.repository.NewsRepository
import app.id.crypick.domain.repository.SettingRepository
import app.id.crypick.features.feeds.FeedsScreenModel
import app.id.crypick.features.main.MainViewModel
import app.id.crypick.utils.DateTimeKtx
import com.russhwolf.settings.Settings
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
        )
    }
    single<SettingRepository> {
        SettingRepositoryImpl(get())
    }

    single { MainViewModel(settingsRepository = get()) }
    single { FeedsScreenModel(newsRepository = get()) }
}

expect fun platformModule(): Module