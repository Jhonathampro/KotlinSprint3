package br.com.github.sprint3.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = EuroYellow,
    secondary = EuroBlueLight,
    tertiary = EuroYellowLight,
    background = EuroBlue,
    surface = CardBackground
)

private val LightColorScheme = lightColorScheme(
    primary = EuroBlue,
    secondary = EuroYellow,
    tertiary = EuroBlueDark,
    background = EuroBlue,
    surface = CardBackground
)

@Composable
fun Sprint3Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}