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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.*
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.portfolio.ui.theme.PrimaryGreen
import com.example.portfolio.ui.theme.SecondaryWarm
import com.example.portfolio.ui.theme.SurfaceDark

enum class AgriScreenTab(val title: String, val subtitle: String) {
    HOME("Home Dashboard", "Crop & Weather Overview"),
    WEATHER("Live Weather", "Hourly & 6-Day Forecast"),
    AI_CHAT("AgriAI Assistant", "Powered by Gemini AI")
}

/**
 * Interactive Phone Showcase for Agri Connect displaying high-fidelity recreations
 * of the user's actual Android application screenshots.
 */
@Composable
fun AgriConnectInteractiveShowcase(
    modifier: Modifier = Modifier,
    initialTab: AgriScreenTab = AgriScreenTab.HOME
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
            AgriScreenTab.entries.forEach { tab ->
                val isSelected = selectedTab == tab
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(if (isSelected) PrimaryGreen else Color.Transparent)
                        .clickable { selectedTab = tab }
                        .padding(horizontal = if (isMobile) 10.dp else 14.dp, vertical = if (isMobile) 6.dp else 8.dp),
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
                .shadow(elevation = 32.dp, shape = RoundedCornerShape(44.dp), spotColor = PrimaryGreen.copy(alpha = 0.2f))
                .clip(RoundedCornerShape(44.dp))
                .background(Color(0xFF0C100D))
                .border(3.5.dp, Color(0xFF2C3E2D), RoundedCornerShape(44.dp))
                .padding(6.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(38.dp))
                    .height(if (isMobile) 560.dp else 640.dp)
            ) {
                // Top Phone Bezel & Camera Punch-hole
                TopDeviceStatusBar(time = if (selectedTab == AgriScreenTab.AI_CHAT) "10:35" else "10:34")

                // Screen Content with smooth crossfade
                Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
                    Crossfade(targetState = selectedTab, animationSpec = tween(300)) { tab ->
                        when (tab) {
                            AgriScreenTab.HOME -> AgriConnectHomeScreen(onOpenWeather = { selectedTab = AgriScreenTab.WEATHER })
                            AgriScreenTab.WEATHER -> AgriConnectWeatherScreen()
                            AgriScreenTab.AI_CHAT -> AgriConnectAiScreen()
                        }
                    }
                }

                // Bottom Home Bar Indicator
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            if (selectedTab == AgriScreenTab.WEATHER) Color(0xFF14242F) else Color(0xFFE8EFE6)
                        )
                        .padding(vertical = 6.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .width(100.dp)
                            .height(4.dp)
                            .clip(RoundedCornerShape(2.dp))
                            .background(
                                if (selectedTab == AgriScreenTab.WEATHER) Color.White.copy(alpha = 0.6f) else Color(0xFF4A5568)
                            )
                    )
                }
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// SCREEN 1: Home / Farmer Dashboard
// ─────────────────────────────────────────────────────────────────────────────
@Composable
fun AgriConnectHomeScreen(onOpenWeather: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F7F3))
            .verticalScroll(rememberScrollState())
    ) {
        // Green Top Header Area
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFF1B5E20), Color(0xFF2E7D32), Color(0xFF1E6023))
                    )
                )
                .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 48.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                // Top Nav Row (Menu + Title + Avatar)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = "AGRI CONNECT",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            ),
                            color = Color.White
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .clip(CircleShape)
                            .background(Color.Black.copy(alpha = 0.3f))
                            .border(1.5.dp, Color.White, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Profile",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "AGRI-CONNECT",
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontSize = 10.sp,
                        letterSpacing = 1.2.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = Color.White.copy(alpha = 0.8f)
                )

                Text(
                    text = "Hello, Farmer 👋",
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                    color = Color.White
                )
            }
        }

        // Overlapping Weather Card
        Box(
            modifier = Modifier
                .offset(y = (-32).dp)
                .padding(horizontal = 14.dp)
                .fillMaxWidth()
                .shadow(elevation = 8.dp, shape = RoundedCornerShape(20.dp), spotColor = Color.Black.copy(alpha = 0.1f))
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
                .clickable { onOpenWeather() }
                .padding(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Left temp & location
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = "28°C",
                        style = MaterialTheme.typography.displayMedium.copy(
                            fontWeight = FontWeight.Black,
                            fontSize = 32.sp
                        ),
                        color = Color(0xFF1B1B1B)
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = Color(0xFF2E7D32),
                            modifier = Modifier.size(13.dp)
                        )
                        Text(
                            text = "Pune, India",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
                            color = Color(0xFF2E7D32)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .padding(top = 2.dp)
                            .clip(RoundedCornerShape(50))
                            .background(Color(0xFFE8F5E9))
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = "Tap for 7-day forecast ->",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            color = Color(0xFF2E7D32)
                        )
                    }
                }

                // Right weather state & details
                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFFFF3E0)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.WbSunny,
                            contentDescription = null,
                            tint = Color(0xFFFFA000),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Text(
                        text = "Partly Cloudy",
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                        color = Color(0xFF2D3748)
                    )
                    Text(
                        text = "H: 30° • L: 22°",
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                        color = Color(0xFF718096)
                    )
                    Text(
                        text = "Humidity: 65%",
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                        color = Color(0xFF718096)
                    )
                }
            }
        }

        // Quick Actions Section
        Column(
            modifier = Modifier
                .offset(y = (-20).dp)
                .padding(horizontal = 14.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Quick Actions",
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                    color = Color(0xFF1A202C)
                )
                Text(
                    text = "Services",
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                    color = Color(0xFF2E7D32)
                )
            }

            // 3 Action Cards in a row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                QuickActionCard(
                    icon = Icons.Default.ShoppingCart,
                    iconBg = Color(0xFFE8F5E9),
                    iconTint = Color(0xFF2E7D32),
                    title = "Farmer Shop",
                    subtitle = "Seeds & Tools",
                    modifier = Modifier.weight(1f)
                )
                QuickActionCard(
                    icon = Icons.Default.WaterDrop,
                    iconBg = Color(0xFFE1F5FE),
                    iconTint = Color(0xFF0288D1),
                    title = "Crop Water",
                    subtitle = "Water Levels",
                    modifier = Modifier.weight(1f)
                )
                @Suppress("DEPRECATION")
                QuickActionCard(
                    icon = Icons.Filled.TrendingUp,
                    iconBg = Color(0xFFFFF8E1),
                    iconTint = Color(0xFFF57C00),
                    title = "Mandi Rates",
                    subtitle = "Daily Prices",
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // Explore Agri-Hub Section
        Column(
            modifier = Modifier
                .offset(y = (-10).dp)
                .padding(horizontal = 14.dp, vertical = 6.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Explore Agri-Hub",
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                    color = Color(0xFF1A202C)
                )
                Text(
                    text = "View All",
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                    color = Color(0xFF2E7D32)
                )
            }

            // 2 Hub Cards
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                HubCard(
                    icon = Icons.Default.Description,
                    iconBg = Color(0xFFE8F5E9),
                    iconTint = Color(0xFF2E7D32),
                    title = "Gov Schemes",
                    subtitle = "Subsidies & Grants",
                    modifier = Modifier.weight(1f)
                )
                HubCard(
                    icon = Icons.Default.Lightbulb,
                    iconBg = Color(0xFFF3E5F5),
                    iconTint = Color(0xFF7B1FA2),
                    title = "Techniques",
                    subtitle = "Modern Methods",
                    modifier = Modifier.weight(1f)
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Bottom Navigation Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFE8EFE6))
                .padding(horizontal = 24.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(Color(0xFFD3E3D1))
                    .padding(horizontal = 14.dp, vertical = 6.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    tint = Color(0xFF1B5E20),
                    modifier = Modifier.size(18.dp)
                )
            }
            Icon(imageVector = Icons.Default.Person, contentDescription = "Profile", tint = Color(0xFF2E7D32), modifier = Modifier.size(20.dp))
            Icon(imageVector = Icons.Default.WaterDrop, contentDescription = "Water", tint = Color(0xFF2E7D32), modifier = Modifier.size(20.dp))
            Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Account", tint = Color(0xFF2E7D32), modifier = Modifier.size(20.dp))
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// SCREEN 2: Weather & 6-Day Forecast
// ─────────────────────────────────────────────────────────────────────────────
@Composable
fun AgriConnectWeatherScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF19313F))
            .verticalScroll(rememberScrollState())
    ) {
        // Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF162B37))
                .padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu", tint = Color.White, modifier = Modifier.size(18.dp))
                Text(
                    text = "AGRI CONNECT",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, letterSpacing = 1.sp),
                    color = Color.White
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.LocationOn, contentDescription = null, tint = Color.White, modifier = Modifier.size(14.dp))
                Text(
                    text = " Noida, IN",
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                    color = Color.White
                )
            }

            Text(
                text = "Updated at: 14/08/2026 11:30 PM",
                style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp),
                color = Color(0xFF90A4AE),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Main Weather Hero Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
                    .background(Color(0xFF264757))
                    .padding(vertical = 16.dp, horizontal = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "27.08°C",
                        style = MaterialTheme.typography.displayMedium.copy(
                            fontWeight = FontWeight.Black,
                            fontSize = 38.sp
                        ),
                        color = Color.White
                    )
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(Color(0xFF375D70))
                            .padding(horizontal = 12.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = "Overcast clouds",
                            style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp, fontWeight = FontWeight.Medium),
                            color = Color.White
                        )
                    }
                    Text(
                        text = "Feels Like: 31.42°C",
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                        color = Color(0xFFB0BEC5)
                    )
                }
            }

            // Hourly Forecast
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Hourly Forecast",
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                    color = Color.White
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    HourlyCard("11:30", "27.08°C", "94", Modifier.weight(1f))
                    HourlyCard("02:30", "27.71°C", "85", Modifier.weight(1f))
                    HourlyCard("05:30", "27.53°C", "76", Modifier.weight(1f))
                    HourlyCard("08:30", "29.11°C", "60", Modifier.weight(1f))
                }
            }

            // 6-Day Extended Forecast
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "6-Day Extended Forecast",
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                    color = Color.White
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFF264757))
                        .padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ForecastRow("2026-08-14 21:00", "Clouds", "27.71°C")
                    HorizontalDivider(color = Color(0xFF33586C), thickness = 0.5.dp)
                    ForecastRow("2026-08-15 18:00", "Rain", "29.99°C")
                    HorizontalDivider(color = Color(0xFF33586C), thickness = 0.5.dp)
                    ForecastRow("2026-08-16 18:00", "Clouds", "32.11°C")
                    HorizontalDivider(color = Color(0xFF33586C), thickness = 0.5.dp)
                    ForecastRow("2026-08-17 18:00", "Rain", "28.31°C")
                    HorizontalDivider(color = Color(0xFF33586C), thickness = 0.5.dp)
                    ForecastRow("2026-08-18 18:00", "Clouds", "27.62°C")
                    HorizontalDivider(color = Color(0xFF33586C), thickness = 0.5.dp)
                    ForecastRow("2026-08-19 15:00", "Rain", "31.29°C")
                }
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// SCREEN 3: AgriAI Assistant (Gemini AI)
// ─────────────────────────────────────────────────────────────────────────────
@Composable
fun AgriConnectAiScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF1F5F0))
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFF1B5E20), Color(0xFF226E28))
                    )
                )
                .padding(horizontal = 14.dp, vertical = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu", tint = Color.White, modifier = Modifier.size(20.dp))
                
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.AutoAwesome,
                        contentDescription = "AI",
                        tint = Color(0xFF1B5E20),
                        modifier = Modifier.size(20.dp)
                    )
                }

                Column {
                    Text(
                        text = "AgriAI Assistant",
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                        color = Color.White
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(modifier = Modifier.size(5.dp).clip(CircleShape).background(Color(0xFF69F0AE)))
                        Text(
                            text = "Powered by Gemini • Online",
                            style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp),
                            color = Color.White.copy(alpha = 0.85f)
                        )
                    }
                }
            }
        }

        // Chat Container
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(12.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFFE8EFE6).copy(alpha = 0.6f))
                .border(1.dp, Color(0xFFD0DDD0), RoundedCornerShape(20.dp))
                .padding(16.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    text = "Hello, I am Your Chat bot.",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                    color = Color(0xFF2D3748)
                )
                Text(
                    text = "You can ask me any Thing",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF4A5568)
                )
            }
        }

        // Bottom Input Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF1F5F0))
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(50))
                    .background(Color.White)
                    .border(1.dp, Color(0xFFCBD5E1), RoundedCornerShape(50))
                    .padding(horizontal = 14.dp, vertical = 10.dp)
            ) {
                Text(
                    text = "Ask anything about farming...",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF94A3B8)
                )
            }

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF1B5E20)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Send,
                    contentDescription = "Send",
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Reusable Subcomponents
// ─────────────────────────────────────────────────────────────────────────────

@Composable
private fun TopDeviceStatusBar(time: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF1B5E20))
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
private fun QuickActionCard(
    icon: ImageVector,
    iconBg: Color,
    iconTint: Color,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(14.dp), spotColor = Color.Black.copy(alpha = 0.05f))
            .clip(RoundedCornerShape(14.dp))
            .background(Color.White)
            .padding(vertical = 12.dp, horizontal = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
                    .background(iconBg),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = icon, contentDescription = null, tint = iconTint, modifier = Modifier.size(18.dp))
            }
            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp, fontWeight = FontWeight.Bold),
                color = Color(0xFF1A202C),
                textAlign = TextAlign.Center
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.sp),
                color = Color(0xFF718096),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun HubCard(
    icon: ImageVector,
    iconBg: Color,
    iconTint: Color,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(14.dp), spotColor = Color.Black.copy(alpha = 0.05f))
            .clip(RoundedCornerShape(14.dp))
            .background(Color.White)
            .padding(12.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(iconBg),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = icon, contentDescription = null, tint = iconTint, modifier = Modifier.size(18.dp))
            }
            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp, fontWeight = FontWeight.Bold),
                color = Color(0xFF1A202C)
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp),
                color = Color(0xFF718096)
            )
        }
    }
}

@Composable
private fun HourlyCard(time: String, temp: String, hum: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF264757))
            .padding(vertical = 10.dp, horizontal = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(text = time, style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp), color = Color(0xFFB0BEC5))
            Icon(imageVector = Icons.Default.WbSunny, contentDescription = null, tint = Color(0xFFFFB300), modifier = Modifier.size(14.dp))
            Text(text = temp, style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp, fontWeight = FontWeight.Bold), color = Color.White)
            Text(text = "💧 $hum", style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.sp), color = Color(0xFF81D4FA))
        }
    }
}

@Composable
private fun ForecastRow(date: String, condition: String, temp: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = date, style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp), color = Color(0xFFCFD8DC))
        Text(text = condition, style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp, fontWeight = FontWeight.Medium), color = Color(0xFF81D4FA))
        Text(text = temp, style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp, fontWeight = FontWeight.Bold), color = Color.White)
    }
}
