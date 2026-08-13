package com.example.portfolio.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.portfolio.ui.theme.GlassBackground
import com.example.portfolio.ui.theme.GlassBorder
import com.example.portfolio.ui.theme.GlassBorderHover
import com.example.portfolio.ui.theme.GlowGreen

@Composable
fun PortfolioCard(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(16.dp),
    backgroundColor: Color = GlassBackground,
    borderColor: Color = GlassBorder,
    hoverBorderColor: Color = GlassBorderHover,
    accentBorderColor: Color? = null, // Optional left-edge colored accent
    contentPadding: Dp = 24.dp,
    watermarkEmoji: String? = null,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val hoverInteractionSource = remember { MutableInteractionSource() }
    val isHovered by hoverInteractionSource.collectIsHoveredAsState()

    val animatedBorderColor by animateColorAsState(
        targetValue = if (isHovered) hoverBorderColor else borderColor,
        animationSpec = tween(durationMillis = 250),
        label = "card_border"
    )

    var cardModifier = modifier
        .hoverable(hoverInteractionSource)
        .shadow(
            elevation = if (isHovered) 8.dp else 0.dp,
            shape = shape,
            spotColor = GlowGreen.copy(alpha = 0.15f),
            ambientColor = Color.Transparent
        )
        .clip(shape)
        .background(backgroundColor)
        .border(width = 1.dp, color = animatedBorderColor, shape = shape)

    if (onClick != null) {
        cardModifier = cardModifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = androidx.compose.foundation.LocalIndication.current,
            onClick = onClick
        )
    }

    Box(
        modifier = cardModifier
    ) {
        // Optional left accent border
        if (accentBorderColor != null) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .fillMaxWidth(0.004f)
                    .matchParentSize()
                    .background(accentBorderColor)
            )
        }

        if (watermarkEmoji != null) {
            Text(
                text = watermarkEmoji,
                style = MaterialTheme.typography.displayLarge.copy(fontSize = 64.sp),
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 16.dp, end = 16.dp)
                    .alpha(0.06f)
            )
        }
        
        Box(modifier = Modifier.padding(contentPadding).fillMaxWidth()) {
            content()
        }
    }
}
