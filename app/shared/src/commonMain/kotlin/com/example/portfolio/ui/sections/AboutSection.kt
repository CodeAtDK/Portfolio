package com.example.portfolio.ui.sections

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AutoStories
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.portfolio.ui.components.PortfolioCard
import com.example.portfolio.ui.components.SectionHeader
import com.example.portfolio.ui.components.TechChip
import com.example.portfolio.ui.components.fadeInSlideUp
import com.example.portfolio.ui.theme.*

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AboutSection(
    modifier: Modifier = Modifier
) {
    val isMobile = LocalIsMobile.current

    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(if (isMobile) 24.dp else 40.dp)
    ) {
        SectionHeader(number = "01", title = "About")

        // Main Narrative Story Card (Matching SkillGroupCard styling)
        PortfolioCard(
            modifier = Modifier
                .fillMaxWidth()
                .fadeInSlideUp(durationMs = 600, delayMs = 100),
            accentBorderColor = AccentGreen.copy(alpha = 0.5f)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    text = "DEVELOPER PROFILE & BACKGROUND",
                    style = MaterialTheme.typography.labelSmall,
                    color = PrimaryGreen
                )

                Text(
                    text = "Building Polished Android & Multiplatform Experiences",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = if (isMobile) 19.sp else 22.sp
                    ),
                    color = MaterialTheme.colorScheme.onSurface
                )

                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        text = "I'm an Android Developer who has been building mobile applications since August 2023. I enjoy turning ideas into intuitive, polished Android experiences that solve real-world problems.",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            lineHeight = 26.sp
                        ),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "I graduated with a B.Tech in Electronics & Communication Engineering from Jaypee Institute of Information Technology, Noida (2022–2026). Android development keeps me engaged because it is both complex and constantly evolving.",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            lineHeight = 26.sp
                        ),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = buildAnnotatedString {
                            append("Right now I'm diving deep into ")
                            withStyle(
                                SpanStyle(
                                    color = MaterialTheme.colorScheme.onSurface,
                                    fontWeight = FontWeight.SemiBold
                                )
                            ) {
                                append("Kotlin Multiplatform")
                            }
                            append(" — bridging the gap between Android, iOS, and Web while maximizing code sharing and maintaining native performance.")
                        },
                        style = MaterialTheme.typography.bodyLarge.copy(
                            lineHeight = 26.sp
                        ),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                // Profile Highlights Chips
                FlowRow(
                    modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TechChip(text = "Android (Since 2023)")
                    TechChip(text = "B.Tech ECE (JIIT Noida)")
                    TechChip(text = "Kotlin Multiplatform")
                    TechChip(text = "Jetpack Compose")
                    TechChip(text = "Open to Roles")
                }
            }
        }

        // Philosophy Cards (Responsive: 3-column Row on Desktop, Vertical Column on Mobile)
        if (isMobile) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AboutPhilosophyCard(
                    category = "DESIGN PHILOSOPHY",
                    categoryColor = PrimaryGreen,
                    accentColor = AccentGreen,
                    icon = Icons.Outlined.Palette,
                    title = "Design for People",
                    description = "Creating clean, intuitive UI that feels natural and reduces cognitive load for the end user.",
                    tags = listOf("User-Centric UI", "Intuitive UX", "Fluid Motion"),
                    modifier = Modifier.fillMaxWidth().fadeInSlideUp(durationMs = 600, delayMs = 200)
                )
                AboutPhilosophyCard(
                    category = "ENGINEERING FOCUS",
                    categoryColor = SecondaryWarm,
                    accentColor = AccentOrange,
                    icon = Icons.Outlined.Build,
                    title = "Solve Real Problems",
                    description = "Focusing on practical mobile experiences that address actual user needs effectively.",
                    tags = listOf("Clean Architecture", "Offline First", "Real Impact"),
                    modifier = Modifier.fillMaxWidth().fadeInSlideUp(durationMs = 600, delayMs = 300)
                )
                AboutPhilosophyCard(
                    category = "CONTINUOUS LEARNING",
                    categoryColor = TertiaryBlue,
                    accentColor = AccentBlue,
                    icon = Icons.Outlined.AutoStories,
                    title = "Keep Learning",
                    description = "Actively learning new tools, architectures, and approaches to stay adaptable in a fast-moving ecosystem.",
                    tags = listOf("KMP & CMP", "AI Workflows", "Modern Tooling"),
                    modifier = Modifier.fillMaxWidth().fadeInSlideUp(durationMs = 600, delayMs = 400)
                )
            }
        } else {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AboutPhilosophyCard(
                    category = "DESIGN PHILOSOPHY",
                    categoryColor = PrimaryGreen,
                    accentColor = AccentGreen,
                    icon = Icons.Outlined.Palette,
                    title = "Design for People",
                    description = "Creating clean, intuitive UI that feels natural and reduces cognitive load for the end user.",
                    tags = listOf("User-Centric UI", "Intuitive UX", "Fluid Motion"),
                    modifier = Modifier.weight(1f).fillMaxHeight().fadeInSlideUp(durationMs = 600, delayMs = 200)
                )
                AboutPhilosophyCard(
                    category = "ENGINEERING FOCUS",
                    categoryColor = SecondaryWarm,
                    accentColor = AccentOrange,
                    icon = Icons.Outlined.Build,
                    title = "Solve Real Problems",
                    description = "Focusing on practical mobile experiences that address actual user needs effectively.",
                    tags = listOf("Clean Architecture", "Offline First", "Real Impact"),
                    modifier = Modifier.weight(1f).fillMaxHeight().fadeInSlideUp(durationMs = 600, delayMs = 300)
                )
                AboutPhilosophyCard(
                    category = "CONTINUOUS LEARNING",
                    categoryColor = TertiaryBlue,
                    accentColor = AccentBlue,
                    icon = Icons.Outlined.AutoStories,
                    title = "Keep Learning",
                    description = "Actively learning new tools, architectures, and approaches to stay adaptable in a fast-moving ecosystem.",
                    tags = listOf("KMP & CMP", "AI Workflows", "Modern Tooling"),
                    modifier = Modifier.weight(1f).fillMaxHeight().fadeInSlideUp(durationMs = 600, delayMs = 400)
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun AboutPhilosophyCard(
    category: String,
    categoryColor: Color,
    accentColor: Color,
    icon: ImageVector,
    title: String,
    description: String,
    tags: List<String>,
    modifier: Modifier = Modifier
) {
    PortfolioCard(
        modifier = modifier,
        accentBorderColor = accentColor.copy(alpha = 0.5f)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = category,
                    style = MaterialTheme.typography.labelSmall,
                    color = categoryColor
                )
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = accentColor,
                    modifier = Modifier.size(18.dp)
                )
            }

            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium.copy(
                    lineHeight = 22.sp
                ),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            FlowRow(
                modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                tags.forEach { tag ->
                    TechChip(text = tag)
                }
            }
        }
    }
}
