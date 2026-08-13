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
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.portfolio.ui.components.SectionHeader
import com.example.portfolio.ui.components.fadeInSlideUp
import com.example.portfolio.ui.theme.*

@Composable
fun ProjectsSection(
    modifier: Modifier = Modifier,
    onOpenCaseStudy: (String) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
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
                caseStudyId = "foodbridge",
                onOpenCaseStudy = onOpenCaseStudy,
                emoji = "🍽️",
                gradientColors = listOf(Color(0xFF3DDC84), Color(0xFF58A6FF)),
                isFeatured = true,
                isReversed = false,
                modifier = Modifier.fadeInSlideUp(delayMs = 0)
            )

            ProjectCardItem(
                title = "Agri Connect",
                description = "A direct farmer-to-consumer marketplace enabling agricultural trade without middlemen. Features include real-time pricing, crop cataloging, and order management.",
                tags = listOf("Kotlin", "MVVM", "Retrofit", "Room"),
                caseStudyId = "agriconnect",
                onOpenCaseStudy = onOpenCaseStudy,
                emoji = "🌾",
                gradientColors = listOf(Color(0xFFFF8A65), Color(0xFF3DDC84)),
                isFeatured = false,
                isReversed = true,
                modifier = Modifier.fadeInSlideUp(delayMs = 100)
            )

            ProjectCardItem(
                title = "CareNest",
                description = "An elderly care management application with medication tracking, health monitoring dashboards, and caregiver communication features.",
                tags = listOf("Kotlin", "Compose", "Hilt", "Gemini AI"),
                caseStudyId = "carenest",
                onOpenCaseStudy = onOpenCaseStudy,
                emoji = "❤️",
                gradientColors = listOf(Color(0xFF58A6FF), Color(0xFFFF8A65)),
                isFeatured = false,
                isReversed = false,
                modifier = Modifier.fadeInSlideUp(delayMs = 200)
            )
            
            ProjectCardItem(
                title = "This Portfolio",
                description = "A developer-crafted portfolio site with a terminal-themed design system. Features an embedded AI assistant powered by Google Gemini.",
                tags = listOf("HTML", "CSS", "JavaScript", "KMP"),
                caseStudyId = null,
                onOpenCaseStudy = onOpenCaseStudy,
                emoji = "🌐",
                gradientColors = listOf(Color(0xFF3DDC84), Color(0xFF58A6FF)),
                isFeatured = false,
                isReversed = true,
                modifier = Modifier.fadeInSlideUp(delayMs = 300)
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
    caseStudyId: String?,
    onOpenCaseStudy: (String) -> Unit,
    emoji: String,
    gradientColors: List<Color>,
    isFeatured: Boolean,
    isReversed: Boolean,
    modifier: Modifier = Modifier
) {
    val visualBlock = @Composable {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(SurfaceDark)
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            gradientColors[0].copy(alpha = 0.12f),
                            gradientColors[1].copy(alpha = 0.06f)
                        )
                    )
                )
                .border(1.dp, GlassBorder, RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            // Glow behind the emoji
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .background(
                        gradientColors[0].copy(alpha = 0.15f),
                        shape = RoundedCornerShape(50)
                    )
                    .blur(40.dp)
            )
            Text(
                text = emoji,
                style = MaterialTheme.typography.displayLarge.copy(fontSize = 72.sp),
                modifier = Modifier.alpha(0.35f)
            )
        }
    }

    val contentBlock = @Composable {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            // Featured badge
            if (isFeatured) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(PrimaryGreen.copy(alpha = 0.1f))
                        .border(1.dp, PrimaryGreen.copy(alpha = 0.3f), RoundedCornerShape(50))
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "★ Featured",
                        style = MaterialTheme.typography.labelSmall,
                        color = PrimaryGreen
                    )
                }
            }

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

            if (caseStudyId != null) {
                Text(
                    text = "View Case Study ->",
                    style = MaterialTheme.typography.labelSmall,
                    color = PrimaryGreen,
                    modifier = Modifier
                        .clickable { onOpenCaseStudy(caseStudyId) }
                        .padding(top = 8.dp)
                )
            }
        }
    }

    Row(
        modifier = modifier.fillMaxWidth(),
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
}
