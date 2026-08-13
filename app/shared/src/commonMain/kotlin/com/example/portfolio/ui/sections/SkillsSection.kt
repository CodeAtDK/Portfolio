package com.example.portfolio.ui.sections

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.portfolio.ui.components.PortfolioCard
import com.example.portfolio.ui.components.SectionHeader
import com.example.portfolio.ui.components.TechChip
import com.example.portfolio.ui.components.fadeInSlideUp
import com.example.portfolio.ui.theme.*

@Composable
fun SkillsSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        SectionHeader(number = "02", title = "Skills")

        Text(
            text = "A growing Android-focused toolkit shaped by hands-on projects, API integrations, real-time features, modern UI development, and cross-platform learning.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        // Bento Grid Desktop Layout
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            // Row 1
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                SkillGroupCard(
                    category = "ANDROID & CROSS-PLATFORM",
                    categoryColor = PrimaryGreen,
                    accentColor = AccentGreen,
                    title = "Core Development",
                    skills = listOf("Kotlin", "Java", "Jetpack Compose", "XML", "MVVM / MVI", "Clean Architecture"),
                    watermark = "📱",
                    modifier = Modifier.weight(2f).fadeInSlideUp(delayMs = 0)
                )
                SkillGroupCard(
                    category = "CORE LIBRARIES",
                    categoryColor = SecondaryWarm,
                    accentColor = AccentOrange,
                    title = "Architecture & Perf",
                    skills = listOf("Coroutines & Flow", "Hilt / Dagger", "Room DB", "Retrofit", "Navigation", "WorkManager", "StateFlow"),
                    watermark = "⚙️",
                    modifier = Modifier.weight(1f).fadeInSlideUp(delayMs = 100)
                )
            }
            
            // Row 2
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                SkillGroupCard(
                    category = "INTEGRATIONS",
                    categoryColor = TertiaryBlue,
                    accentColor = AccentBlue,
                    title = "Backend & Cloud",
                    skills = listOf("Firebase", "Firestore", "REST APIs", "Gemini AI", "Postman"),
                    watermark = "☁️",
                    modifier = Modifier.weight(1f).fadeInSlideUp(delayMs = 150)
                )
                SkillGroupCard(
                    category = "WORKFLOW",
                    categoryColor = TextDim,
                    accentColor = AccentMuted,
                    title = "Tools & Practices",
                    skills = listOf("Android Studio", "Git / GitHub", "Material Design 3", "JUnit", "Agile / Scrum"),
                    watermark = "🔧",
                    modifier = Modifier.weight(1f).fadeInSlideUp(delayMs = 200)
                )
            }

            // Row 3
            SkillGroupCard(
                category = "LANGUAGES & MULTIPLATFORM",
                categoryColor = PrimaryGreen,
                accentColor = AccentGreen,
                title = "Growing Beyond Android",
                description = "Comfortable across the modern stack and exploring cross-platform delivery with KMP/CMP.",
                skills = listOf("KMP", "CMP", "Ktor", "C++ / C", "Python", "SQL"),
                watermark = "🌍",
                modifier = Modifier.fillMaxWidth().fadeInSlideUp(delayMs = 250)
            )
        }
    }
}

@OptIn(androidx.compose.foundation.layout.ExperimentalLayoutApi::class)
@Composable
private fun SkillGroupCard(
    category: String,
    categoryColor: Color,
    accentColor: Color,
    title: String,
    description: String? = null,
    skills: List<String>,
    watermark: String? = null,
    modifier: Modifier = Modifier
) {
    PortfolioCard(
        modifier = modifier,
        watermarkEmoji = watermark,
        accentBorderColor = accentColor.copy(alpha = 0.5f) // Colored left accent
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(
                text = category,
                style = MaterialTheme.typography.labelSmall,
                color = categoryColor
            )
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            if (description != null) {
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                skills.forEach { skill ->
                    TechChip(text = skill)
                }
            }
        }
    }
}
