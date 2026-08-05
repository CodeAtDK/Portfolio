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
fun ExperienceSection(
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
                text = "EXPERIENCE",
                style = MaterialTheme.typography.labelSmall,
                color = TertiaryCyan
            )
            Text(
                text = "My professional journey.",
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "From academic foundations in engineering to hands-on Android and Multiplatform development - every step has sharpened my product intuition and technical craftsmanship.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // Experience Items
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            ExperienceCardItem(
                timeframe = "2022 - 2026",
                title = "B.Tech - Electronics & Communication Engineering",
                subtitle = "Jaypee Institute of Information Technology, Noida",
                description = "Developed a strong foundation in programming, algorithms, and embedded systems alongside specialization in modern Android mobile application architecture.",
                tags = listOf("System Design", "Algorithms", "Kotlin", "Engineering")
            )

            ExperienceCardItem(
                timeframe = "Aug 2023 - Present",
                title = "Android & KMP Developer",
                subtitle = "Self-Directed Open Source Projects",
                description = "Built production-quality mobile applications using Kotlin, Jetpack Compose, MVVM, Hilt, and Firebase. Implemented real-time features, REST APIs, and Gemini AI integration across multiple specialized domains including healthcare and agriculture.",
                tags = listOf("3+ Real-World Apps", "Jetpack Compose", "Gemini AI Integration", "Architecture")
            )

            ExperienceCardItem(
                timeframe = "Current Focus",
                title = "Exploring Kotlin Multiplatform (KMP)",
                subtitle = "Expanding Beyond Single-Platform Mobile",
                description = "Actively adopting KMP and Compose Multiplatform to unify UI design systems and backend business logic across Android, iOS, and Web without sacrificing native UX fluency.",
                tags = listOf("KMP", "WASM", "iOS SwiftUI Host", "Shared UI")
            )
        }
    }
}

@Composable
private fun ExperienceCardItem(
    timeframe: String,
    title: String,
    subtitle: String,
    description: String,
    tags: List<String>
) {
    GlassCard(
        modifier = Modifier.fillMaxWidth(),
        borderColor = PrimaryBlue.copy(alpha = 0.25f)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(
                text = timeframe,
                style = MaterialTheme.typography.labelSmall,
                color = TertiaryCyan
            )
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium.copy(color = PrimaryBlue),
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            FlowRowWrapper {
                tags.forEach { tag ->
                    TechChip(text = tag)
                }
            }
        }
    }
}
