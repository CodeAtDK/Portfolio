package com.example.portfolio.ui.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.portfolio.model.CaseStudy
import com.example.portfolio.ui.components.AnimatedMeshBackground
import com.example.portfolio.ui.components.PortfolioCard
import com.example.portfolio.ui.components.TechChip
import com.example.portfolio.ui.components.fadeInSlideUp
import com.example.portfolio.ui.theme.*

/**
 * Full-screen case study detail view, matching the portfolio's dark glassmorphism design.
 * Replaces the external HTML case study pages with a native Compose equivalent.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CaseStudyScreen(
    caseStudy: CaseStudy,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val accentColor = Color(caseStudy.accentColorHex)
    val uriHandler = LocalUriHandler.current

    Box(modifier = modifier.fillMaxSize().background(BackgroundDark)) {
        AnimatedMeshBackground(modifier = Modifier.fillMaxSize())

        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier
                    .width(1100.dp)
                    .padding(horizontal = 40.dp)
            ) {
                // ─── Breadcrumb + Back ───────────────────────────────────────
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .clickable { onBack() }
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "←",
                            style = MaterialTheme.typography.bodyMedium,
                            color = TextMuted
                        )
                        Text(
                            text = "Back to Projects",
                            style = MaterialTheme.typography.labelSmall,
                            color = TextMuted
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Projects",
                            style = MaterialTheme.typography.labelSmall,
                            color = TextMuted
                        )
                        Text(
                            text = "/",
                            style = MaterialTheme.typography.labelSmall,
                            color = TextDim
                        )
                        Text(
                            text = caseStudy.title,
                            style = MaterialTheme.typography.labelSmall,
                            color = TextPrimary,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(40.dp))

                // ─── Hero Section ────────────────────────────────────────────
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fadeInSlideUp(durationMs = 600),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    // Category badge
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(accentColor.copy(alpha = 0.08f))
                            .border(1.dp, accentColor.copy(alpha = 0.3f), RoundedCornerShape(50))
                            .padding(horizontal = 16.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = caseStudy.category,
                            style = MaterialTheme.typography.labelSmall,
                            color = accentColor
                        )
                    }

                    // Title
                    Text(
                        text = caseStudy.title,
                        style = MaterialTheme.typography.displayLarge,
                        color = accentColor
                    )

                    // Tagline
                    Text(
                        text = caseStudy.tagline,
                        style = MaterialTheme.typography.headlineMedium,
                        color = TextMuted
                    )

                    // Description
                    Text(
                        text = caseStudy.description,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.widthIn(max = 700.dp)
                    )

                    // GitHub link button
                    if (caseStudy.githubUrl != null) {
                        Row(
                            modifier = Modifier.padding(top = 8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(accentColor)
                                    .clickable { uriHandler.openUri(caseStudy.githubUrl) }
                                    .padding(horizontal = 24.dp, vertical = 14.dp)
                            ) {
                                Text(
                                    text = "View Source on GitHub ->",
                                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
                                    color = BackgroundDark
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(100.dp))

                // ─── Challenge & Solution ────────────────────────────────────
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fadeInSlideUp(durationMs = 500, delayMs = 100),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    // Challenge card
                    PortfolioCard(
                        modifier = Modifier.weight(1f)
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("⚠️", style = MaterialTheme.typography.headlineMedium)
                                Text(
                                    text = "The Challenge",
                                    style = MaterialTheme.typography.headlineMedium,
                                    color = SecondaryWarm
                                )
                            }
                            Text(
                                text = caseStudy.challenge,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    // Solution card
                    PortfolioCard(
                        modifier = Modifier.weight(1f),
                        accentBorderColor = accentColor.copy(alpha = 0.5f)
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("💡", style = MaterialTheme.typography.headlineMedium)
                                Text(
                                    text = "The Solution",
                                    style = MaterialTheme.typography.headlineMedium,
                                    color = accentColor
                                )
                            }
                            Text(
                                text = caseStudy.solution,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(100.dp))

                // ─── Key Features ────────────────────────────────────────────
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fadeInSlideUp(durationMs = 500, delayMs = 200),
                    verticalArrangement = Arrangement.spacedBy(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Key Features",
                        style = MaterialTheme.typography.displayMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    )

                    // 2x2 grid
                    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                        for (rowStart in caseStudy.features.indices step 2) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(20.dp)
                            ) {
                                for (i in rowStart until minOf(rowStart + 2, caseStudy.features.size)) {
                                    val feature = caseStudy.features[i]
                                    PortfolioCard(
                                        modifier = Modifier.weight(1f)
                                    ) {
                                        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                                            // Icon circle
                                            Box(
                                                modifier = Modifier
                                                    .size(48.dp)
                                                    .clip(RoundedCornerShape(50))
                                                    .background(accentColor.copy(alpha = 0.1f)),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text(
                                                    text = feature.icon,
                                                    style = MaterialTheme.typography.headlineMedium
                                                )
                                            }
                                            Text(
                                                text = feature.title,
                                                style = MaterialTheme.typography.headlineMedium,
                                                color = MaterialTheme.colorScheme.onSurface
                                            )
                                            Text(
                                                text = feature.description,
                                                style = MaterialTheme.typography.bodyMedium,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }
                                    }
                                }
                                // Fill empty slot if odd number of features
                                if (rowStart + 1 >= caseStudy.features.size) {
                                    Spacer(modifier = Modifier.weight(1f))
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(100.dp))

                // ─── Technology Stack ────────────────────────────────────────
                PortfolioCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fadeInSlideUp(durationMs = 500, delayMs = 300),
                    accentBorderColor = accentColor.copy(alpha = 0.3f)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        Text(
                            text = "Technology Stack",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        FlowRow(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            caseStudy.techStack.forEach { tag ->
                                TechChip(
                                    text = tag.name,
                                    textColor = Color(tag.colorHex)
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(80.dp))

                // ─── Footer ──────────────────────────────────────────────────
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    HorizontalDivider(color = BorderDark, thickness = 1.dp)
                    Text(
                        text = "© 2026 Dhruva Khatavkar // Built with Native Compose Multiplatform",
                        style = MaterialTheme.typography.labelSmall,
                        color = TextMuted,
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}
