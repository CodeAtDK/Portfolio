package com.example.portfolio.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.portfolio.model.NavSection
import com.example.portfolio.ui.theme.PrimaryGreen

@Composable
fun TopNavBar(
    currentSection: NavSection,
    onSectionSelected: (NavSection) -> Unit,
    modifier: Modifier = Modifier,
    isDesktop: Boolean = true
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(MaterialTheme.colorScheme.background.copy(alpha = 0.85f))
            .padding(horizontal = 24.dp),
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
            color = MaterialTheme.colorScheme.onBackground
        )

        if (isDesktop) {
            // Desktop Links
            Row(
                horizontalArrangement = Arrangement.spacedBy(28.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                NavSection.values().forEach { section ->
                    val isSelected = section == currentSection
                    Text(
                        text = section.title,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Medium
                        ),
                        color = if (isSelected) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.clickable { onSectionSelected(section) }
                    )
                }
            }

            // Status Pill
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(horizontal = 14.dp, vertical = 5.dp)
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
            // Mobile hamburger (simplified text for now)
            Text(
                text = "MENU",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
