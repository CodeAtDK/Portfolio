package com.example.portfolio.ui.sections

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.portfolio.model.NavSection
import com.example.portfolio.ui.components.TerminalMockup
import com.example.portfolio.ui.theme.*

@Composable
fun HomeSection(
    onNavigate: (NavSection) -> Unit,
    modifier: Modifier = Modifier,
    isDesktop: Boolean = true
) {
    if (isDesktop) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 40.dp),
            horizontalArrangement = Arrangement.spacedBy(56.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HomeContent(
                onNavigate = onNavigate,
                modifier = Modifier.weight(1.15f)
            )
            TerminalMockup(
                modifier = Modifier.weight(0.85f)
            )
        }
    } else {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 40.dp),
            verticalArrangement = Arrangement.spacedBy(48.dp)
        ) {
            HomeContent(onNavigate = onNavigate)
            TerminalMockup(modifier = Modifier.fillMaxWidth())
        }
    }
}

@Composable
private fun HomeContent(
    onNavigate: (NavSection) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "Android Developer • Kotlin • Jetpack Compose",
            style = MaterialTheme.typography.labelSmall,
            color = SecondaryWarm
        )

        Text(
            text = buildAnnotatedString {
                append("Building ")
                withStyle(SpanStyle(color = PrimaryGreen)) {
                    append("native")
                }
                append("\nAndroid experiences.")
            },
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = "I'm Dhruva Khatavkar — I build fluid, performant Android applications with Jetpack Compose, Kotlin, and modern architectural patterns. Currently exploring Kotlin Multiplatform.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        // Action Buttons
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { onNavigate(NavSection.PROJECTS) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryGreen,
                    contentColor = BackgroundDark
                ),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(horizontal = 22.dp, vertical = 12.dp)
            ) {
                Text(
                    text = "Explore My Work",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
                )
            }

            OutlinedButton(
                onClick = { onNavigate(NavSection.AI_WORKSPACE) },
                shape = RoundedCornerShape(8.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, BorderDark),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colorScheme.onSurface
                ),
                contentPadding = PaddingValues(horizontal = 22.dp, vertical = 12.dp)
            ) {
                Text(
                    text = "Ask Dhruva AI ->",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
                )
            }
        }
    }
}
