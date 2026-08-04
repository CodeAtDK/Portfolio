package com.example.portfolio.ui.sections

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.portfolio.ui.components.GlassCard
import com.example.portfolio.ui.components.TechChip
import com.example.portfolio.ui.theme.*

@Composable
fun AboutSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        // Header
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(
                text = "ABOUT ME",
                style = MaterialTheme.typography.labelSmall,
                color = TertiaryCyan
            )
            Text(
                text = "Building Android experiences through curiosity and constant learning.",
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "I'm Dhruva Khatavkar, an Android Developer who has been building mobile applications since August 2023. I enjoy turning ideas into intuitive, polished Android experiences that solve real-world problems. Android development keeps me engaged because it is both complex and constantly evolving.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // Development Philosophy Section
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text(
                text = "DEVELOPMENT PHILOSOPHY",
                style = MaterialTheme.typography.labelSmall,
                color = SecondaryPurple
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                PhilosophyItem(
                    title = "Design for People",
                    description = "Creating clean, intuitive UI that feels natural and reduces cognitive load for the end user."
                )
                PhilosophyItem(
                    title = "Solve Real Problems",
                    description = "Focusing on practical mobile application experiences that address actual user needs effectively."
                )
                PhilosophyItem(
                    title = "Keep Learning",
                    description = "Actively learning new tools, architectures, and approaches to stay adaptable in a fast-moving ecosystem."
                )
            }
        }

        // Currently Exploring (KMP Focus Card)
        GlassCard(
            modifier = Modifier.fillMaxWidth(),
            animateGlow = true,
            backgroundColor = SurfaceContainerLow.copy(alpha = 0.85f),
            borderColor = PrimaryBlue.copy(alpha = 0.35f)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    text = "CURRENTLY EXPLORING",
                    style = MaterialTheme.typography.labelSmall,
                    color = PrimaryBlue
                )
                Text(
                    text = "Kotlin Multiplatform (KMP)",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Bridging the gap between Android, iOS, and Web while maximizing code sharing and maintaining native performance and fluid UI interactions.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                FlowRowWrapper {
                    TechChip(text = "Compose Multiplatform", textColor = PrimaryBlue)
                    TechChip(text = "Shared Logic", textColor = SecondaryPurple)
                    TechChip(text = "Ktor & Coroutines", textColor = TertiaryCyan)
                }
            }
        }

        // Core Strengths
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(
                text = "CORE STRENGTHS",
                style = MaterialTheme.typography.labelSmall,
                color = TertiaryCyan
            )
            FlowRowWrapper {
                TechChip(text = "Android SDK & Kotlin", textColor = PrimaryBlue)
                TechChip(text = "UI Design & Material 3")
                TechChip(text = "Problem Solving")
                TechChip(text = "REST API & Retrofit")
                TechChip(text = "State Management")
            }
        }
    }
}

@Composable
private fun PhilosophyItem(title: String, description: String) {
    GlassCard(modifier = Modifier.fillMaxWidth(), contentPadding = 20.dp) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun FlowRowWrapper(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    @OptIn(androidx.compose.foundation.layout.ExperimentalLayoutApi::class)
    androidx.compose.foundation.layout.FlowRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        content()
    }
}
