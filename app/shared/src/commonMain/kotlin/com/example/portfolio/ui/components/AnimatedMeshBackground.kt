package com.example.portfolio.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun AnimatedMeshBackground(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "mesh")
    
    val time by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(20000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "time"
    )

    Canvas(modifier = modifier.fillMaxSize()) {
        val width = size.width
        val height = size.height

        // Background base
        drawRect(Color(0xFF0A0A0A))

        // First animated blob (Electric Blue)
        val x1 = width * (0.5f + 0.3f * sin(time * 2 * PI.toFloat()))
        val y1 = height * (0.5f + 0.2f * cos(time * 2 * PI.toFloat() * 0.7f))
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(Color(0xFF0A5AFF).copy(alpha = 0.15f), Color.Transparent),
                center = Offset(x1, y1),
                radius = width * 0.8f
            ),
            center = Offset(x1, y1),
            radius = width * 0.8f
        )

        // Second animated blob (Deep Purple)
        val x2 = width * (0.3f + 0.4f * cos(time * 2 * PI.toFloat() * 1.2f))
        val y2 = height * (0.7f + 0.3f * sin(time * 2 * PI.toFloat() * 0.5f))
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(Color(0xFF6200EA).copy(alpha = 0.12f), Color.Transparent),
                center = Offset(x2, y2),
                radius = width * 0.9f
            ),
            center = Offset(x2, y2),
            radius = width * 0.9f
        )
        
        // Third blob for depth
        val x3 = width * (0.8f + 0.2f * sin(time * 2 * PI.toFloat() * 0.8f))
        val y3 = height * (0.2f + 0.4f * cos(time * 2 * PI.toFloat() * 1.5f))
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(Color(0xFF00DAF3).copy(alpha = 0.08f), Color.Transparent),
                center = Offset(x3, y3),
                radius = width * 0.6f
            ),
            center = Offset(x3, y3),
            radius = width * 0.6f
        )
    }
}
