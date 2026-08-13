package com.example.portfolio.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val PortfolioTypography = Typography(
    // Display Large (Hero Desktop headings) - 44sp / bold
    displayLarge = TextStyle(
        fontFamily = FontFamily.Default, // Represents Space Grotesk in HTML
        fontWeight = FontWeight.Bold,
        fontSize = 42.sp,
        lineHeight = 50.sp,
        letterSpacing = (-0.02f).sp
    ),
    // Display Medium (Mobile Hero & Section Titles) - 28sp / bold
    displayMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = (-0.02f).sp
    ),
    // Headline Medium (Card & Section headings) - 20sp / semi-bold
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        letterSpacing = (-0.01f).sp
    ),
    // Title Medium (Sub-card & list headings) - 16sp / semi-bold
    titleMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    // Body Large (Hero & introduction paragraphs) - 17sp
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default, // Represents Inter in HTML
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp,
        lineHeight = 26.sp
    ),
    // Body Medium (Standard descriptive text & explanations) - 15sp
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 24.sp
    ),
    // Label Small / JetBrains Mono style (Category labels, dates, subtitles) - 13sp
    labelSmall = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Medium,
        fontSize = 13.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.04.sp
    ),
    // Label Medium / Code snippet style (Tech chips & AI tags) - 12sp
    labelMedium = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp
    )
)
