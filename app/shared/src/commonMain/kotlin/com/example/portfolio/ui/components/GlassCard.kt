package com.example.portfolio.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.portfolio.ui.theme.GlassBorder
import com.example.portfolio.ui.theme.PrimaryBlue
import com.example.portfolio.ui.theme.SurfaceContainerLow

@Composable
fun GlassCard(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(24.dp),
    backgroundColor: Color = SurfaceContainerLow.copy(alpha = 0.70f),
    borderColor: Color = GlassBorder,
    contentPadding: Dp = 24.dp,
    animateGlow: Boolean = false,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "card_glow")
    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.15f,
        targetValue = if (animateGlow) 0.65f else 0.15f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glow_alpha"
    )

    val activeBorderColor = if (animateGlow) {
        PrimaryBlue.copy(alpha = glowAlpha)
    } else {
        borderColor
    }

    var cardModifier = modifier
        .clip(shape)
        .background(backgroundColor)
        .border(width = if (animateGlow) 1.5.dp else 1.dp, color = activeBorderColor, shape = shape)

    if (onClick != null) {
        cardModifier = cardModifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = androidx.compose.foundation.LocalIndication.current,
            onClick = onClick
        )
    }

    Box(
        modifier = cardModifier.padding(contentPadding)
    ) {
        content()
    }
}
