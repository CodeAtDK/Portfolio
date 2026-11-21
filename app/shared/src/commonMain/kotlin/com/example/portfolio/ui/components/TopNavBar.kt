package com.example.portfolio.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.portfolio.model.NavSection
import com.example.portfolio.ui.theme.BackgroundDark
import com.example.portfolio.ui.theme.GlassBorder
import com.example.portfolio.ui.theme.PrimaryGreen
import com.example.portfolio.ui.theme.SurfaceContainerHigh

@Composable
fun TopNavBar(
    modifier: Modifier = Modifier,
    currentSection: NavSection = NavSection.HOME,
    onNavClick: ((String) -> Unit)? = null,
    onSectionSelected: ((NavSection) -> Unit)? = null,
    isDesktop: Boolean = true
) {
    var mobileMenuOpen by remember { mutableStateOf(false) }

    val handleSelection: (NavSection) -> Unit = { section ->
        mobileMenuOpen = false
        onSectionSelected?.invoke(section)
        onNavClick?.invoke(section.title)
    }

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .background(BackgroundDark.copy(alpha = 0.92f))
                .padding(horizontal = if (isDesktop) 24.dp else 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Logo
            Text(
                text = buildAnnotatedString {
                    append("dhruva")
                    withStyle(SpanStyle(color = PrimaryGreen)) {
                        append(".dev")
                    }
                },
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.clickable { handleSelection(NavSection.HOME) }
            )

            if (isDesktop) {
                // Desktop Links
                Row(
                    horizontalArrangement = Arrangement.spacedBy(24.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    NavSection.values().forEach { section ->
                        val isSelected = section == currentSection
                        Text(
                            text = section.title,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                            ),
                            color = if (isSelected) PrimaryGreen else MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .clickable { handleSelection(section) }
                                .padding(horizontal = 6.dp, vertical = 4.dp)
                        )
                    }
                }

                // Status Pill
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(PrimaryGreen.copy(alpha = 0.12f))
                        .border(1.dp, PrimaryGreen.copy(alpha = 0.3f), CircleShape)
                        .padding(horizontal = 12.dp, vertical = 5.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(PrimaryGreen)
                    )
                    Text(
                        text = "open to work",
                        style = MaterialTheme.typography.labelSmall,
                        color = PrimaryGreen
                    )
                }
            } else {
                // Mobile Menu Button
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Mobile Status Dot
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(PrimaryGreen)
                    )
                    IconButton(
                        onClick = { mobileMenuOpen = !mobileMenuOpen },
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            imageVector = if (mobileMenuOpen) Icons.Default.Close else Icons.Default.Menu,
                            contentDescription = if (mobileMenuOpen) "Close Menu" else "Open Menu",
                            tint = PrimaryGreen
                        )
                    }
                }
            }
        }

        // Mobile Dropdown Menu
        AnimatedVisibility(
            visible = !isDesktop && mobileMenuOpen,
            enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
                    .background(SurfaceContainerHigh)
                    .border(1.dp, GlassBorder, RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
                    .padding(vertical = 12.dp, horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                NavSection.values().forEach { section ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .clickable { handleSelection(section) }
                            .padding(horizontal = 12.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = section.title,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = if (section == currentSection) FontWeight.Bold else FontWeight.Normal,
                                fontSize = 15.sp
                            ),
                            color = if (section == currentSection) PrimaryGreen else MaterialTheme.colorScheme.onSurface
                        )
                        if (section == NavSection.CONTACT) {
                            Text(
                                text = "Get in touch →",
                                style = MaterialTheme.typography.labelSmall,
                                color = PrimaryGreen
                            )
                        }
                    }
                }
            }
        }
    }
}

