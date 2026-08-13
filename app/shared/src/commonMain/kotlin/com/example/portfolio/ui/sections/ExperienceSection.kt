package com.example.portfolio.ui.sections

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.portfolio.ui.components.PortfolioCard
import com.example.portfolio.ui.components.SectionHeader
import com.example.portfolio.ui.components.TechChip
import com.example.portfolio.ui.components.fadeInSlideUp
import com.example.portfolio.ui.components.rememberPulseAlpha
import com.example.portfolio.ui.theme.*

@Composable
fun ExperienceSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        SectionHeader(number = "04", title = "Experience")

        Box(
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp)
        ) {
            // Background Timeline Line — gradient from green to transparent
            Box(
                modifier = Modifier
                    .width(2.dp)
                    .fillMaxHeight()
                    .padding(top = 8.dp, bottom = 8.dp)
                    .background(TimelineGradient)
                    .offset(x = 5.dp)
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(48.dp)
            ) {
                ExperienceCardItem(
                    timeframe = "Oct 2024 – Jan 2025 • Remote",
                    title = "Android Developer Intern",
                    subtitle = "ADM Education and Welfare Society",
                    bullets = listOf(
                        "Built a modular Android application in Kotlin, following MVVM architecture, to manage internship placements for a large student base.",
                        "Designed responsive UIs with Jetpack Compose and structured state with Coroutines/Flow, improving user navigation flow by 25%.",
                        "Integrated Firebase Realtime Database and Auth with Hilt-based dependency injection to handle concurrent user sessions and secure data storage."
                    ),
                    tags = listOf("Kotlin", "Jetpack Compose", "MVVM", "Hilt", "Firebase"),
                    isCurrent = true,
                    modifier = Modifier.fadeInSlideUp(delayMs = 0)
                )

                ExperienceCardItem(
                    timeframe = "2022 – 2026",
                    title = "B.Tech in Electronics & Communication",
                    subtitle = "Jaypee Institute of Information Technology, Noida",
                    description = "Relevant Coursework: Data Structures, DBMS, OS, OOPS",
                    tags = emptyList(),
                    isCurrent = false,
                    modifier = Modifier.fadeInSlideUp(delayMs = 150)
                )

                ExperienceCardItem(
                    timeframe = "2019 – 2021",
                    title = "Higher Secondary Education (PCM)",
                    subtitle = "Late M.E Bhore Junior College, Padali",
                    tags = emptyList(),
                    isCurrent = false,
                    modifier = Modifier.fadeInSlideUp(delayMs = 300)
                )
            }
        }
    }
}

@OptIn(androidx.compose.foundation.layout.ExperimentalLayoutApi::class)
@Composable
private fun ExperienceCardItem(
    timeframe: String,
    title: String,
    subtitle: String,
    description: String? = null,
    bullets: List<String> = emptyList(),
    tags: List<String>,
    isCurrent: Boolean,
    modifier: Modifier = Modifier
) {
    val pulseAlpha = if (isCurrent) rememberPulseAlpha() else 1f

    Row(modifier = modifier.fillMaxWidth()) {
        // Timeline Dot Indicator
        Box(
            modifier = Modifier.padding(top = 8.dp)
        ) {
            if (isCurrent) {
                // Pulsing glow ring behind the active dot
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .offset(x = (-6).dp, y = (-6).dp)
                        .alpha(pulseAlpha * 0.4f)
                        .shadow(8.dp, CircleShape, spotColor = PrimaryGreen)
                        .clip(CircleShape)
                        .background(PrimaryGreen.copy(alpha = 0.2f))
                )
            }
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .clip(CircleShape)
                    .background(if (isCurrent) PrimaryGreen else SurfaceDark)
                    .border(2.dp, if (isCurrent) PrimaryGreen else PrimaryGreenDim, CircleShape)
            )
        }
        
        Spacer(modifier = Modifier.width(20.dp))
        
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = timeframe,
                style = MaterialTheme.typography.labelSmall,
                color = SecondaryWarm,
                modifier = Modifier.padding(bottom = 6.dp)
            )
            
            PortfolioCard(
                modifier = Modifier.fillMaxWidth(),
                borderColor = if (isCurrent) PrimaryGreenDim.copy(alpha = 0.3f) else GlassBorder
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextMuted
                    )
                    
                    if (description != null) {
                        Text(
                            text = description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    
                    if (bullets.isNotEmpty()) {
                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            bullets.forEach { bullet ->
                                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                    Text("•", color = PrimaryGreen.copy(alpha = 0.6f))
                                    Text(
                                        text = bullet,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    }
                    
                    if (tags.isNotEmpty()) {
                        FlowRow(
                            modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            tags.forEach { tag ->
                                TechChip(text = tag)
                            }
                        }
                    }
                }
            }
        }
    }
}
