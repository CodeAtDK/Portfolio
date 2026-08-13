package com.example.portfolio.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// Core Background & Surface Tokens (Developer Aesthetic)
val BackgroundDark = Color(0xFF0D1117)
val SurfaceDark = Color(0xFF161B22)
val SurfaceBright = Color(0xFF1C2333)
val BorderDark = Color(0xFF30363D)
val BorderLight = Color(0xFF3D444D)

// Primary (Android Green)
val PrimaryGreen = Color(0xFF3DDC84)
val PrimaryGreenDim = Color(0xFF2A9D63)
val PrimaryGreenGlow = Color(0x263DDC84) // 15% opacity

// Secondary (Warm Orange)
val SecondaryWarm = Color(0xFFFF8A65)
val SecondaryWarmGlow = Color(0x14FF8A65) // 8% opacity

// Tertiary (Blue)
val TertiaryBlue = Color(0xFF58A6FF)
val TertiaryBlueGlow = Color(0x1458A6FF) // 8% opacity

// Semantic & Text
val TextPrimary = Color(0xFFE6EDF3)
val TextMuted = Color(0xFF8B949E)
val TextDim = Color(0xFF656D76)
val ErrorLight = Color(0xFFF85149)

// For Material Theme mapping
val OnSurface = TextPrimary
val OnSurfaceVariant = TextMuted
val SurfaceContainerLowest = Color(0xFF010409)
val SurfaceContainerLow = Color(0xFF0D1117)
val SurfaceContainer = Color(0xFF161B22)
val SurfaceContainerHigh = Color(0xFF21262D)
val SurfaceContainerHighest = Color(0xFF30363D)

// Gradients matching HTML
val ProjectGradient1 = Brush.linearGradient(
    colors = listOf(Color(0x263DDC84), Color(0x1458A6FF)) // 15% green to 8% blue
)
val ProjectGradient2 = Brush.linearGradient(
    colors = listOf(Color(0x26FF8A65), Color(0x143DDC84)) // 15% orange to 8% green
)
val ProjectGradient3 = Brush.linearGradient(
    colors = listOf(Color(0x2658A6FF), Color(0x14FF8A65)) // 15% blue to 8% orange
)
val ProjectGradient4 = Brush.linearGradient(
    colors = listOf(Color(0x1A3DDC84), Color(0x1A58A6FF)) // 10% green to 10% blue
)

val TimelineGradient = Brush.verticalGradient(
    colors = listOf(PrimaryGreenDim, BorderDark, Color.Transparent)
)
