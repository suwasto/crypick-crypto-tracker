package app.id.crypick

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.staticCompositionLocalOf
import app.id.crypick.features.main.MainScreen
import app.id.crypick.features.main.MainViewModel
import app.id.crypick.ui.theme.CrypickTheme
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import org.koin.compose.KoinContext
import org.koin.compose.koinInject

@Composable
fun CrypickApp(
    mainViewModel: MainViewModel = koinInject()
) {
    val darkTheme = when (mainViewModel.appTheme.collectAsState().value) {
        1 -> true
        else -> false
    }
    KoinContext {
        CrypickTheme(darkTheme) {
            Navigator(
                screen = MainScreen(),
                content = { navigator ->
                    ProvideAppNavigator(
                        navigator = navigator,
                        content = { SlideTransition(navigator = navigator) },
                    )
                }
            )
        }
    }
}

val LocalAppNavigator: ProvidableCompositionLocal<Navigator?> = staticCompositionLocalOf { null }

@Composable
fun ProvideAppNavigator(navigator: Navigator, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalAppNavigator provides navigator) {
        content()
    }
}
