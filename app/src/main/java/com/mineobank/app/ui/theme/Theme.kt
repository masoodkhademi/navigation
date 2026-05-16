package com.mineobank.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection

private val MineoLightColorScheme = lightColorScheme(
    primary = MineoNavy,
    onPrimary = MineoSurface,
    primaryContainer = MineoLightBlue,
    onPrimaryContainer = MineoSurface,
    secondary = MineoAccent,
    onSecondary = MineoSurface,
    background = MineoBackground,
    onBackground = MineoTextPrimary,
    surface = MineoSurface,
    onSurface = MineoTextPrimary,
    surfaceVariant = MineoBackground,
    onSurfaceVariant = MineoTextMedium,
    error = MineoError,
    onError = MineoSurface,
    outline = MineoDivider
)

private val MineoDarkColorScheme = darkColorScheme(
    primary = MineoAccent,
    onPrimary = MineoDarkNavy,
    primaryContainer = MineoNavy,
    onPrimaryContainer = MineoSurface,
    secondary = MineoAccent,
    onSecondary = MineoDarkNavy,
    background = MineoDarkNavy,
    onBackground = MineoSurface,
    surface = MineoNavy,
    onSurface = MineoSurface,
    surfaceVariant = MineoLightBlue,
    onSurfaceVariant = MineoTextLight,
    error = MineoError,
    onError = MineoSurface,
    outline = MineoNavInactive
)

@Composable
fun MineoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) MineoDarkColorScheme else MineoLightColorScheme

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = MineoTypography,
            content = content
        )
    }
}
