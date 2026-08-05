package com.example.portfolio.ui.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.portfolio.model.NavSection
import com.example.portfolio.ui.components.GlassCard
import com.example.portfolio.ui.components.TechChip
import com.example.portfolio.ui.theme.*

@Composable
fun HomeSection(
    onNavigate: (NavSection) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        // Status & Location Tags
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TechChip(
                text = "LOC: Pune, India",
                textColor = TertiaryCyan
            )
            TechChip(
                text = "STATUS: Available for roles",
                textColor = Color(0xFF4CAF50)
            )
        }

        // Hero Headline
        Text(
            text = buildAnnotatedString {
                append("Building ")
                withStyle(SpanStyle(color = PrimaryBlue)) {
                    append("Intelligent")
                }
                append("\nAndroid Experiences.")
            },
            style = MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        // Hero Bio Paragraph
        Text(
            text = "I'm Dhruva Khatavkar, an Android Developer specializing in Jetpack Compose, Kotlin, and AI integrations. I craft fluid, performant applications with a deep focus on modern architectural patterns.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.widthIn(max = 680.dp)
        )

        // Action Buttons
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { onNavigate(NavSection.PROJECTS) },
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryContainer),
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 14.dp)
            ) {
                Text(
                    text = "Explore My Work",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                    color = Color.White
                )
            }

            OutlinedButton(
                onClick = { onNavigate(NavSection.AI_WORKSPACE) },
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, GlassBorderHover),
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 14.dp)
            ) {
                Text(
                    text = "Meet Dhruva AI",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                    color = PrimaryBlue
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // AI Assistant Teaser Card
        GlassCard(
            modifier = Modifier.fillMaxWidth(),
            animateGlow = true,
            onClick = { onNavigate(NavSection.AI_WORKSPACE) }
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "// ASK DHRUVA AI",
                        style = MaterialTheme.typography.labelSmall,
                        color = TertiaryCyan
                    )
                    Text(
                        text = "Open Interactive Chat ->",
                        style = MaterialTheme.typography.labelSmall,
                        color = PrimaryBlue
                    )
                }

                Text(
                    text = "Have questions about my technical stack, architecture philosophy, or Kotlin experience?",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "An interactive AI model is trained on my professional background and engineering case studies. Try asking it anything about my projects or codebase skills!",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
