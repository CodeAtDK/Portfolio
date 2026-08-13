package com.example.portfolio.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColorScheme(
    primary = PrimaryGreen,
    onPrimary = BackgroundDark,
    primaryContainer = PrimaryGreenDim,
    onPrimaryContainer = TextPrimary,
    inversePrimary = PrimaryGreen,
    
    secondary = SecondaryWarm,
    onSecondary = BackgroundDark,
    secondaryContainer = SecondaryWarmGlow,
    onSecondaryContainer = SecondaryWarm,
    
    tertiary = TertiaryBlue,
    onTertiary = BackgroundDark,
    tertiaryContainer = TertiaryBlueGlow,
    onTertiaryContainer = TertiaryBlue,
    
    background = BackgroundDark,
    onBackground = TextPrimary,
    
    surface = SurfaceDark,
    onSurface = TextPrimary,
    surfaceVariant = SurfaceBright,
    onSurfaceVariant = TextMuted,
    
    outline = BorderDark,
    outlineVariant = BorderLight,
    
    error = ErrorLight,
    onError = BackgroundDark,
    
    surfaceContainerLowest = SurfaceContainerLowest,
    surfaceContainerLow = SurfaceContainerLow,
    surfaceContainer = SurfaceContainer,
    surfaceContainerHigh = SurfaceContainerHigh,
    surfaceContainerHighest = SurfaceContainerHighest
)

@Composable
fun PortfolioTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorPalette,
        typography = PortfolioTypography,
        content = content
    )
}
