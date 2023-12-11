package app.id.crypick.domain.repository

import kotlinx.coroutines.flow.Flow

interface SettingRepository {
    suspend fun saveAppTheme(theme: Int)
    fun getAppTheme(): Flow<Int?>
    fun clearAll()
}