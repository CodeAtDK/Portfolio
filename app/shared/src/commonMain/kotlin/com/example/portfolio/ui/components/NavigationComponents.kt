package com.example.portfolio.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.portfolio.model.NavSection
import com.example.portfolio.ui.theme.*

@Composable
fun DesktopSideNav(
    currentSection: NavSection,
    onSectionSelected: (NavSection) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(80.dp)
            .fillMaxHeight()
            .background(SurfaceDark.copy(alpha = 0.90f))
            .border(width = 1.dp, color = GlassBorder)
            .padding(vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Brand Logo Badge
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceContainerHigh),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "D",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = PrimaryBlue
                )
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        NavSection.values().forEach { section ->
            val isSelected = section == currentSection
            val bg = if (isSelected) MaterialTheme.colorScheme.primaryContainer else Color.Transparent
            val iconColor = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else OnSurfaceVariant

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(bg)
                    .clickable { onSectionSelected(section) },
                contentAlignment = Alignment.Center
            ) {
                SectionIcon(section = section, tint = iconColor)
            }
        }
    }
}

@Composable
fun MobileBottomNav(
    currentSection: NavSection,
    onSectionSelected: (NavSection) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(SurfaceDark.copy(alpha = 0.95f))
            .border(width = 1.dp, color = GlassBorder)
            .padding(horizontal = 4.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        NavSection.values().forEach { section ->
            val isSelected = section == currentSection
            val bg = if (isSelected) SecondaryContainer.copy(alpha = 0.35f) else Color.Transparent
            val iconColor = if (isSelected) PrimaryBlue else OnSurfaceVariant

            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(bg)
                    .clickable { onSectionSelected(section) }
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SectionIcon(section = section, tint = iconColor)
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = section.title.take(5),
                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                    color = iconColor
                )
            }
        }
    }
}

@Composable
private fun SectionIcon(section: NavSection, tint: Color) {
    val symbol = when (section) {
        NavSection.HOME -> "01"
        NavSection.ABOUT -> "02"
        NavSection.SKILLS -> "{ }"
        NavSection.PROJECTS -> "</>"
        NavSection.EXPERIENCE -> "05"
        NavSection.AI_WORKSPACE -> "AI"
        NavSection.CONTACT -> "@"
    }
    Text(
        text = symbol,
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
        color = tint
    )
}
