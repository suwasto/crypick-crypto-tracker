package app.id.crypick.di

import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(
    enableNetworkLogs: Boolean = true,
    appDeclaration: KoinAppDeclaration = {}
) = startKoin {
    appDeclaration()
    modules(listOf(platformModule(), commonModule(enableNetworkLogs = enableNetworkLogs)))
}

// called by ios
fun KoinApplication.Companion.start(): KoinApplication = initKoin {}

