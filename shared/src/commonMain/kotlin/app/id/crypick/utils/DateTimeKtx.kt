package app.id.crypick.utils

import androidx.compose.ui.text.capitalize
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class DateTimeKtx {

  fun getCurrentDate(): String {
    val currentMoment = Clock.System.now()
    val now = currentMoment.toLocalDateTime(TimeZone.currentSystemDefault())
    val date = now.date
    val day = date.dayOfMonth
    val month = date.monthNumber
    val year = date.year
    return "${year}-${month.zeroPrefixed(2)}-${day.zeroPrefixed(2)}"
  }

  fun getFormattedDate(
    iso8601Timestamp: String,
  ): String {
    val localDateTime = iso8601TimestampToLocalDateTime(iso8601Timestamp)
    val date = localDateTime.date
    val day = date.dayOfMonth
    val month = date.month.name.lowercase().replaceFirstChar { it.uppercase() }
    val year = date.year
    
    // This format should be generated based on an argument.
    // For now, we're hardcoding this to the 'dd.MM.yyyy' format.
    return "${day.zeroPrefixed(2)} $month $year"
  }
  
  private fun iso8601TimestampToLocalDateTime(timestamp: String): LocalDateTime {
    return Instant.parse(timestamp).toLocalDateTime(TimeZone.currentSystemDefault())
  }
}