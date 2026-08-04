package com.example.portfolio.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import com.example.portfolio.ui.theme.CanvasBase

private data class FloatingBox(
    val initialX: Float, // 0.0 to 1.0 of screen width
    val initialY: Float, // 0.0 to 1.0 of screen height
    val size: Float,     // Box dimensions in canvas units
    val speedY: Float,   // Vertical floating velocity
    val speedX: Float,   // Horizontal sway oscillation
    val rotSpeed: Float, // Rotational momentum (turns per cycle)
    val color: Color,
    val isWireframe: Boolean
)

private data class StarParticle(
    val x: Float,
    val y: Float,
    val radius: Float,
    val maxAlpha: Float,
    val phase: Float
)

@Composable
fun AnimatedMeshBackground(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "floating_cosmos")

    // Primary loop continuous animator (0.0 to 1.0 over 24 seconds)
    val loop by infiniteTransition.animateFloat(
        initialValue = 0.0f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 24000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "loop"
    )

    // Pulsating atmosphere animator (0.0 to 1.0 oscillating)
    val pulse by infiniteTransition.animateFloat(
        initialValue = 0.0f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 6000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse"
    )

    // Generate floating boxes and cosmic dust once
    val floatingBoxes = remember {
        listOf(
            FloatingBox(0.12f, 0.75f, 48f, 0.3f, 0.04f, 2f, Color(0xFF3D5AFE), true),
            FloatingBox(0.82f, 0.65f, 56f, 0.25f, -0.05f, -1.5f, Color(0xFF00DAF3), false),
            FloatingBox(0.72f, 0.20f, 32f, 0.35f, 0.03f, 3f, Color(0xFF6200EA), true),
            FloatingBox(0.25f, 0.30f, 42f, 0.20f, -0.03f, -2f, Color(0xFF3D5AFE), false),
            FloatingBox(0.55f, 0.55f, 36f, 0.28f, 0.05f, 2.5f, Color(0xFF00DAF3), true),
            FloatingBox(0.18f, 0.15f, 60f, 0.18f, 0.02f, 1.2f, Color(0xFF6200EA), false),
            FloatingBox(0.48f, 0.85f, 28f, 0.40f, -0.04f, -3.5f, Color(0xFFBBC3FF), true),
            FloatingBox(0.90f, 0.35f, 45f, 0.22f, -0.03f, 1.8f, Color(0xFF3D5AFE), false),
            FloatingBox(0.35f, 0.65f, 50f, 0.26f, 0.04f, -1.5f, Color(0xFF00DAF3), true),
            FloatingBox(0.65f, 0.85f, 38f, 0.32f, -0.02f, 2.2f, Color(0xFFCFBCFF), true)
        )
    }

    val starParticles = remember {
        List(50) { i ->
            StarParticle(
                x = ((i * 37) % 100) / 100f,
                y = ((i * 67) % 100) / 100f,
                radius = (1.2f + (i % 4) * 0.6f),
                maxAlpha = 0.25f + (i % 5) * 0.12f,
                phase = (i * 0.2f) % 1.0f
            )
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(CanvasBase)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // 1. Deep Cosmic Glowing Mesh Orbs
            val center1 = Offset(w * (0.25f + 0.1f * pulse), h * (0.3f - 0.08f * pulse))
            val radius1 = maxOf(w, h) * 0.65f
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0xFF3D5AFE).copy(alpha = 0.16f),
                        Color(0xFF3D5AFE).copy(alpha = 0.04f),
                        Color.Transparent
                    ),
                    center = center1,
                    radius = radius1
                ),
                center = center1,
                radius = radius1
            )

            val center2 = Offset(w * (0.75f - 0.09f * pulse), h * (0.7f + 0.07f * pulse))
            val radius2 = maxOf(w, h) * 0.75f
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0xFF6200EA).copy(alpha = 0.14f),
                        Color(0xFF6200EA).copy(alpha = 0.03f),
                        Color.Transparent
                    ),
                    center = center2,
                    radius = radius2
                ),
                center = center2,
                radius = radius2
            )

            // 2. Drifting Dust & Starfield Particles
            starParticles.forEach { star ->
                // Calculate pulsating brightness
                val alphaCycle = kotlin.math.abs(kotlin.math.sin((loop + star.phase) * kotlin.math.PI * 4)).toFloat()
                val currentAlpha = (star.maxAlpha * (0.3f + 0.7f * alphaCycle)).coerceIn(0.05f, 0.8f)
                
                // Slight drift upward with continuous loop wrapping
                val currentY = (star.y - (loop * 0.08f) + 1.0f) % 1.0f
                val pos = Offset(star.x * w, currentY * h)
                
                drawCircle(
                    color = Color.White.copy(alpha = currentAlpha),
                    radius = star.radius,
                    center = pos
                )
            }

            // 3. Floating Geometric Boxes & Cubes (Ayush Raj site inspiration)
            floatingBoxes.forEach { box ->
                // Continuous vertical wrap-around float calculation
                val yOffset = (box.initialY - (loop * box.speedY) + 1.0f) % 1.0f
                val xOffset = (box.initialX + kotlin.math.sin(loop * kotlin.math.PI * 2 * 2) * box.speedX).toFloat().coerceIn(0.02f, 0.98f)
                
                val centerPos = Offset(xOffset * w, yOffset * h)
                val rotationAngle = (loop * box.rotSpeed * 360f)
                
                // Scale boxes based on responsive canvas width
                val responsiveSize = (box.size * (minOf(w, h) / 700f).coerceIn(0.7f, 1.5f))
                val boxSize = Size(responsiveSize, responsiveSize)

                // Draw rotated geometric box with glowing glass edge
                withTransform({
                    rotate(degrees = rotationAngle, pivot = centerPos)
                }) {
                    val topLeft = Offset(centerPos.x - boxSize.width / 2f, centerPos.y - boxSize.height / 2f)

                    if (box.isWireframe) {
                        // Glowing wireframe floating box
                        drawRoundRect(
                            color = box.color.copy(alpha = 0.45f),
                            topLeft = topLeft,
                            size = boxSize,
                            cornerRadius = CornerRadius(8f, 8f),
                            style = Stroke(width = 2.5f)
                        )
                        // Inner faint glass fill
                        drawRoundRect(
                            color = box.color.copy(alpha = 0.04f),
                            topLeft = topLeft,
                            size = boxSize,
                            cornerRadius = CornerRadius(8f, 8f)
                        )
                    } else {
                        // Translucent glass cube with crisp outline
                        drawRoundRect(
                            color = box.color.copy(alpha = 0.15f),
                            topLeft = topLeft,
                            size = boxSize,
                            cornerRadius = CornerRadius(8f, 8f)
                        )
                        drawRoundRect(
                            color = Color.White.copy(alpha = 0.25f),
                            topLeft = topLeft,
                            size = boxSize,
                            cornerRadius = CornerRadius(8f, 8f),
                            style = Stroke(width = 1.5f)
                        )
                    }
                }
            }
        }
    }
}
