package com.example.portfolio.ui.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.portfolio.ui.components.PortfolioCard
import com.example.portfolio.ui.components.SectionHeader
import com.example.portfolio.ui.theme.*

@Composable
fun ProjectsSection(
    modifier: Modifier = Modifier,
    isDesktop: Boolean = true
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 40.dp),
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        SectionHeader(number = "03", title = "Projects")

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(80.dp)
        ) {
            ProjectCardItem(
                title = "FoodBridge",
                description = "A platform connecting surplus food donors with NGOs to reduce food waste and feed communities in need. Real-time matching with location-based services.",
                tags = listOf("Kotlin", "Compose", "Firebase", "Maps API"),
                linkUrl = "https://dhruvasho.github.io/foodbridge-case-study.html",
                emoji = "🍽️",
                gradient = ProjectGradient1,
                isDesktop = isDesktop,
                isReversed = false
            )

            ProjectCardItem(
                title = "Agri Connect",
                description = "A direct farmer-to-consumer marketplace enabling agricultural trade without middlemen. Features include real-time pricing, crop cataloging, and order management.",
                tags = listOf("Kotlin", "MVVM", "Retrofit", "Room"),
                linkUrl = "https://dhruvasho.github.io/agriconnect-case-study.html",
                emoji = "🌾",
                gradient = ProjectGradient2,
                isDesktop = isDesktop,
                isReversed = true
            )

            ProjectCardItem(
                title = "CareNest",
                description = "An elderly care management application with medication tracking, health monitoring dashboards, and caregiver communication features.",
                tags = listOf("Kotlin", "Compose", "Hilt", "Gemini AI"),
                linkUrl = "https://dhruvasho.github.io/carenest-case-study.html",
                emoji = "❤️",
                gradient = ProjectGradient3,
                isDesktop = isDesktop,
                isReversed = false
            )
            
            ProjectCardItem(
                title = "This Portfolio",
                description = "A developer-crafted portfolio site with a terminal-themed design system. Features an embedded AI assistant powered by Google Gemini.",
                tags = listOf("HTML", "CSS", "JavaScript", "KMP"),
                linkUrl = null,
                emoji = "🌐",
                gradient = ProjectGradient4,
                isDesktop = isDesktop,
                isReversed = true
            )
        }
    }
}

@OptIn(androidx.compose.foundation.layout.ExperimentalLayoutApi::class)
@Composable
private fun ProjectCardItem(
    title: String,
    description: String,
    tags: List<String>,
    linkUrl: String?,
    emoji: String,
    gradient: Brush,
    isDesktop: Boolean,
    isReversed: Boolean
) {
    val uriHandler = LocalUriHandler.current

    val visualBlock = @Composable {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(SurfaceDark)
                .background(gradient)
                .border(1.dp, BorderDark, RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = emoji,
                style = MaterialTheme.typography.displayLarge.copy(fontSize = 72.sp),
                modifier = Modifier.alpha(0.2f)
            )
        }
    }

    val contentBlock = @Composable {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            
            // Tags with "//" separator
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                tags.forEachIndexed { index, tag ->
                    Text(
                        text = tag,
                        style = MaterialTheme.typography.labelSmall,
                        color = TextMuted
                    )
                    if (index < tags.size - 1) {
                        Text(
                            text = "//",
                            style = MaterialTheme.typography.labelSmall,
                            color = PrimaryGreen
                        )
                    }
                }
            }

            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            if (linkUrl != null) {
                Text(
                    text = "View Case Study ->",
                    style = MaterialTheme.typography.labelSmall,
                    color = PrimaryGreen,
                    modifier = Modifier
                        .clickable { uriHandler.openUri(linkUrl) }
                        .padding(top = 8.dp)
                )
            }
        }
    }

    if (isDesktop) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(48.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isReversed) {
                Box(modifier = Modifier.weight(1f)) { contentBlock() }
                Box(modifier = Modifier.weight(1.1f)) { visualBlock() }
            } else {
                Box(modifier = Modifier.weight(1.1f)) { visualBlock() }
                Box(modifier = Modifier.weight(1f)) { contentBlock() }
            }
        }
    } else {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            visualBlock()
            contentBlock()
        }
    }
}
