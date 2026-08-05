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
fun ProjectsSection(
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
                text = "PROJECTS",
                style = MaterialTheme.typography.labelSmall,
                color = TertiaryCyan
            )
            Text(
                text = "Real-world apps I've built.",
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Each project represents a hands-on journey through modern Android development - from architecture and APIs to polished UI with Jetpack Compose and Multiplatform capabilities.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // Projects List
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            ProjectCardItem(
                category = "FOOD RESCUE PLATFORM",
                categoryColor = PrimaryBlue,
                title = "FoodBridge",
                description = "A platform connecting surplus food donors with NGOs to reduce food waste and feed communities in need. Featuring real-time matching with location-based services.",
                tags = listOf("Kotlin", "Jetpack Compose", "Firebase", "Maps API", "Coroutines")
            )

            ProjectCardItem(
                category = "FARMER MARKETPLACE",
                categoryColor = TertiaryCyan,
                title = "Agri Connect",
                description = "A direct farmer-to-consumer marketplace enabling agricultural trade without middlemen. Features include real-time pricing dashboards, crop cataloging, and secure order management.",
                tags = listOf("Kotlin", "MVVM Architecture", "Retrofit", "Room DB", "REST APIs")
            )

            ProjectCardItem(
                category = "HEALTHCARE & ELDERLY CARE",
                categoryColor = SecondaryPurple,
                title = "CareNest",
                description = "An elderly care management application with medication tracking, health monitoring dashboards, and caregiver communication tools powered by AI assistant insights.",
                tags = listOf("Kotlin", "Compose", "Hilt DI", "Gemini AI", "StateFlow")
            )

            ProjectCardItem(
                category = "CROSS-PLATFORM PORTFOLIO",
                categoryColor = PrimaryBlue,
                title = "Compose Multiplatform Portfolio",
                description = "A reactive, shader-styled portfolio application compiled natively for Android, iOS, and Web WASM using shared Kotlin logic and the Synthetic Noir design system.",
                tags = listOf("KMP", "Compose Multiplatform", "WASM", "iOS SwiftUI Host", "Canvas Graphics")
            )
        }
    }
}

@Composable
private fun ProjectCardItem(
    category: String,
    categoryColor: androidx.compose.ui.graphics.Color,
    title: String,
    description: String,
    tags: List<String>
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
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface
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
