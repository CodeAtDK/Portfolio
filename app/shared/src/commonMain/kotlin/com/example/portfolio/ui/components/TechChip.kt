package com.example.portfolio.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.portfolio.ui.theme.GlassBorder
import com.example.portfolio.ui.theme.PrimaryGreen

@Composable
fun TechChip(
    text: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color(0x0AFFFFFF), // 4% white
    borderColor: Color = GlassBorder,
    textColor: Color = MaterialTheme.colorScheme.onSurfaceVariant
) {
    val hoverInteractionSource = remember { MutableInteractionSource() }
    val isHovered by hoverInteractionSource.collectIsHoveredAsState()

    val animatedBg by animateColorAsState(
        targetValue = if (isHovered) PrimaryGreen.copy(alpha = 0.08f) else backgroundColor,
        animationSpec = tween(200),
        label = "chip_bg"
    )
    val animatedBorder by animateColorAsState(
        targetValue = if (isHovered) PrimaryGreen.copy(alpha = 0.3f) else borderColor,
        animationSpec = tween(200),
        label = "chip_border"
    )
    val animatedText by animateColorAsState(
        targetValue = if (isHovered) PrimaryGreen else textColor,
        animationSpec = tween(200),
        label = "chip_text"
    )

    Box(
        modifier = modifier
            .hoverable(hoverInteractionSource)
            .clip(RoundedCornerShape(50)) // Pill shape
            .background(animatedBg)
            .border(1.dp, animatedBorder, RoundedCornerShape(50))
            .padding(horizontal = 14.dp, vertical = 6.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            color = animatedText
        )
    }
}
