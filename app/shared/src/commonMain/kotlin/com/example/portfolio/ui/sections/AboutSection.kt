package com.example.portfolio.ui.sections

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.portfolio.ui.components.PortfolioCard
import com.example.portfolio.ui.components.SectionHeader
import com.example.portfolio.ui.theme.*

@Composable
fun AboutSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 40.dp),
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        SectionHeader(number = "01", title = "About")

        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text(
                text = "I'm an Android Developer who has been building mobile applications since August 2023. I enjoy turning ideas into intuitive, polished Android experiences that solve real-world problems.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "I graduated with a B.Tech in Electronics & Communication Engineering from Jaypee Institute of Information Technology, Noida (2022–2026). Android development keeps me engaged because it is both complex and constantly evolving.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "Right now I'm diving deep into Kotlin Multiplatform — bridging the gap between Android, iOS, and Web while maximizing code sharing and maintaining native performance.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // Philosophy Cards (Responsive Row/Column could be used, keeping simple Column for now)
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            PhilosophyItem(
                icon = "🎨",
                title = "Design for People",
                description = "Creating clean, intuitive UI that feels natural and reduces cognitive load for the end user."
            )
            PhilosophyItem(
                icon = "🛠️",
                title = "Solve Real Problems",
                description = "Focusing on practical mobile experiences that address actual user needs effectively."
            )
            PhilosophyItem(
                icon = "📚",
                title = "Keep Learning",
                description = "Actively learning new tools, architectures, and approaches to stay adaptable in a fast-moving ecosystem."
            )
        }
    }
}

@Composable
private fun PhilosophyItem(icon: String, title: String, description: String) {
    PortfolioCard(modifier = Modifier.fillMaxWidth()) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(text = icon, style = MaterialTheme.typography.headlineMedium)
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
