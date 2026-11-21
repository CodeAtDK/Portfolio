package com.example.portfolio.ui.sections

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.filled.Agriculture
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CloudDone
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FormatColorFill
import androidx.compose.material.icons.filled.Grass
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material.icons.outlined.Code
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.portfolio.ui.components.SectionHeader
import com.example.portfolio.ui.components.fadeInSlideUp
import com.example.portfolio.ui.theme.*
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import portfolio.app.shared.generated.resources.Res
import portfolio.app.shared.generated.resources.foodbridge_preview
import portfolio.app.shared.generated.resources.agriconnect_preview
import portfolio.app.shared.generated.resources.carenest_preview

data class ProjectMetric(
    val icon: ImageVector? = null,
    val value: String,
    val label: String
)

@Composable
fun ProjectsSection(
    modifier: Modifier = Modifier,
    onOpenCaseStudy: (String) -> Unit = {}
) {
    val isMobile = LocalIsMobile.current

    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(if (isMobile) 28.dp else 40.dp)
    ) {
        SectionHeader(number = "03", title = "Projects")

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(if (isMobile) 44.dp else 80.dp)
        ) {
            // Flagship: Inkspace Notebook
            ProjectCardItem(
                title = "Inkspace Notebook",
                role = "Lead Mobile & Multiplatform Engineer",
                description = "A modern, dark-themed cross-platform handwriting and document studio. Features 11 inking tools, responsive 3-tier toolbar, audio-synchronized note recording, multi-page PDF annotation, and real-time cloud synchronization.",
                tags = listOf("Kotlin Multiplatform", "Compose Multiplatform", "Supabase", "SQLDelight SQLite", "Skiko Canvas", "Ktor"),
                metrics = listOf(
                    ProjectMetric(Icons.Filled.Bolt, "Sub-8ms", "Inking Latency"),
                    ProjectMetric(Icons.Filled.Sync, "95%+", "Shared Codebase"),
                    ProjectMetric(Icons.Filled.CloudDone, "100%", "Offline-First Sync")
                ),
                caseStudyId = "inkspace",
                liveDemoUrl = "https://notes.dhruva.website",
                playStoreUrl = "https://play.google.com/store/apps/details?id=dev.dhruva.notebook",
                githubUrl = "https://github.com/CodeAtDK/NotesApplications",
                onOpenCaseStudy = onOpenCaseStudy,
                previewImage = null,
                gradientColors = listOf(Color(0xFF2563EB), Color(0xFF00DAF3)),
                isFeatured = true,
                isReversed = false,
                modifier = Modifier.fadeInSlideUp(delayMs = 0)
            )

            // FoodBridge
            ProjectCardItem(
                title = "FoodBridge",
                role = "Lead Android Engineer",
                description = "A platform connecting surplus food donors with NGOs to reduce food waste and feed communities in need. Features instant location-based matching, real-time rescue dispatch, and verified NGO claims.",
                tags = listOf("Kotlin", "Jetpack Compose", "Firebase Firestore", "Google Maps API", "Coroutines"),
                metrics = listOf(
                    ProjectMetric(Icons.Filled.Bolt, "Instant", "Radar Matching"),
                    ProjectMetric(Icons.Filled.Sync, "500+ Meals", "Surplus Rescued"),
                    ProjectMetric(Icons.Filled.CloudDone, "100%", "Offline Firebase Sync")
                ),
                caseStudyId = "foodbridge",
                liveDemoUrl = null,
                githubUrl = "https://github.com/CodeAtDK/Portfolio",
                onOpenCaseStudy = onOpenCaseStudy,
                previewImage = Res.drawable.foodbridge_preview,
                gradientColors = listOf(Color(0xFF3DDC84), Color(0xFF58A6FF)),
                isFeatured = false,
                isReversed = true,
                modifier = Modifier.fadeInSlideUp(delayMs = 100)
            )

            // Agri Connect
            ProjectCardItem(
                title = "Agri Connect",
                role = "Android Architecture Engineer",
                description = "A direct farmer-to-consumer marketplace enabling agricultural trade without middlemen. Features include real-time Mandi pricing, crop cataloging, instant buyer bidding, and offline-first Room database.",
                tags = listOf("Kotlin", "Jetpack Compose", "MVVM Clean Arch", "Retrofit REST", "Room SQLite"),
                metrics = listOf(
                    ProjectMetric(Icons.Filled.Bolt, "Direct B2C", "No Middlemen"),
                    ProjectMetric(Icons.Filled.Sync, "Live Rates", "Mandi Price Index"),
                    ProjectMetric(Icons.Filled.CloudDone, "100%", "Offline Room DB")
                ),
                caseStudyId = "agriconnect",
                liveDemoUrl = null,
                githubUrl = "https://github.com/CodeAtDK/Portfolio",
                onOpenCaseStudy = onOpenCaseStudy,
                previewImage = Res.drawable.agriconnect_preview,
                gradientColors = listOf(Color(0xFFFF8A65), Color(0xFF3DDC84)),
                isFeatured = false,
                isReversed = false,
                modifier = Modifier.fadeInSlideUp(delayMs = 200)
            )

            // CareNest
            ProjectCardItem(
                title = "CareNest",
                role = "Android & AI Systems Engineer",
                description = "An elderly care management application with intelligent medication scheduling, Google Gemini AI symptom triage, vitals telemetry dashboards, and caregiver communication alerts.",
                tags = listOf("Kotlin", "Jetpack Compose", "Hilt DI", "Google Gemini AI", "WorkManager"),
                metrics = listOf(
                    ProjectMetric(Icons.Filled.Bolt, "Gemini AI", "Symptom Triage"),
                    ProjectMetric(Icons.Filled.Sync, "Smart Sync", "Pill Schedule Alerts"),
                    ProjectMetric(Icons.Filled.CloudDone, "100%", "Offline Telemetry")
                ),
                caseStudyId = "carenest",
                liveDemoUrl = null,
                githubUrl = "https://github.com/CodeAtDK/Portfolio",
                onOpenCaseStudy = onOpenCaseStudy,
                previewImage = Res.drawable.carenest_preview,
                gradientColors = listOf(Color(0xFF58A6FF), Color(0xFFFF8A65)),
                isFeatured = false,
                isReversed = true,
                modifier = Modifier.fadeInSlideUp(delayMs = 300)
            )
            
            // This Portfolio
            ProjectCardItem(
                title = "This Portfolio",
                role = "Full-Stack Multiplatform Engineer",
                description = "A developer-crafted portfolio web and desktop app engineered on Kotlin Multiplatform and Compose Wasm. Features a terminal-themed design system and embedded Google Gemini AI.",
                tags = listOf("Kotlin Multiplatform", "Compose Multiplatform", "Compose Wasm", "HTML5 / CSS3", "Gemini API"),
                metrics = listOf(
                    ProjectMetric(Icons.Filled.Bolt, "60 FPS", "Wasm & Skiko Engine"),
                    ProjectMetric(Icons.Filled.Sync, "Gemini AI", "Terminal Assistant"),
                    ProjectMetric(Icons.Filled.CloudDone, "95%+", "Shared Codebase")
                ),
                caseStudyId = "portfolio",
                liveDemoUrl = "https://dhruva.website",
                githubUrl = "https://github.com/CodeAtDK/Portfolio",
                onOpenCaseStudy = onOpenCaseStudy,
                previewImage = null,
                gradientColors = listOf(Color(0xFF3DDC84), Color(0xFF58A6FF)),
                isFeatured = false,
                isReversed = false,
                modifier = Modifier.fadeInSlideUp(delayMs = 400)
            )
        }
    }
}

@OptIn(androidx.compose.foundation.layout.ExperimentalLayoutApi::class)
@Composable
private fun ProjectCardItem(
    title: String,
    description: String,
    tags: List<String>,
    caseStudyId: String?,
    onOpenCaseStudy: (String) -> Unit,
    previewImage: DrawableResource?,
    gradientColors: List<Color>,
    isFeatured: Boolean,
    isReversed: Boolean,
    role: String? = null,
    metrics: List<ProjectMetric> = emptyList(),
    liveDemoUrl: String? = null,
    playStoreUrl: String? = null,
    githubUrl: String? = null,
    modifier: Modifier = Modifier
) {
    val isMobile = LocalIsMobile.current
    val uriHandler = LocalUriHandler.current

    val visualBlock = @Composable {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(if (isMobile) 280.dp else 360.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(SurfaceDark)
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            gradientColors[0].copy(alpha = 0.16f),
                            gradientColors[1].copy(alpha = 0.08f)
                        )
                    )
                )
                .border(1.dp, if (isFeatured) gradientColors[0].copy(alpha = 0.4f) else GlassBorder, RoundedCornerShape(16.dp))
                .clickable(enabled = caseStudyId != null) {
                    if (caseStudyId != null) onOpenCaseStudy(caseStudyId)
                },
            contentAlignment = Alignment.Center
        ) {
            if (previewImage != null) {
                // Actual app screenshot in a phone-frame style container
                Box(
                    modifier = Modifier
                        .widthIn(max = if (isMobile) 140.dp else 165.dp)
                        .fillMaxHeight()
                        .padding(vertical = 16.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xFF1A1A2E))
                        .border(2.dp, GlassBorder.copy(alpha = 0.35f), RoundedCornerShape(20.dp))
                        .shadow(12.dp, RoundedCornerShape(20.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(previewImage),
                        contentDescription = "$title screenshot",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(20.dp)),
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.TopCenter
                    )
                }
            } else if (caseStudyId == "inkspace" || caseStudyId == "inkspace-notebook") {
                // GoodNotes / Tablet inking preview snapshot
                Box(
                    modifier = Modifier
                        .widthIn(max = if (isMobile) 320.dp else 400.dp)
                        .fillMaxWidth(0.94f)
                        .padding(vertical = 12.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFF0B1120))
                        .border(1.5.dp, Color(0xFF38BDF8).copy(alpha = 0.35f), RoundedCornerShape(16.dp))
                        .shadow(16.dp, RoundedCornerShape(16.dp), spotColor = Color(0xFF2563EB).copy(alpha = 0.3f))
                        .padding(10.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFF111827))
                            .padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Top toolbar preview
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Edit,
                                    contentDescription = "Inkspace Studio",
                                    tint = Color(0xFF38BDF8),
                                    modifier = Modifier.size(13.dp)
                                )
                                Text(
                                    text = "Inkspace Studio • KMP",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    color = Color.White
                                )
                            }

                            // Cloud Sync status
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(50))
                                    .background(Color(0xFF10B981).copy(alpha = 0.15f))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(modifier = Modifier.size(5.dp).clip(CircleShape).background(Color(0xFF10B981)))
                                    Text(
                                        text = "Supabase Synced",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            fontSize = 9.sp,
                                            fontWeight = FontWeight.Medium
                                        ),
                                        color = Color(0xFF34D399)
                                    )
                                }
                            }
                        }

                        // Toolbar items preview with vector icons
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(6.dp))
                                .background(Color(0xFF1E293B))
                                .padding(horizontal = 10.dp, vertical = 5.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(Icons.Filled.Edit, "Fountain", tint = Color(0xFF38BDF8), modifier = Modifier.size(13.dp))
                                Icon(Icons.Filled.Brush, "Brush", tint = Color(0xFF94A3B8), modifier = Modifier.size(13.dp))
                                Icon(Icons.Filled.FormatColorFill, "Highlighter", tint = Color(0xFF94A3B8), modifier = Modifier.size(13.dp))
                                Icon(Icons.Filled.Category, "Shapes", tint = Color(0xFF94A3B8), modifier = Modifier.size(13.dp))
                                Icon(Icons.Filled.Mic, "Audio Sync", tint = Color(0xFFEF4444), modifier = Modifier.size(13.dp))
                            }
                            Text(
                                text = "11 Inking Tools",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.SemiBold
                                ),
                                color = Color(0xFF38BDF8)
                            )
                        }

                        // Canvas note simulation
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(if (isMobile) 110.dp else 135.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFF090D16))
                                .padding(10.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Speed,
                                        contentDescription = "Sub-8ms",
                                        tint = Color(0xFF38BDF8),
                                        modifier = Modifier.size(15.dp)
                                    )
                                    Text(
                                        text = "Sub-8ms Inking Engine",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Bold
                                        ),
                                        color = Color(0xFF38BDF8)
                                    )
                                }
                                Text(
                                    text = "∫ (Quadratic Bézier Smoothing) dt",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontSize = 10.sp,
                                        fontFamily = FontFamily.Monospace
                                    ),
                                    color = Color(0xFF94A3B8)
                                )
                                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(50))
                                            .background(PrimaryGreen.copy(alpha = 0.15f))
                                            .padding(horizontal = 8.dp, vertical = 3.dp)
                                    ) {
                                        Text(
                                            text = "KMP • CMP • iOS • Android • Web",
                                            style = MaterialTheme.typography.labelSmall.copy(
                                                fontSize = 9.sp,
                                                fontWeight = FontWeight.SemiBold
                                            ),
                                            color = PrimaryGreen
                                        )
                                    }
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(50))
                                            .background(SecondaryWarm.copy(alpha = 0.15f))
                                            .padding(horizontal = 8.dp, vertical = 3.dp)
                                        ) {
                                        Text(
                                            text = "100% Offline-First",
                                            style = MaterialTheme.typography.labelSmall.copy(
                                                fontSize = 9.sp,
                                                fontWeight = FontWeight.SemiBold
                                            ),
                                            color = SecondaryWarm
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (caseStudyId == "portfolio") {
                // Browser snapshot for This Portfolio
                Box(
                    modifier = Modifier
                        .widthIn(max = 280.dp)
                        .fillMaxWidth(0.9f)
                        .padding(horizontal = 4.dp)
                ) {
                    com.example.portfolio.ui.components.PortfolioWebSnapshotCard()
                }
            }
        }
    }

    val contentBlock = @Composable {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Header Row: Title + Role Badge
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = title,
                        style = if (isMobile) MaterialTheme.typography.titleLarge else MaterialTheme.typography.headlineMedium,
                        color = TextPrimary
                    )

                    if (isFeatured) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(50))
                                .background(PrimaryGreen.copy(alpha = 0.15f))
                                .border(1.dp, PrimaryGreen.copy(alpha = 0.4f), RoundedCornerShape(50))
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Star,
                                    contentDescription = "Featured Flagship",
                                    tint = PrimaryGreen,
                                    modifier = Modifier.size(11.dp)
                                )
                                Text(
                                    text = "Flagship",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    color = PrimaryGreen
                                )
                            }
                        }
                    }
                }

                if (role != null) {
                    Text(
                        text = role,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold
                        ),
                        color = gradientColors[0]
                    )
                }
            }

            // Key Metrics Pill Strip
            if (metrics.isNotEmpty()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    metrics.forEach { metric ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(8.dp))
                                .background(SurfaceDark)
                                .border(1.dp, BorderDark, RoundedCornerShape(8.dp))
                                .padding(horizontal = 10.dp, vertical = 6.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                if (metric.icon != null) {
                                    Icon(
                                        imageVector = metric.icon,
                                        contentDescription = metric.label,
                                        tint = PrimaryGreen,
                                        modifier = Modifier.size(13.dp)
                                    )
                                }
                                Column {
                                    Text(
                                        text = metric.value,
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 11.5.sp
                                        ),
                                        color = PrimaryGreen
                                    )
                                    Text(
                                        text = metric.label,
                                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                                        color = TextMuted
                                    )
                                }
                            }
                        }
                    }
                }
            }
            
            // Tech Stack Tags
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                tags.forEach { tag ->
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(SurfaceDark)
                            .border(1.dp, BorderDark, RoundedCornerShape(6.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = tag,
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontSize = 10.sp,
                                fontFamily = FontFamily.Monospace
                            ),
                            color = TextMuted
                        )
                    }
                }
            }

            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 22.sp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            // Action Buttons
            FlowRow(
                modifier = Modifier.padding(top = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (playStoreUrl != null) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFF01875F))
                            .clickable { uriHandler.openUri(playStoreUrl) }
                            .padding(horizontal = 14.dp, vertical = 8.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.OpenInNew,
                                contentDescription = "Google Play",
                                tint = Color.White,
                                modifier = Modifier.size(13.dp)
                            )
                            Text(
                                text = "Google Play",
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                color = Color.White
                            )
                        }
                    }
                }

                if (liveDemoUrl != null) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFF2563EB))
                            .clickable { uriHandler.openUri(liveDemoUrl) }
                            .padding(horizontal = 14.dp, vertical = 8.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.OpenInNew,
                                contentDescription = "Live Demo",
                                tint = Color.White,
                                modifier = Modifier.size(13.dp)
                            )
                            Text(
                                text = "Live Demo",
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                color = Color.White
                            )
                        }
                    }
                }

                if (caseStudyId != null) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(SurfaceDark)
                            .border(1.dp, GlassBorder, RoundedCornerShape(8.dp))
                            .clickable { onOpenCaseStudy(caseStudyId) }
                            .padding(horizontal = 14.dp, vertical = 8.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "View Case Study",
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
                                color = PrimaryGreen
                            )
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = "View Case Study",
                                tint = PrimaryGreen,
                                modifier = Modifier.size(13.dp)
                            )
                        }
                    }
                }

                if (githubUrl != null) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(SurfaceDark)
                            .border(1.dp, BorderDark, RoundedCornerShape(8.dp))
                            .clickable { uriHandler.openUri(githubUrl) }
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Code,
                                contentDescription = "GitHub",
                                tint = TextMuted,
                                modifier = Modifier.size(14.dp)
                            )
                            Text(
                                text = "GitHub",
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Medium),
                                color = TextMuted
                            )
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.OpenInNew,
                                contentDescription = "External Link",
                                tint = TextMuted.copy(alpha = 0.7f),
                                modifier = Modifier.size(11.dp)
                            )
                        }
                    }
                }
            }
        }
    }

    if (isMobile) {
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Box(modifier = Modifier.fillMaxWidth()) { visualBlock() }
            Box(modifier = Modifier.fillMaxWidth()) { contentBlock() }
        }
    } else {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(44.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isReversed) {
                Box(modifier = Modifier.weight(1.05f)) { contentBlock() }
                Box(modifier = Modifier.weight(1f)) { visualBlock() }
            } else {
                Box(modifier = Modifier.weight(1f)) { visualBlock() }
                Box(modifier = Modifier.weight(1.05f)) { contentBlock() }
            }
        }
    }
}
