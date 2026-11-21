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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import com.example.portfolio.ui.theme.BackgroundDark
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

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

/**
 * Renders the glowing cosmos background with animated mesh orbs, floating geometric boxes,
 * and twinkling star particles. Uses zero-allocation concentric alpha passes for orbs,
 * preventing Skiko C++ SkShader exhaustion and nullptr crashes on Safari WebKit.
 */
@Composable
fun AnimatedMeshBackground(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "floating_cosmos")

    // Continuous 24-second floating cycle for boxes & dust
    val loop by infiniteTransition.animateFloat(
        initialValue = 0.0f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 24000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "loop"
    )

    // Pulsating atmospheric wave for cosmic orbs
    val pulse by infiniteTransition.animateFloat(
        initialValue = 0.0f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 8000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse"
    )

    // Dynamic mesh time for harmonic orb orbits
    val time by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 20000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "time"
    )

    // Generate floating geometric boxes once
    val floatingBoxes = remember {
        listOf(
            FloatingBox(0.12f, 0.75f, 48f, 0.30f, 0.04f, 2.0f, Color(0xFF3D5AFE), true),
            FloatingBox(0.82f, 0.65f, 56f, 0.25f, -0.05f, -1.5f, Color(0xFF00DAF3), false),
            FloatingBox(0.72f, 0.20f, 32f, 0.35f, 0.03f, 3.0f, Color(0xFF6200EA), true),
            FloatingBox(0.25f, 0.30f, 42f, 0.20f, -0.03f, -2.0f, Color(0xFF3D5AFE), false),
            FloatingBox(0.55f, 0.55f, 36f, 0.28f, 0.05f, 2.5f, Color(0xFF00DAF3), true),
            FloatingBox(0.18f, 0.15f, 60f, 0.18f, 0.02f, 1.2f, Color(0xFF6200EA), false),
            FloatingBox(0.48f, 0.85f, 28f, 0.40f, -0.04f, -3.5f, Color(0xFFBBC3FF), true),
            FloatingBox(0.90f, 0.35f, 45f, 0.22f, -0.03f, 1.8f, Color(0xFF3D5AFE), false),
            FloatingBox(0.35f, 0.65f, 50f, 0.26f, 0.04f, -1.5f, Color(0xFF00DAF3), true),
            FloatingBox(0.65f, 0.85f, 38f, 0.32f, -0.02f, 2.2f, Color(0xFFCFBCFF), true)
        )
    }

    // Generate cosmic dust particles once
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
            .background(BackgroundDark)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height
            if (w <= 0f || h <= 0f) return@Canvas

            // 0. Base dark canvas
            drawRect(Color(0xFF0A0A0A))

            // 1. Deep Cosmic Glowing Mesh Orbs (Shader-free multi-layer alpha gradient)
            // Blob 1: Electric Blue orb orbiting smoothly
            val x1 = w * (0.35f + 0.25f * sin(time * 2 * PI.toFloat()))
            val y1 = h * (0.35f + 0.18f * cos(time * 2 * PI.toFloat() * 0.7f))
            drawGlowingOrb(
                center = Offset(x1, y1),
                radius = maxOf(w, h) * 0.55f,
                baseColor = Color(0xFF0A5AFF),
                peakAlpha = 0.16f
            )

            // Blob 2: Deep Purple orb with breathing pulse
            val x2 = w * (0.70f + 0.20f * cos(time * 2 * PI.toFloat() * 1.2f))
            val y2 = h * (0.65f + 0.22f * sin(time * 2 * PI.toFloat() * 0.5f))
            drawGlowingOrb(
                center = Offset(x2, y2),
                radius = maxOf(w, h) * 0.60f,
                baseColor = Color(0xFF6200EA),
                peakAlpha = 0.14f
            )

            // Blob 3: Vibrant Cyan accent orb
            val x3 = w * (0.80f + 0.15f * sin(time * 2 * PI.toFloat() * 0.8f))
            val y3 = h * (0.20f + 0.25f * cos(time * 2 * PI.toFloat() * 1.5f))
            drawGlowingOrb(
                center = Offset(x3, y3),
                radius = maxOf(w, h) * 0.45f,
                baseColor = Color(0xFF00DAF3),
                peakAlpha = 0.10f
            )

            // 2. Drifting Dust & Starfield Particles
            starParticles.forEach { star ->
                val alphaCycle = abs(sin((loop + star.phase) * PI.toFloat() * 4))
                val currentAlpha = (star.maxAlpha * (0.3f + 0.7f * alphaCycle)).coerceIn(0.05f, 0.8f)
                val currentY = (star.y - (loop * 0.08f) + 1.0f) % 1.0f
                val pos = Offset(star.x * w, currentY * h)

                drawCircle(
                    color = Color.White.copy(alpha = currentAlpha),
                    radius = star.radius,
                    center = pos
                )
            }

            // 3. Floating Geometric Boxes & Translucent Cubes
            floatingBoxes.forEach { box ->
                val yOffset = (box.initialY - (loop * box.speedY) + 1.0f) % 1.0f
                val xOffset = (box.initialX + sin(loop * PI.toFloat() * 4) * box.speedX).coerceIn(0.02f, 0.98f)

                val centerPos = Offset(xOffset * w, yOffset * h)
                val rotationAngle = (loop * box.rotSpeed * 360f)
                val responsiveSize = (box.size * (minOf(w, h) / 700f).coerceIn(0.7f, 1.4f))
                val boxSize = Size(responsiveSize, responsiveSize)

                withTransform({
                    rotate(degrees = rotationAngle, pivot = centerPos)
                }) {
                    val topLeft = Offset(centerPos.x - boxSize.width / 2f, centerPos.y - boxSize.height / 2f)

                    if (box.isWireframe) {
                        // Glowing wireframe floating box with subtle glow
                        drawRoundRect(
                            color = box.color.copy(alpha = 0.45f),
                            topLeft = topLeft,
                            size = boxSize,
                            cornerRadius = CornerRadius(8f, 8f),
                            style = Stroke(width = 2.0f)
                        )
                        // Faint inner glass sheen
                        drawRoundRect(
                            color = box.color.copy(alpha = 0.04f),
                            topLeft = topLeft,
                            size = boxSize,
                            cornerRadius = CornerRadius(8f, 8f)
                        )
                    } else {
                        // Translucent glass cube with crisp highlight edge
                        drawRoundRect(
                            color = box.color.copy(alpha = 0.15f),
                            topLeft = topLeft,
                            size = boxSize,
                            cornerRadius = CornerRadius(8f, 8f)
                        )
                        drawRoundRect(
                            color = Color.White.copy(alpha = 0.22f),
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

/**
 * Draws a soft, organic glowing orb with quadratic falloff using concentric alpha passes.
 * Uses 0 native SkShader allocations, guaranteeing 100% stability across all WebAssembly runtimes.
 */
private fun DrawScope.drawGlowingOrb(
    center: Offset,
    radius: Float,
    baseColor: Color,
    peakAlpha: Float
) {
    if (radius <= 0f) return
    val steps = 7
    for (i in steps downTo 1) {
        val fraction = i.toFloat() / steps
        val stepRadius = radius * fraction
        // Smooth quadratic falloff simulates radial-gradient blur perfectly
        val stepAlpha = peakAlpha * (1.0f - fraction) * (1.0f - fraction) * 0.45f
        drawCircle(
            color = baseColor.copy(alpha = stepAlpha),
            radius = stepRadius,
            center = center
        )
    }
}
