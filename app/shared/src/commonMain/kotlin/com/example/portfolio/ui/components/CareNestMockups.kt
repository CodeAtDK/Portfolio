package com.example.portfolio.ui.components

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.portfolio.ui.theme.PrimaryGreen
import com.example.portfolio.ui.theme.SurfaceDark
import com.example.portfolio.ui.theme.TertiaryBlue

enum class CareNestTab(val title: String, val subtitle: String) {
    SERVICES("Healthcare Hub", "Doctor consults & services"),
    SYMPTOM_CHECKER("AI Symptom Checker", "Interactive triage questionnaire")
}

/**
 * Interactive Phone Showcase for CareNest displaying high-fidelity recreations
 * of the user's actual Android application screenshots.
 */
@Composable
fun CareNestInteractiveShowcase(
    modifier: Modifier = Modifier,
    initialTab: CareNestTab = CareNestTab.SERVICES
) {
    var selectedTab by remember { mutableStateOf(initialTab) }
    val isMobile = com.example.portfolio.ui.theme.LocalIsMobile.current

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(if (isMobile) 14.dp else 20.dp)
    ) {
        // Tab Selector Pills
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .background(SurfaceDark)
                .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(50))
                .padding(4.dp)
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            CareNestTab.entries.forEach { tab ->
                val isSelected = selectedTab == tab
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(if (isSelected) Color(0xFF00ACC1) else Color.Transparent)
                        .clickable { selectedTab = tab }
                        .padding(horizontal = if (isMobile) 10.dp else 16.dp, vertical = if (isMobile) 6.dp else 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = tab.title,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            fontSize = if (isMobile) 10.sp else 11.sp
                        ),
                        color = if (isSelected) Color(0xFF0F172A) else Color(0xFF94A3B8)
                    )
                }
            }
        }

        // Phone Frame Container
        Box(
            modifier = Modifier
                .widthIn(max = if (isMobile) 320.dp else 340.dp)
                .fillMaxWidth()
                .shadow(elevation = 32.dp, shape = RoundedCornerShape(44.dp), spotColor = Color(0xFF00ACC1).copy(alpha = 0.25f))
                .clip(RoundedCornerShape(44.dp))
                .background(Color(0xFF07141A))
                .border(3.5.dp, Color(0xFF0E4050), RoundedCornerShape(44.dp))
                .padding(6.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(38.dp))
                    .height(if (isMobile) 560.dp else 640.dp)
            ) {
                // Top Phone Bezel & Camera Punch-hole
                TopCareNestStatusBar(time = if (selectedTab == CareNestTab.SYMPTOM_CHECKER) "11:41" else "11:40")

                // Screen Content with smooth crossfade
                Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
                    Crossfade(targetState = selectedTab, animationSpec = tween(300)) { tab ->
                        when (tab) {
                            CareNestTab.SERVICES -> CareNestHomeScreen(onOpenSymptomChecker = { selectedTab = CareNestTab.SYMPTOM_CHECKER })
                            CareNestTab.SYMPTOM_CHECKER -> CareNestSymptomCheckerScreen()
                        }
                    }
                }

                // Bottom Home Bar Indicator
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF1F5F9))
                        .padding(vertical = 6.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .width(100.dp)
                            .height(4.dp)
                            .clip(RoundedCornerShape(2.dp))
                            .background(Color(0xFF475569))
                    )
                }
            }
        }
    }
}

/**
 * Compact Mini Phone Card Preview for "CareNest" in ProjectsSection.
 */
@Composable
fun CareNestMiniPreviewCard(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .widthIn(max = 260.dp)
            .fillMaxWidth(0.85f)
            .clip(RoundedCornerShape(18.dp))
            .background(Color(0xFF07141A))
            .border(1.5.dp, Color(0xFF00ACC1).copy(alpha = 0.5f), RoundedCornerShape(18.dp))
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF8FAFC))
                .padding(bottom = 8.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            // Teal header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Brush.verticalGradient(listOf(Color(0xFF0E7490), Color(0xFF0891B2))))
                    .padding(horizontal = 8.dp, vertical = 6.dp)
            ) {
                Column {
                    Text("CareNest", style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp, fontWeight = FontWeight.Bold), color = Color.White)
                    Text("Welcome to CareNest", style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp, fontWeight = FontWeight.Bold), color = Color.White)
                    Text("✓ 24/7 Doctors Online", style = MaterialTheme.typography.labelSmall.copy(fontSize = 7.sp), color = Color(0xFF80DEEA))
                }
            }

            // Quick Service Cards
            Row(
                modifier = Modifier.padding(horizontal = 6.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                MiniServicePill("Doctor Chat", Color(0xFFE0F7F6), Color(0xFF00838F), Modifier.weight(1f))
                MiniServicePill("Video Consult", Color(0xFFE0F2FE), Color(0xFF0284C7), Modifier.weight(1f))
            }
            Row(
                modifier = Modifier.padding(horizontal = 6.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                MiniServicePill("Medical Shop", Color(0xFFE8F5E9), Color(0xFF2E7D32), Modifier.weight(1f))
                MiniServicePill("AI Triage", Color(0xFFFFE4E6), Color(0xFFE11D48), Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun MiniServicePill(title: String, bg: Color, textColor: Color, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .background(bg)
            .padding(vertical = 5.dp, horizontal = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = title, style = MaterialTheme.typography.labelSmall.copy(fontSize = 7.sp, fontWeight = FontWeight.Bold), color = textColor)
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// SCREEN 1: Healthcare Services Hub
// ─────────────────────────────────────────────────────────────────────────────
@Composable
fun CareNestHomeScreen(onOpenSymptomChecker: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC))
            .verticalScroll(rememberScrollState())
    ) {
        // App Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu", tint = Color(0xFF1E293B), modifier = Modifier.size(20.dp))
            Text(
                text = "CareNest",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = Color(0xFF0F172A)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Welcome Hero Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        Brush.linearGradient(
                            colors = listOf(Color(0xFF0E7490), Color(0xFF0891B2), Color(0xFF06B6D4))
                        )
                    )
                    .padding(18.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "Welcome to CareNest",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = Color.White
                    )
                    Text(
                        text = "How can we assist your health today?",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.9f)
                    )
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(Color.Black.copy(alpha = 0.18f))
                            .border(0.5.dp, Color.White.copy(alpha = 0.3f), RoundedCornerShape(50))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null, tint = Color(0xFF4ADE80), modifier = Modifier.size(11.dp))
                            Text(
                                text = "24/7 Doctors Online • Instant Triage",
                                style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp, fontWeight = FontWeight.SemiBold),
                                color = Color.White
                            )
                        }
                    }
                }
            }

            // Section Title
            Text(
                text = "Healthcare Services",
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                color = Color(0xFF0F172A)
            )

            // 2x4 Service Grid
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    ServiceGridCard(
                        icon = Icons.Default.ChatBubble,
                        iconTint = Color(0xFF007987),
                        cardBg = Color(0xFFE0F7F6),
                        title = "Doctor Chat",
                        subtitle = "Instant consult",
                        badge = "Active",
                        badgeBg = Color(0xFFB2EBF2),
                        badgeColor = Color(0xFF007987),
                        modifier = Modifier.weight(1f)
                    )
                    ServiceGridCard(
                        icon = Icons.Default.Videocam,
                        iconTint = Color(0xFF0284C7),
                        cardBg = Color(0xFFE0F2FE),
                        title = "Video Consult",
                        subtitle = "Live doctor video",
                        modifier = Modifier.weight(1f)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    ServiceGridCard(
                        icon = Icons.Default.MedicalServices,
                        iconTint = Color(0xFF10B981),
                        cardBg = Color(0xFFE8F5E9),
                        title = "Medical Shop",
                        subtitle = "Meds & equipment",
                        modifier = Modifier.weight(1f)
                    )
                    ServiceGridCard(
                        icon = Icons.Default.Description,
                        iconTint = Color(0xFFF59E0B),
                        cardBg = Color(0xFFFEF3C7),
                        title = "Health Library",
                        subtitle = "Schemes & guides",
                        modifier = Modifier.weight(1f)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    ServiceGridCard(
                        icon = Icons.Default.Favorite,
                        iconTint = Color(0xFFF43F5E),
                        cardBg = Color(0xFFFFE4E6),
                        title = "Symptom Check",
                        subtitle = "Instant diagnostics",
                        badge = "AI Triage",
                        badgeBg = Color(0xFFFFCDD2),
                        badgeColor = Color(0xFFE11D48),
                        onClick = onOpenSymptomChecker,
                        modifier = Modifier.weight(1f)
                    )
                    ServiceGridCard(
                        icon = Icons.Default.Eco,
                        iconTint = Color(0xFF10B981),
                        cardBg = Color(0xFFE8F8F0),
                        title = "Diet & Nutrition",
                        subtitle = "Personalized meals",
                        modifier = Modifier.weight(1f)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    ServiceGridCard(
                        icon = Icons.Default.Medication,
                        iconTint = Color(0xFF8B5CF6),
                        cardBg = Color(0xFFF3E8FF),
                        title = "Pill Reminder",
                        subtitle = "Daily medication",
                        modifier = Modifier.weight(1f)
                    )
                    ServiceGridCard(
                        icon = Icons.Default.BarChart,
                        iconTint = Color(0xFF0284C7),
                        cardBg = Color(0xFFE0F2FE),
                        title = "Lab Reports",
                        subtitle = "Records & scans",
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// SCREEN 2: AI Symptom Checker Form
// ─────────────────────────────────────────────────────────────────────────────
@Composable
fun CareNestSymptomCheckerScreen() {
    var selectedGender by remember { mutableStateOf("Female") }
    var priorConditions by remember { mutableStateOf("No") }
    var fever by remember { mutableStateOf("No") }
    var coughType by remember { mutableStateOf("Dry Cough") }
    var painIntensity by remember { mutableStateOf("Mild") }
    var fatigue by remember { mutableStateOf("No") }
    var shortnessOfBreath by remember { mutableStateOf("No") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC))
            .verticalScroll(rememberScrollState())
    ) {
        // App Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu", tint = Color(0xFF1E293B), modifier = Modifier.size(20.dp))
            Text(
                text = "CareNest",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = Color(0xFF0F172A)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Header AI Banner Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
                    .background(Color(0xFFFFE4E6))
                    .border(1.dp, Color(0xFFFECDD3), RoundedCornerShape(18.dp))
                    .padding(14.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFF43F5E)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(imageVector = Icons.Default.Favorite, contentDescription = null, tint = Color.White, modifier = Modifier.size(20.dp))
                    }
                    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                        Text(
                            text = "AI Symptom Checker",
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                            color = Color(0xFF1E293B)
                        )
                        Text(
                            text = "Answer the questions below for instant medical triage & AI insights.",
                            style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp),
                            color = Color(0xFF64748B)
                        )
                    }
                }
            }

            // 1. Patient Demographics Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
                    .background(Color.White)
                    .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(18.dp))
                    .padding(14.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(
                        text = "1. Patient Demographics",
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                        color = Color(0xFF0E7490)
                    )

                    // Patient Age
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(
                            text = "Patient Age (Years)",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
                            color = Color(0xFF1E293B)
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(10.dp))
                                .padding(horizontal = 12.dp, vertical = 8.dp)
                        ) {
                            Text("e.g. 28", style = MaterialTheme.typography.bodySmall, color = Color(0xFF94A3B8))
                        }
                    }

                    // Gender Radio Options
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(
                            text = "Gender",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
                            color = Color(0xFF1E293B)
                        )
                        Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                            listOf("Male", "Female", "Other").forEach { gender ->
                                RadioOption(
                                    label = gender,
                                    selected = selectedGender == gender,
                                    onClick = { selectedGender = gender }
                                )
                            }
                        }
                    }

                    // Prior Medical Conditions
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(
                            text = "Any Prior Medical Conditions?",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
                            color = Color(0xFF1E293B)
                        )
                        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            listOf("Yes", "No").forEach { opt ->
                                RadioOption(
                                    label = opt,
                                    selected = priorConditions == opt,
                                    onClick = { priorConditions = opt }
                                )
                            }
                        }
                    }
                }
            }

            // 2. Core Symptoms Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
                    .background(Color.White)
                    .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(18.dp))
                    .padding(14.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(
                        text = "2. Core Symptoms",
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                        color = Color(0xFF0E7490)
                    )

                    // Fever
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text("Fever / High Body Temperature?", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold), color = Color(0xFF1E293B))
                        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            listOf("Yes", "No").forEach { opt ->
                                RadioOption(label = opt, selected = fever == opt, onClick = { fever = opt })
                            }
                        }
                    }

                    // Cough Type
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text("Cough Type", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold), color = Color(0xFF1E293B))
                        Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                            listOf("Dry Cough", "Productive / Wet").forEach { opt ->
                                RadioOption(label = opt, selected = coughType == opt, onClick = { coughType = opt })
                            }
                        }
                    }

                    // General Pain Intensity
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text("General Pain Intensity", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold), color = Color(0xFF1E293B))
                        Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                            listOf("Mild", "Moderate", "Severe").forEach { opt ->
                                RadioOption(label = opt, selected = painIntensity == opt, onClick = { painIntensity = opt })
                            }
                        }
                    }

                    // Fatigue
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text("Excessive Fatigue / Weakness?", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold), color = Color(0xFF1E293B))
                        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            listOf("Yes", "No").forEach { opt ->
                                RadioOption(label = opt, selected = fatigue == opt, onClick = { fatigue = opt })
                            }
                        }
                    }

                    // Shortness of Breath
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text("Shortness of Breath / Breathing Difficulty?", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold), color = Color(0xFF1E293B))
                        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            listOf("Yes", "No").forEach { opt ->
                                RadioOption(label = opt, selected = shortnessOfBreath == opt, onClick = { shortnessOfBreath = opt })
                            }
                        }
                    }
                }
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Reusable Subcomponents
// ─────────────────────────────────────────────────────────────────────────────

@Composable
private fun TopCareNestStatusBar(time: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF0E7490))
            .padding(horizontal = 14.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = time,
            style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp, fontWeight = FontWeight.Bold),
            color = Color.White
        )
        // Camera punch hole
        Box(
            modifier = Modifier
                .size(10.dp)
                .clip(CircleShape)
                .background(Color.Black)
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.SignalCellularAlt, contentDescription = null, tint = Color.White, modifier = Modifier.size(10.dp))
            Icon(imageVector = Icons.Default.Wifi, contentDescription = null, tint = Color.White, modifier = Modifier.size(10.dp))
            Icon(imageVector = Icons.Default.BatteryFull, contentDescription = null, tint = Color.White, modifier = Modifier.size(10.dp))
        }
    }
}

@Composable
private fun ServiceGridCard(
    icon: ImageVector,
    iconTint: Color,
    cardBg: Color,
    title: String,
    subtitle: String,
    badge: String? = null,
    badgeBg: Color = Color.Transparent,
    badgeColor: Color = Color.Transparent,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(18.dp))
            .background(cardBg)
            .clickable { onClick() }
            .padding(14.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = icon, contentDescription = null, tint = iconTint, modifier = Modifier.size(24.dp))
                if (badge != null) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(badgeBg)
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = badge,
                            style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.sp, fontWeight = FontWeight.Bold),
                            color = badgeColor
                        )
                    }
                }
            }

            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp, fontWeight = FontWeight.Bold),
                    color = Color(0xFF0F172A)
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.sp),
                    color = Color(0xFF64748B)
                )
            }
        }
    }
}

@Composable
private fun RadioOption(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clickable { onClick() }
            .padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(14.dp)
                .clip(CircleShape)
                .border(1.5.dp, if (selected) Color(0xFF0E7490) else Color(0xFF94A3B8), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            if (selected) {
                Box(
                    modifier = Modifier
                        .size(7.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF0E7490))
                )
            }
        }
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
            color = Color(0xFF334155)
        )
    }
}
