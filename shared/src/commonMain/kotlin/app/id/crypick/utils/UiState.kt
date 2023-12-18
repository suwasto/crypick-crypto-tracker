package app.id.crypick.utils

data class UiState<T>(
    var loading: Boolean = true,
    var data: T? = null,
    var errorMsg: String? = null
)
