package app.id.crypick.data.repository

import app.id.crypick.data.local.PreferenceManager
import app.id.crypick.domain.repository.SettingRepository
import kotlinx.coroutines.flow.Flow

class SettingRepositoryImpl(
    private val preferenceManager: PreferenceManager
) : SettingRepository {

    override suspend fun saveAppTheme(theme: Int) {
        preferenceManager.setInt(key = APP_THEME, value = theme)
    }

    override fun getAppTheme(): Flow<Int?> {
        return preferenceManager.getInt(key = APP_THEME)
    }

    override fun clearAll() {
        return preferenceManager.clearPreferences()
    }

    companion object {
        private const val APP_THEME = "apptheme"
    }

}