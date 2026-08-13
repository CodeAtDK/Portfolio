package com.example.portfolio.ui.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import org.jetbrains.compose.resources.painterResource
import portfolio.app.shared.generated.resources.Res
import portfolio.app.shared.generated.resources.profile
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.portfolio.ui.components.TerminalMockup
import com.example.portfolio.ui.components.fadeInSlideUp
import com.example.portfolio.ui.theme.*

@Composable
fun HomeSection(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 80.dp, bottom = 60.dp), // Hero needs to breathe
        horizontalArrangement = Arrangement.spacedBy(56.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HomeContent(
            modifier = Modifier.weight(1.15f)
        )
        TerminalMockup(
            modifier = Modifier
                .weight(0.85f)
                .fadeInSlideUp(durationMs = 700, delayMs = 300)
        )
    }
}

@Composable
private fun HomeContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fadeInSlideUp(durationMs = 600),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // Profile image with green glow behind it
        Box(contentAlignment = Alignment.Center) {
            // Glow circle behind the image
            Box(
                modifier = Modifier
                    .size(140.dp)
                    .clip(CircleShape)
                    .background(PrimaryGreen.copy(alpha = 0.12f))
                    .blur(20.dp)
            )
            Image(
                painter = painterResource(Res.drawable.profile),
                contentDescription = "Dhruva Khatavkar Profile Photo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .border(2.dp, PrimaryGreen.copy(alpha = 0.6f), CircleShape)
            )
        }

        Text(
            text = "Android Developer • Kotlin • Jetpack Compose",
            style = MaterialTheme.typography.labelSmall.copy(fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace),
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

        // Action Buttons — consistent 12dp radius
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { /* Scroll to projects */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryGreen,
                    contentColor = BackgroundDark
                ),
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 14.dp)
            ) {
                Text(
                    text = "Explore My Work",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
                )
            }

            OutlinedButton(
                onClick = { /* View Resume */ },
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, GlassBorder),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colorScheme.onSurface
                ),
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 14.dp)
            ) {
                Text(
                    text = "View Resume",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
                )
            }

            OutlinedButton(
                onClick = { /* Scroll to AI workspace */ },
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, GlassBorder),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colorScheme.onSurface
                ),
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 14.dp)
            ) {
                Text(
                    text = "Ask Dhruva AI ->",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
                )
            }
        }
    }
}
