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
fun SkillsSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        // Header
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(
                text = "TECHNICAL SKILLS",
                style = MaterialTheme.typography.labelSmall,
                color = TertiaryCyan
            )
            Text(
                text = "Tools I use to build Android experiences.",
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "A growing Android-focused toolkit shaped by hands-on projects, API integrations, real-time features, modern UI development, and cross-platform learning.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // Bento Grid Items
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Card 1: Android Development
            SkillGroupCard(
                category = "HANDS-ON PROJECT EXPERIENCE",
                categoryColor = PrimaryBlue,
                title = "Android Development",
                skills = listOf("Kotlin", "Android SDK", "Jetpack Compose", "MVVM", "Navigation")
            )

            // Card 2: Architecture & Perf
            SkillGroupCard(
                category = "STRONG FOUNDATION",
                categoryColor = SecondaryPurple,
                title = "Architecture & Performance",
                skills = listOf("Coroutines", "Flow", "Hilt DI", "Room Database", "StateFlow")
            )

            // Card 3: Backend & Cloud
            SkillGroupCard(
                category = "INTEGRATIONS",
                categoryColor = TertiaryCyan,
                title = "Backend & Cloud Services",
                skills = listOf("Firebase", "REST APIs", "Retrofit", "Gemini AI SDK", "JSON Parsing")
            )

            // Card 4: Product UI
            SkillGroupCard(
                category = "DESIGN FOCUS",
                categoryColor = PrimaryBlue,
                title = "Product UI & Design",
                skills = listOf("Figma to Compose", "Material 3 Design", "Accessibility", "Animations", "Responsive Layouts")
            )

            // Card 5: KMP (Featured)
            SkillGroupCard(
                category = "CURRENTLY EXPLORING",
                categoryColor = TertiaryCyan,
                title = "Growing Beyond Android (KMP)",
                skills = listOf("Kotlin Multiplatform", "Compose Multiplatform", "Ktor Client", "WASM / Web", "iOS SwiftUI Host")
            )
        }
    }
}

@Composable
private fun SkillGroupCard(
    category: String,
    categoryColor: androidx.compose.ui.graphics.Color,
    title: String,
    skills: List<String>
) {
    GlassCard(modifier = Modifier.fillMaxWidth()) {
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
            FlowRowWrapper {
                skills.forEach { skill ->
                    TechChip(text = skill)
                }
            }
        }
    }
}
