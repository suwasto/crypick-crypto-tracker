package app.id.crypick.utils

import androidx.compose.runtime.Composable


@Composable
expect fun BackHandler(isEnabled: Boolean, onBack: ()-> Unit)
