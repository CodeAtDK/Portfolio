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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.portfolio.ui.theme.*

enum class PortfolioWebTab(val title: String, val subtitle: String) {
    HERO("Web Hero & Terminal", "Home section & live Kotlin mockup"),
    AI_TERMINAL("Dhruva AI Workspace", "Embedded Google Gemini assistant"),
    TECH_SPECS("KMP Architecture", "Multiplatform Compose & Ktor")
}

/**
 * Compact Web Browser Snapshot Mockup for "This Portfolio" project card.
 */
@Composable
fun PortfolioWebSnapshotCard(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(Color(0xFF0D1117))
            .border(1.5.dp, PrimaryGreen.copy(alpha = 0.4f), RoundedCornerShape(18.dp))
            .padding(2.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Browser Address Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF161B22))
                    .padding(horizontal = 10.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Traffic light dots
                Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                    Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(Color(0xFFFF5F56)))
                    Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(Color(0xFFFFBD2E)))
                    Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(Color(0xFF27C93F)))
                }

                // URL Pill
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(Color(0xFF0D1117))
                        .border(0.5.dp, Color.White.copy(alpha = 0.15f), RoundedCornerShape(50))
                        .padding(horizontal = 10.dp, vertical = 2.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.Default.Lock, contentDescription = null, tint = PrimaryGreen, modifier = Modifier.size(8.dp))
                        Text(
                            text = "dhruvakhatavkar.dev",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontSize = 8.sp,
                                fontFamily = FontFamily.Monospace
                            ),
                            color = Color(0xFFC9D1D9)
                        )
                    }
                }

                Box(modifier = Modifier.size(8.dp))
            }

            HorizontalDivider(color = Color(0xFF30363D), thickness = 0.5.dp)

            // Web Content Preview
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF0D1117))
                    .padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Mini Top Nav
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(modifier = Modifier.size(5.dp).clip(CircleShape).background(PrimaryGreen))
                        Text("DK // dev", style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.sp, fontWeight = FontWeight.Bold), color = Color.White)
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text("about", style = MaterialTheme.typography.labelSmall.copy(fontSize = 7.sp), color = TextMuted)
                        Text("projects", style = MaterialTheme.typography.labelSmall.copy(fontSize = 7.sp, fontWeight = FontWeight.Bold), color = PrimaryGreen)
                        Text("ai", style = MaterialTheme.typography.labelSmall.copy(fontSize = 7.sp), color = TertiaryBlue)
                    }
                }

                // Mini Hero & Terminal Split
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Left Hero Info
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = "Android Developer",
                            style = MaterialTheme.typography.labelSmall.copy(fontSize = 7.sp, fontWeight = FontWeight.SemiBold),
                            color = PrimaryGreen
                        )
                        Text(
                            text = "Building native Android & KMP experiences.",
                            style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp, fontWeight = FontWeight.Bold, lineHeight = 11.sp),
                            color = Color.White
                        )
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(PrimaryGreen)
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text("Explore", style = MaterialTheme.typography.labelSmall.copy(fontSize = 6.sp, fontWeight = FontWeight.Bold), color = BackgroundDark)
                            }
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(SurfaceDark)
                                    .border(0.5.dp, Color.White.copy(alpha = 0.2f), RoundedCornerShape(4.dp))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text("Ask AI", style = MaterialTheme.typography.labelSmall.copy(fontSize = 6.sp, fontWeight = FontWeight.SemiBold), color = Color.White)
                            }
                        }
                    }

                    // Right Mini Terminal
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFF161B22))
                            .border(0.5.dp, Color(0xFF30363D), RoundedCornerShape(8.dp))
                            .padding(6.dp)
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(horizontalArrangement = Arrangement.spacedBy(3.dp)) {
                                    Box(modifier = Modifier.size(4.dp).clip(CircleShape).background(Color(0xFFFF5F56)))
                                    Box(modifier = Modifier.size(4.dp).clip(CircleShape).background(Color(0xFFFFBD2E)))
                                    Box(modifier = Modifier.size(4.dp).clip(CircleShape).background(Color(0xFF27C93F)))
                                }
                                Text("Dhruva.kt", style = MaterialTheme.typography.labelSmall.copy(fontSize = 6.sp), color = TextMuted)
                            }
                            HorizontalDivider(color = Color(0xFF30363D), thickness = 0.5.dp)
                            Text("val name = \"Dhruva\"", style = MaterialTheme.typography.labelSmall.copy(fontSize = 6.sp), color = SecondaryWarm)
                            Text("val role = \"Android Dev\"", style = MaterialTheme.typography.labelSmall.copy(fontSize = 6.sp), color = SecondaryWarm)
                            Text("val stack = listOf(KMP)", style = MaterialTheme.typography.labelSmall.copy(fontSize = 6.sp), color = TertiaryBlue)
                            Text("val status = \"Open\" ▋", style = MaterialTheme.typography.labelSmall.copy(fontSize = 6.sp, fontWeight = FontWeight.Bold), color = PrimaryGreen)
                        }
                    }
                }

                // Mini stats footer
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xFF161B22))
                        .padding(vertical = 4.dp, horizontal = 6.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Text("3+ Apps", style = MaterialTheme.typography.labelSmall.copy(fontSize = 7.sp, fontWeight = FontWeight.Bold), color = PrimaryGreen)
                    Text("•", style = MaterialTheme.typography.labelSmall.copy(fontSize = 7.sp), color = TextDim)
                    Text("2+ Years", style = MaterialTheme.typography.labelSmall.copy(fontSize = 7.sp, fontWeight = FontWeight.Bold), color = SecondaryWarm)
                    Text("•", style = MaterialTheme.typography.labelSmall.copy(fontSize = 7.sp), color = TextDim)
                    Text("KMP & CMP", style = MaterialTheme.typography.labelSmall.copy(fontSize = 7.sp, fontWeight = FontWeight.Bold), color = TertiaryBlue)
                }
            }
        }
    }
}

/**
 * Interactive Full Browser Showcase for "This Portfolio" in Case Study view.
 */
@Composable
fun PortfolioWebInteractiveShowcase(modifier: Modifier = Modifier) {
    var selectedTab by remember { mutableStateOf(PortfolioWebTab.HERO) }
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
            PortfolioWebTab.entries.forEach { tab ->
                val isSelected = selectedTab == tab
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(if (isSelected) PrimaryGreen else Color.Transparent)
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

        // Full Browser Window Container
        Box(
            modifier = Modifier
                .widthIn(max = if (isMobile) 330.dp else 480.dp)
                .fillMaxWidth()
                .shadow(elevation = 32.dp, shape = RoundedCornerShape(24.dp), spotColor = PrimaryGreen.copy(alpha = 0.2f))
                .clip(RoundedCornerShape(24.dp))
                .background(Color(0xFF0D1117))
                .border(2.dp, Color(0xFF30363D), RoundedCornerShape(24.dp))
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                // macOS Browser Titlebar
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF161B22))
                        .padding(horizontal = 14.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        Box(modifier = Modifier.size(10.dp).clip(CircleShape).background(Color(0xFFFF5F56)))
                        Box(modifier = Modifier.size(10.dp).clip(CircleShape).background(Color(0xFFFFBD2E)))
                        Box(modifier = Modifier.size(10.dp).clip(CircleShape).background(Color(0xFF27C93F)))
                    }

                    // Centered URL Bar
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(Color(0xFF0D1117))
                            .border(1.dp, Color(0xFF30363D), RoundedCornerShape(50))
                            .padding(horizontal = 14.dp, vertical = 4.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(imageVector = Icons.Default.Lock, contentDescription = null, tint = PrimaryGreen, modifier = Modifier.size(11.dp))
                            Text(
                                text = "https://dhruvakhatavkar.dev",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontSize = 11.sp,
                                    fontFamily = FontFamily.Monospace
                                ),
                                color = Color(0xFFC9D1D9)
                            )
                        }
                    }

                    Box(modifier = Modifier.size(10.dp))
                }

                HorizontalDivider(color = Color(0xFF30363D), thickness = 1.dp)

                // Tab Content with Crossfade
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(380.dp)
                        .background(Color(0xFF0D1117))
                        .padding(16.dp)
                ) {
                    Crossfade(targetState = selectedTab, animationSpec = tween(300)) { tab ->
                        when (tab) {
                            PortfolioWebTab.HERO -> PortfolioHeroTabContent()
                            PortfolioWebTab.AI_TERMINAL -> PortfolioAiTabContent()
                            PortfolioWebTab.TECH_SPECS -> PortfolioSpecsTabContent()
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun PortfolioHeroTabContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(PrimaryGreen))
                Text("Dhruva.dev", style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold), color = Color.White)
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(PrimaryGreen.copy(alpha = 0.1f))
                    .border(0.5.dp, PrimaryGreen.copy(alpha = 0.4f), RoundedCornerShape(50))
                    .padding(horizontal = 10.dp, vertical = 3.dp)
            ) {
                Text("open to work", style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp), color = PrimaryGreen)
            }
        }

        // Terminal Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFF161B22))
                .border(1.dp, Color(0xFF30363D), RoundedCornerShape(12.dp))
                .padding(12.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(Color(0xFFFF5F56)))
                        Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(Color(0xFFFFBD2E)))
                        Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(Color(0xFF27C93F)))
                    }
                    Text("DhruvaKhatavkar.kt", style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp), color = TextMuted)
                }
                HorizontalDivider(color = Color(0xFF30363D), thickness = 0.5.dp)

                Text("val name = \"Dhruva Khatavkar\"", style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp), color = SecondaryWarm)
                Text("val role = \"Android Developer\"", style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp), color = SecondaryWarm)
                Text("val location = \"Beed, India\"", style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp), color = SecondaryWarm)
                Text("val stack = listOf(\"Kotlin\", \"Compose\", \"KMP\")", style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp), color = TertiaryBlue)
                Text("val status = \"Open to roles\" ▋", style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp, fontWeight = FontWeight.Bold), color = PrimaryGreen)
            }
        }

        // Stats strip
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFF161B22))
                .border(0.5.dp, Color(0xFF30363D), RoundedCornerShape(10.dp))
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("3+", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), color = PrimaryGreen)
                Text("Apps", style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp), color = TextMuted)
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("2+", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), color = PrimaryGreen)
                Text("Years", style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp), color = TextMuted)
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("1", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), color = PrimaryGreen)
                Text("Intern", style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp), color = TextMuted)
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("KMP", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), color = PrimaryGreen)
                Text("Learning", style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp), color = TextMuted)
            }
        }
    }
}

@Composable
private fun PortfolioAiTabContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.AutoAwesome, contentDescription = null, tint = PrimaryGreen, modifier = Modifier.size(16.dp))
                Text("Dhruva AI (Gemini 3.5)", style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold), color = Color.White)
            }
            Text("gemini-3.5-flash-lite", style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp, fontFamily = FontFamily.Monospace), color = TertiaryBlue)
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFF161B22))
                .border(1.dp, Color(0xFF30363D), RoundedCornerShape(12.dp))
                .padding(12.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "> user_query: \"What is Dhruva's stack?\"",
                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                    color = PrimaryGreen
                )
                Text(
                    text = "Dhruva specializes in Kotlin, Jetpack Compose, MVVM Architecture, Coroutines/Flow, Room, Hilt, and Kotlin Multiplatform (KMP).",
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp, lineHeight = 16.sp),
                    color = Color(0xFFE6EDF3)
                )
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(PrimaryGreen.copy(alpha = 0.1f))
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text("gemini-3.5-flash-lite", style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.sp), color = PrimaryGreen)
                    }
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(SecondaryWarm.copy(alpha = 0.1f))
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text("1.1s", style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.sp), color = SecondaryWarm)
                    }
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(TertiaryBlue.copy(alpha = 0.1f))
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text("185 tokens", style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.sp), color = TertiaryBlue)
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(50))
                .background(Color(0xFF161B22))
                .border(0.5.dp, Color(0xFF30363D), RoundedCornerShape(50))
                .padding(horizontal = 14.dp, vertical = 8.dp)
        ) {
            Text("Ask anything about Dhruva's work...", style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp), color = TextMuted)
        }
    }
}

@Composable
private fun PortfolioSpecsTabContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text("Multiplatform Architecture", style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold), color = PrimaryGreen)
        Text(
            text = "Shared Compose UI & business logic compiling natively to Android, Desktop (JVM), and the Web (WASM & JS).",
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp),
            color = Color(0xFFC9D1D9)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF161B22))
                    .padding(8.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("Android / Desktop", style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp, fontWeight = FontWeight.Bold), color = Color.White)
                    Text("CIO Engine • Skiko", style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.sp), color = PrimaryGreen)
                }
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF161B22))
                    .padding(8.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("Web (JS / WASM)", style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp, fontWeight = FontWeight.Bold), color = Color.White)
                    Text("Ktor JS • Canvas", style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.sp), color = TertiaryBlue)
                }
            }
        }
    }
}
