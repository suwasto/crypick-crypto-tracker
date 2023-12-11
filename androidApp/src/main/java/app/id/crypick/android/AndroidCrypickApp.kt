package app.id.crypick.android

import android.app.Application
import app.id.crypick.android.di.androidModule
import app.id.crypick.di.initKoin
import io.github.aakira.napier.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class AndroidCrypickApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger(level = if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(androidContext = this@AndroidCrypickApp)
            modules(
                listOf(
                    androidModule,
                ),
            )
        }
    }
}