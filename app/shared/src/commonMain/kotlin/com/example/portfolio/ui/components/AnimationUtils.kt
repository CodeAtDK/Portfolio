package com.example.portfolio.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.composed
import androidx.compose.ui.unit.dp

/**
 * Fade-in + slide-up entrance animation. Use on section content for staggered reveals.
 * The animation triggers once when the composable first enters composition.
 */
@Composable
fun Modifier.fadeInSlideUp(
    durationMs: Int = 600,
    delayMs: Int = 0
): Modifier = composed {
    var started by remember { mutableStateOf(false) }
    
    LaunchedEffect(Unit) {
        started = true
    }
    
    val animatable = remember { Animatable(0f) }
    
    LaunchedEffect(started) {
        if (started) {
            animatable.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = durationMs,
                    delayMillis = delayMs,
                    easing = FastOutSlowInEasing
                )
            )
        }
    }
    
    val progress = animatable.value
    
    this
        .alpha(progress)
        .offset(y = ((1f - progress) * 20f).dp)
}

/**
 * Pulsing glow animation for active state indicators.
 * Returns an animated alpha value that oscillates between min and max.
 */
@Composable
fun rememberPulseAlpha(
    minAlpha: Float = 0.4f,
    maxAlpha: Float = 1f,
    durationMs: Int = 1500
): Float {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val alpha by infiniteTransition.animateFloat(
        initialValue = maxAlpha,
        targetValue = minAlpha,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMs, easing = EaseInOutCubic),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse_alpha"
    )
    return alpha
}

/**
 * Animated typing dots for loading/thinking states.
 * Returns a string like ".", "..", "..." that cycles.
 */
@Composable
fun rememberTypingDots(
    intervalMs: Int = 400
): String {
    var dotCount by remember { mutableStateOf(1) }
    
    LaunchedEffect(Unit) {
        while (true) {
            kotlinx.coroutines.delay(intervalMs.toLong())
            dotCount = (dotCount % 3) + 1
        }
    }
    
    return ".".repeat(dotCount)
}

private val EaseInOutCubic = CubicBezierEasing(0.65f, 0f, 0.35f, 1f)
