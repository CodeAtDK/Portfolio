package com.example.portfolio.ui.components

import androidx.compose.animation.core.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

/**
 * Fade-in + slide-up entrance animation modifier.
 * Returns `this` directly to guarantee immediate, rock-solid visibility across all browsers
 * (Safari WebKit, Chrome, Firefox) without coroutine cancellation or detached Modifier.Node crashes.
 */
fun Modifier.fadeInSlideUp(
    durationMs: Int = 600,
    delayMs: Int = 0
): Modifier = this

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
