package com.example.portfolio.ui.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.filled.Agriculture
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Code
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.portfolio.model.CaseStudy
import com.example.portfolio.ui.components.*
import com.example.portfolio.ui.theme.*

/**
 * Full-screen case study detail view, matching the portfolio's dark glassmorphism design.
 * Highly optimized for both desktop and mobile viewports.
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
    val isMobile = LocalIsMobile.current

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
                    .fillMaxWidth()
                    .widthIn(max = 1100.dp)
                    .padding(horizontal = if (isMobile) 16.dp else 40.dp)
            ) {
                // ─── Breadcrumb + Back Action Bar ────────────────────────────
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = if (isMobile) 16.dp else 28.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(SurfaceDark.copy(alpha = 0.8f))
                            .border(1.dp, GlassBorder, RoundedCornerShape(50))
                            .clickable { onBack() }
                            .padding(horizontal = if (isMobile) 12.dp else 16.dp, vertical = if (isMobile) 6.dp else 8.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = PrimaryGreen,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "Back to Projects",
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
                                color = Color.White
                            )
                        }
                    }

                    if (!isMobile) {
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
                }

                Spacer(modifier = Modifier.height(if (isMobile) 12.dp else 24.dp))

                // ─── Hero Section ────────────────────────────────────────────
                val heroTextContent = @Composable {
                    Column(verticalArrangement = Arrangement.spacedBy(if (isMobile) 14.dp else 20.dp)) {
                        // Category badge
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(50))
                                .background(accentColor.copy(alpha = 0.1f))
                                .border(1.dp, accentColor.copy(alpha = 0.35f), RoundedCornerShape(50))
                                .padding(horizontal = 14.dp, vertical = 5.dp)
                        ) {
                            Text(
                                text = caseStudy.category,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontSize = if (isMobile) 10.sp else 11.sp,
                                    fontWeight = FontWeight.SemiBold
                                ),
                                color = accentColor
                            )
                        }

                        // Title
                        Text(
                            text = caseStudy.title,
                            style = if (isMobile) MaterialTheme.typography.displayMedium.copy(fontSize = 28.sp) else MaterialTheme.typography.displayLarge,
                            color = accentColor
                        )

                        // Tagline
                        Text(
                            text = caseStudy.tagline,
                            style = if (isMobile) MaterialTheme.typography.titleMedium else MaterialTheme.typography.headlineMedium,
                            color = TextMuted
                        )

                        // Description
                        Text(
                            text = caseStudy.description,
                            style = if (isMobile) MaterialTheme.typography.bodyMedium else MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.widthIn(max = 700.dp)
                        )

                        // Action Buttons: Google Play + Live Demo + GitHub
                        FlowRow(
                            modifier = Modifier.padding(top = 4.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            if (caseStudy.playStoreUrl != null) {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(Color(0xFF01875F))
                                        .clickable { uriHandler.openUri(caseStudy.playStoreUrl) }
                                        .padding(horizontal = if (isMobile) 16.dp else 22.dp, vertical = if (isMobile) 10.dp else 12.dp)
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.OpenInNew,
                                            contentDescription = "Google Play",
                                            tint = Color.White,
                                            modifier = Modifier.size(15.dp)
                                        )
                                        Text(
                                            text = "Google Play",
                                            style = MaterialTheme.typography.labelSmall.copy(
                                                fontWeight = FontWeight.Bold,
                                                fontSize = if (isMobile) 12.sp else 13.sp
                                            ),
                                            color = Color.White
                                        )
                                    }
                                }
                            }

                            if (caseStudy.liveDemoUrl != null) {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(accentColor)
                                        .clickable { uriHandler.openUri(caseStudy.liveDemoUrl) }
                                        .padding(horizontal = if (isMobile) 16.dp else 22.dp, vertical = if (isMobile) 10.dp else 12.dp)
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.OpenInNew,
                                            contentDescription = "Live Demo",
                                            tint = Color(0xFF05130B),
                                            modifier = Modifier.size(15.dp)
                                        )
                                        Text(
                                            text = "Live Demo",
                                            style = MaterialTheme.typography.labelSmall.copy(
                                                fontWeight = FontWeight.Bold,
                                                fontSize = if (isMobile) 12.sp else 13.sp
                                            ),
                                            color = Color(0xFF05130B)
                                        )
                                    }
                                }
                            }

                            if (caseStudy.githubUrl != null) {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(SurfaceDark)
                                        .border(1.dp, GlassBorder, RoundedCornerShape(12.dp))
                                        .clickable { uriHandler.openUri(caseStudy.githubUrl) }
                                        .padding(horizontal = if (isMobile) 16.dp else 22.dp, vertical = if (isMobile) 10.dp else 12.dp)
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Outlined.Code,
                                            contentDescription = "GitHub",
                                            tint = Color.White,
                                            modifier = Modifier.size(15.dp)
                                        )
                                        Text(
                                            text = "View Source on GitHub",
                                            style = MaterialTheme.typography.labelSmall.copy(
                                                fontWeight = FontWeight.SemiBold,
                                                fontSize = if (isMobile) 12.sp else 13.sp
                                            ),
                                            color = Color.White
                                        )
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.OpenInNew,
                                            contentDescription = "External Link",
                                            tint = Color.White.copy(alpha = 0.7f),
                                            modifier = Modifier.size(12.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // In desktop view, display hero text + interactive device side-by-side
                if (!isMobile) {
                    when (caseStudy.id) {
                        "inkspace", "inkspace-notebook" -> {
                            Row(
                                modifier = Modifier.fillMaxWidth().fadeInSlideUp(durationMs = 600),
                                horizontalArrangement = Arrangement.spacedBy(40.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(modifier = Modifier.weight(1.05f)) { heroTextContent() }
                                Box(modifier = Modifier.weight(0.95f), contentAlignment = Alignment.Center) {
                                    InkspaceInteractiveShowcase()
                                }
                            }
                        }
                        "foodbridge" -> {
                            Row(
                                modifier = Modifier.fillMaxWidth().fadeInSlideUp(durationMs = 600),
                                horizontalArrangement = Arrangement.spacedBy(40.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(modifier = Modifier.weight(1.1f)) { heroTextContent() }
                                Box(modifier = Modifier.weight(0.9f), contentAlignment = Alignment.Center) {
                                    FoodBridgeInteractiveShowcase()
                                }
                            }
                        }
                        "agriconnect" -> {
                            Row(
                                modifier = Modifier.fillMaxWidth().fadeInSlideUp(durationMs = 600),
                                horizontalArrangement = Arrangement.spacedBy(40.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(modifier = Modifier.weight(1.1f)) { heroTextContent() }
                                Box(modifier = Modifier.weight(0.9f), contentAlignment = Alignment.Center) {
                                    AgriConnectInteractiveShowcase()
                                }
                            }
                        }
                        "carenest" -> {
                            Row(
                                modifier = Modifier.fillMaxWidth().fadeInSlideUp(durationMs = 600),
                                horizontalArrangement = Arrangement.spacedBy(40.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(modifier = Modifier.weight(1.1f)) { heroTextContent() }
                                Box(modifier = Modifier.weight(0.9f), contentAlignment = Alignment.Center) {
                                    CareNestInteractiveShowcase()
                                }
                            }
                        }
                        "portfolio" -> {
                            Row(
                                modifier = Modifier.fillMaxWidth().fadeInSlideUp(durationMs = 600),
                                horizontalArrangement = Arrangement.spacedBy(40.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(modifier = Modifier.weight(1f)) { heroTextContent() }
                                Box(modifier = Modifier.weight(1.1f), contentAlignment = Alignment.Center) {
                                    PortfolioWebInteractiveShowcase()
                                }
                            }
                        }
                        else -> {
                            Column(modifier = Modifier.fillMaxWidth().fadeInSlideUp(durationMs = 600)) {
                                heroTextContent()
                            }
                        }
                    }
                } else {
                    // Mobile view: clean hero text flow
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fadeInSlideUp(durationMs = 600),
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        heroTextContent()
                    }
                }

                Spacer(modifier = Modifier.height(if (isMobile) 36.dp else 70.dp))

                // ─── Challenge & Solution ────────────────────────────────────
                val challengeCard = @Composable {
                    PortfolioCard(modifier = Modifier.fillMaxWidth()) {
                        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Warning,
                                    contentDescription = null,
                                    tint = SecondaryWarm,
                                    modifier = Modifier.size(24.dp)
                                )
                                Text(
                                    text = "The Challenge",
                                    style = if (isMobile) MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold) else MaterialTheme.typography.headlineMedium,
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
                }

                val solutionCard = @Composable {
                    PortfolioCard(
                        modifier = Modifier.fillMaxWidth(),
                        accentBorderColor = accentColor.copy(alpha = 0.5f)
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Lightbulb,
                                    contentDescription = null,
                                    tint = accentColor,
                                    modifier = Modifier.size(24.dp)
                                )
                                Text(
                                    text = "The Solution",
                                    style = if (isMobile) MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold) else MaterialTheme.typography.headlineMedium,
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

                if (isMobile) {
                    Column(
                        modifier = Modifier.fillMaxWidth().fadeInSlideUp(durationMs = 500, delayMs = 100),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        challengeCard()
                        solutionCard()
                    }
                } else {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fadeInSlideUp(durationMs = 500, delayMs = 100),
                        horizontalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        Box(modifier = Modifier.weight(1f)) { challengeCard() }
                        Box(modifier = Modifier.weight(1f)) { solutionCard() }
                    }
                }

                Spacer(modifier = Modifier.height(if (isMobile) 36.dp else 70.dp))

                // ─── Key Features ────────────────────────────────────────────
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fadeInSlideUp(durationMs = 500, delayMs = 200),
                    verticalArrangement = Arrangement.spacedBy(if (isMobile) 16.dp else 28.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Key Features",
                        style = if (isMobile) MaterialTheme.typography.headlineMedium else MaterialTheme.typography.displayMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    )

                    if (isMobile) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(14.dp)
                        ) {
                            caseStudy.features.forEach { feature ->
                                PortfolioCard(modifier = Modifier.fillMaxWidth()) {
                                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                                        Box(
                                            modifier = Modifier
                                                .size(38.dp)
                                                .clip(RoundedCornerShape(50))
                                                .background(accentColor.copy(alpha = 0.1f)),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                imageVector = feature.icon,
                                                contentDescription = null,
                                                tint = accentColor,
                                                modifier = Modifier.size(20.dp)
                                            )
                                        }
                                        Text(
                                            text = feature.title,
                                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
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
                        }
                    } else {
                        // 2x2 grid on desktop
                        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                            for (rowStart in caseStudy.features.indices step 2) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                                ) {
                                    for (i in rowStart until minOf(rowStart + 2, caseStudy.features.size)) {
                                        val feature = caseStudy.features[i]
                                        PortfolioCard(modifier = Modifier.weight(1f)) {
                                            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                                                Box(
                                                    modifier = Modifier
                                                        .size(48.dp)
                                                        .clip(RoundedCornerShape(50))
                                                        .background(accentColor.copy(alpha = 0.1f)),
                                                    contentAlignment = Alignment.Center
                                                ) {
                                                    Icon(
                                                        imageVector = feature.icon,
                                                        contentDescription = null,
                                                        tint = accentColor,
                                                        modifier = Modifier.size(24.dp)
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
                                    if (rowStart + 1 >= caseStudy.features.size) {
                                        Spacer(modifier = Modifier.weight(1f))
                                    }
                                }
                            }
                        }
                    }
                }

                // ─── Project Dedicated Interactive Showcase ──────────────────
                when (caseStudy.id) {
                    "inkspace", "inkspace-notebook" -> {
                        Spacer(modifier = Modifier.height(if (isMobile) 40.dp else 80.dp))

                        PortfolioCard(
                            modifier = Modifier.fillMaxWidth().fadeInSlideUp(durationMs = 500, delayMs = 250),
                            accentBorderColor = Color(0xFF2563EB).copy(alpha = 0.35f)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(if (isMobile) 16.dp else 24.dp)
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.Edit,
                                            contentDescription = "Studio",
                                            tint = Color(0xFF38BDF8),
                                            modifier = Modifier.size(if (isMobile) 22.dp else 28.dp)
                                        )
                                        Text(
                                            text = "Inkspace Studio Interactive Showcase",
                                            style = if (isMobile) MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold) else MaterialTheme.typography.headlineMedium,
                                            color = Color(0xFF38BDF8),
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                    Text(
                                        text = "Interact with the recreated GoodNotes 5-grade UI: Studio Canvas, 15+ Paper Templates, Audio Sync & Copilot, and Real-Time Supabase Sync.",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.widthIn(max = 660.dp)
                                    )
                                }

                                InkspaceInteractiveShowcase(
                                    initialTab = InkspaceTab.EDITOR
                                )
                            }
                        }
                    }
                    "foodbridge" -> {
                        Spacer(modifier = Modifier.height(if (isMobile) 40.dp else 80.dp))

                        PortfolioCard(
                            modifier = Modifier.fillMaxWidth().fadeInSlideUp(durationMs = 500, delayMs = 250),
                            accentBorderColor = Color(0xFF00875A).copy(alpha = 0.3f)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(if (isMobile) 16.dp else 24.dp)
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.Restaurant,
                                            contentDescription = "FoodBridge",
                                            tint = Color(0xFF00875A),
                                            modifier = Modifier.size(if (isMobile) 22.dp else 28.dp)
                                        )
                                        Text(
                                            text = "Food Bridge Interactive Showcase",
                                            style = if (isMobile) MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold) else MaterialTheme.typography.headlineMedium,
                                            color = Color(0xFF00875A),
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                    Text(
                                        text = "Tap through the recreated UI screens below: Donor Hub, Post Food Donation, Surplus Marketplace, and Volunteer Pickups.",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.widthIn(max = 640.dp)
                                    )
                                }

                                FoodBridgeInteractiveShowcase(
                                    initialTab = FoodBridgeTab.HOME
                                )
                            }
                        }
                    }
                    "agriconnect" -> {
                        Spacer(modifier = Modifier.height(if (isMobile) 40.dp else 80.dp))

                        PortfolioCard(
                            modifier = Modifier.fillMaxWidth().fadeInSlideUp(durationMs = 500, delayMs = 250),
                            accentBorderColor = PrimaryGreen.copy(alpha = 0.3f)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(if (isMobile) 16.dp else 24.dp)
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.Agriculture,
                                            contentDescription = "Agri Connect",
                                            tint = PrimaryGreen,
                                            modifier = Modifier.size(if (isMobile) 22.dp else 28.dp)
                                        )
                                        Text(
                                            text = "Agri Connect Interactive Showcase",
                                            style = if (isMobile) MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold) else MaterialTheme.typography.headlineMedium,
                                            color = PrimaryGreen,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                    Text(
                                        text = "Tap through the recreated UI screens below: Farmer Dashboard, OpenWeatherMap intelligence, and Gemini AI Assistant.",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.widthIn(max = 640.dp)
                                    )
                                }

                                AgriConnectInteractiveShowcase(
                                    initialTab = AgriScreenTab.WEATHER
                                )
                            }
                        }
                    }
                    "carenest" -> {
                        Spacer(modifier = Modifier.height(if (isMobile) 40.dp else 80.dp))

                        PortfolioCard(
                            modifier = Modifier.fillMaxWidth().fadeInSlideUp(durationMs = 500, delayMs = 250),
                            accentBorderColor = Color(0xFF00ACC1).copy(alpha = 0.3f)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(if (isMobile) 16.dp else 24.dp)
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.Favorite,
                                            contentDescription = "CareNest",
                                            tint = Color(0xFF00ACC1),
                                            modifier = Modifier.size(if (isMobile) 22.dp else 28.dp)
                                        )
                                        Text(
                                            text = "CareNest Interactive Showcase",
                                            style = if (isMobile) MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold) else MaterialTheme.typography.headlineMedium,
                                            color = Color(0xFF00ACC1),
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                    Text(
                                        text = "Tap through the recreated UI screens below: Healthcare Services Hub and AI Symptom Checker.",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.widthIn(max = 640.dp)
                                    )
                                }

                                CareNestInteractiveShowcase(
                                    initialTab = CareNestTab.SERVICES
                                )
                            }
                        }
                    }
                    "portfolio" -> {
                        Spacer(modifier = Modifier.height(if (isMobile) 40.dp else 80.dp))

                        PortfolioCard(
                            modifier = Modifier.fillMaxWidth().fadeInSlideUp(durationMs = 500, delayMs = 250),
                            accentBorderColor = PrimaryGreen.copy(alpha = 0.3f)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(if (isMobile) 16.dp else 24.dp)
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.Language,
                                            contentDescription = "Web Showcase",
                                            tint = PrimaryGreen,
                                            modifier = Modifier.size(if (isMobile) 22.dp else 28.dp)
                                        )
                                        Text(
                                            text = "Web & Terminal Showcase",
                                            style = if (isMobile) MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold) else MaterialTheme.typography.headlineMedium,
                                            color = PrimaryGreen,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                    Text(
                                        text = "Interact with the live web snapshot, terminal engine, and Google Gemini AI workspace.",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.widthIn(max = 640.dp)
                                    )
                                }

                                PortfolioWebInteractiveShowcase()
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(if (isMobile) 40.dp else 80.dp))

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
                        verticalArrangement = Arrangement.spacedBy(if (isMobile) 16.dp else 24.dp)
                    ) {
                        Text(
                            text = "Technology Stack",
                            style = if (isMobile) MaterialTheme.typography.headlineMedium else MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        FlowRow(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
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

                Spacer(modifier = Modifier.height(if (isMobile) 40.dp else 80.dp))

                // ─── Footer ──────────────────────────────────────────────────
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    HorizontalDivider(color = BorderDark, thickness = 1.dp)
                    Text(
                        text = "© 2026 Dhruva Khatavkar // Built with Native Compose Multiplatform",
                        style = MaterialTheme.typography.labelSmall,
                        color = TextMuted,
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.height(if (isMobile) 24.dp else 40.dp))
            }
        }
    }
}
