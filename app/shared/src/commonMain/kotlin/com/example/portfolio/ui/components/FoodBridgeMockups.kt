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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.portfolio.ui.theme.PrimaryGreen
import com.example.portfolio.ui.theme.SecondaryWarm
import com.example.portfolio.ui.theme.SurfaceDark

enum class FoodBridgeTab(val title: String, val subtitle: String) {
    HOME("Donor Hub", "Community impact & actions"),
    POST_DONATION("Post Donation", "Details & safety verification"),
    MARKETPLACE("Surplus Market", "Discounted rescue items"),
    PICKUPS("Volunteer Pickups", "Active routes & dispatch")
}

/**
 * Interactive Phone Showcase for FoodBridge displaying high-fidelity recreations
 * of the user's actual Android application screenshots.
 */
@Composable
fun FoodBridgeInteractiveShowcase(
    modifier: Modifier = Modifier,
    initialTab: FoodBridgeTab = FoodBridgeTab.HOME
) {
    var selectedTab by remember { mutableStateOf(initialTab) }
    val isMobile = com.example.portfolio.ui.theme.LocalIsMobile.current

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(if (isMobile) 14.dp else 20.dp)
    ) {
        // Tab Selector Pills (Horizontally scrollable for all 4 screens)
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .background(SurfaceDark)
                .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(50))
                .padding(4.dp)
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            FoodBridgeTab.entries.forEach { tab ->
                val isSelected = selectedTab == tab
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(if (isSelected) Color(0xFF00875A) else Color.Transparent)
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
                        color = if (isSelected) Color.White else Color(0xFF94A3B8)
                    )
                }
            }
        }

        // Phone Frame Container
        Box(
            modifier = Modifier
                .widthIn(max = if (isMobile) 320.dp else 340.dp)
                .fillMaxWidth()
                .shadow(elevation = 32.dp, shape = RoundedCornerShape(44.dp), spotColor = Color(0xFF00875A).copy(alpha = 0.25f))
                .clip(RoundedCornerShape(44.dp))
                .background(Color(0xFF0A130E))
                .border(3.5.dp, Color(0xFF1E3A2B), RoundedCornerShape(44.dp))
                .padding(6.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(38.dp))
                    .height(if (isMobile) 560.dp else 640.dp)
            ) {
                // Top Phone Bezel & Camera Punch-hole
                TopFoodBridgeStatusBar(
                    time = when (selectedTab) {
                        FoodBridgeTab.HOME -> "11:54"
                        FoodBridgeTab.POST_DONATION -> "11:55"
                        FoodBridgeTab.MARKETPLACE, FoodBridgeTab.PICKUPS -> "11:56"
                    }
                )

                // Screen Content with smooth crossfade
                Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
                    Crossfade(targetState = selectedTab, animationSpec = tween(300)) { tab ->
                        when (tab) {
                            FoodBridgeTab.HOME -> FoodBridgeDonorHomeScreen(
                                onOpenDonate = { selectedTab = FoodBridgeTab.POST_DONATION },
                                onOpenMarket = { selectedTab = FoodBridgeTab.MARKETPLACE },
                                onOpenPickups = { selectedTab = FoodBridgeTab.PICKUPS }
                            )
                            FoodBridgeTab.POST_DONATION -> FoodBridgePostDonationScreen(onBack = { selectedTab = FoodBridgeTab.HOME })
                            FoodBridgeTab.MARKETPLACE -> FoodBridgeMarketplaceScreen()
                            FoodBridgeTab.PICKUPS -> FoodBridgePickupsScreen(onBack = { selectedTab = FoodBridgeTab.HOME })
                        }
                    }
                }

                // Bottom Home Bar Indicator
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFE8F5E9).copy(alpha = 0.8f))
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
 * Compact Mini Phone Card Preview for "FoodBridge" in ProjectsSection.
 */
@Composable
fun FoodBridgeMiniPreviewCard(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .widthIn(max = 260.dp)
            .fillMaxWidth(0.85f)
            .clip(RoundedCornerShape(18.dp))
            .background(Color(0xFF0A130E))
            .border(1.5.dp, Color(0xFF00875A).copy(alpha = 0.5f), RoundedCornerShape(18.dp))
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF8FAF8))
                .padding(bottom = 8.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            // Green banner
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Brush.verticalGradient(listOf(Color(0xFF00796B), Color(0xFF00875A))))
                    .padding(horizontal = 8.dp, vertical = 6.dp)
            ) {
                Column {
                    Text("Food Bridge", style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp, fontWeight = FontWeight.Bold), color = Color.White)
                    Text("Bridge the Hunger Gap", style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp, fontWeight = FontWeight.Bold), color = Color.White)
                    Text("🌱 Zero Food Waste Initiative", style = MaterialTheme.typography.labelSmall.copy(fontSize = 7.sp), color = Color(0xFFA7F3D0))
                }
            }

            // Quick Stats Row
            Row(
                modifier = Modifier.padding(horizontal = 6.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                MiniFoodBridgeStat("50", "Donations", Color(0xFFE8F5E9), Color(0xFF00875A), Modifier.weight(1f))
                MiniFoodBridgeStat("250", "Meals Fed", Color(0xFFFEF3C7), Color(0xFFD97706), Modifier.weight(1f))
                MiniFoodBridgeStat("120kg", "CO2 Saved", Color(0xFFE0F2FE), Color(0xFF0284C7), Modifier.weight(1f))
            }

            // Action Pills
            Row(
                modifier = Modifier.padding(horizontal = 6.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Box(modifier = Modifier.weight(1f).clip(RoundedCornerShape(6.dp)).background(Color.White).padding(4.dp), contentAlignment = Alignment.Center) {
                    Text("+ Donate Food", style = MaterialTheme.typography.labelSmall.copy(fontSize = 7.sp, fontWeight = FontWeight.Bold), color = Color(0xFF00875A))
                }
                Box(modifier = Modifier.weight(1f).clip(RoundedCornerShape(6.dp)).background(Color.White).padding(4.dp), contentAlignment = Alignment.Center) {
                    Text("Volunteer Pickups", style = MaterialTheme.typography.labelSmall.copy(fontSize = 7.sp, fontWeight = FontWeight.Bold), color = Color(0xFF0284C7))
                }
            }
        }
    }
}

@Composable
private fun MiniFoodBridgeStat(value: String, label: String, bg: Color, valueColor: Color, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .background(bg)
            .padding(vertical = 4.dp, horizontal = 2.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = value, style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp, fontWeight = FontWeight.Bold), color = valueColor)
            Text(text = label, style = MaterialTheme.typography.labelSmall.copy(fontSize = 6.sp), color = Color(0xFF64748B))
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// SCREEN 1: Food Bridge Donor Dashboard
// ─────────────────────────────────────────────────────────────────────────────
@Composable
fun FoodBridgeDonorHomeScreen(
    onOpenDonate: () -> Unit = {},
    onOpenMarket: () -> Unit = {},
    onOpenPickups: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9FAF9))
            .verticalScroll(rememberScrollState())
    ) {
        // App Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 14.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFE8F5E9)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(imageVector = Icons.Default.VolunteerActivism, contentDescription = null, tint = Color(0xFF00875A), modifier = Modifier.size(18.dp))
                }
                Column {
                    Text("Food Bridge", style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold), color = Color(0xFF0F172A))
                    Text("Community Food Network", style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.sp), color = Color(0xFF64748B))
                }
            }

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.Security, contentDescription = "Security", tint = Color(0xFF475569), modifier = Modifier.size(18.dp))
                Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Cart", tint = Color(0xFF475569), modifier = Modifier.size(18.dp))
            }
        }

        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Donor Greeting
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    Text("Hello, Donor Dhruva", style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold), color = Color(0xFF0F172A))
                    Text("Ready to make a difference today?", style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp), color = Color(0xFF64748B))
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(Color(0xFFFEF3C7))
                        .border(0.5.dp, Color(0xFFF59E0B).copy(alpha = 0.4f), RoundedCornerShape(50))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(3.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Bronze Tier",
                            tint = Color(0xFFB45309),
                            modifier = Modifier.size(9.dp)
                        )
                        Text("Bronze Tier", style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.sp, fontWeight = FontWeight.Bold), color = Color(0xFFB45309))
                    }
                }
            }

            // Hero Banner Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xFF00875A))
                    .padding(16.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(Color.White.copy(alpha = 0.15f))
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text("🌱 ZERO FOOD WASTE INITIATIVE", style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.sp, fontWeight = FontWeight.Bold), color = Color.White)
                    }

                    Text("Bridge the Hunger Gap", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), color = Color.White)

                    Text(
                        text = "Your surplus edible food can nourish families in your local community.",
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp),
                        color = Color.White.copy(alpha = 0.9f)
                    )

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(Color.White)
                            .clickable { onOpenDonate() }
                            .padding(horizontal = 14.dp, vertical = 8.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(imageVector = Icons.Default.AddCircle, contentDescription = null, tint = Color(0xFF00875A), modifier = Modifier.size(14.dp))
                            Text("Donate Food Now", style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp, fontWeight = FontWeight.Bold), color = Color(0xFF00875A))
                        }
                    }
                }
            }

            // Your Community Impact Section
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Your Community Impact", style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold), color = Color(0xFF0F172A))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ImpactStatCard(icon = Icons.Default.VolunteerActivism, iconBg = Color(0xFFE8F5E9), iconTint = Color(0xFF00875A), value = "50", label = "Donations", modifier = Modifier.weight(1f))
                    ImpactStatCard(icon = Icons.Default.Restaurant, iconBg = Color(0xFFFEF3C7), iconTint = Color(0xFFD97706), value = "250", label = "Meals Fed", modifier = Modifier.weight(1f))
                    ImpactStatCard(icon = Icons.Default.Eco, iconBg = Color(0xFFE0F2FE), iconTint = Color(0xFF0284C7), value = "120kg", label = "CO2 Saved", modifier = Modifier.weight(1f))
                }
            }

            // Quick Actions Section
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Quick Actions", style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold), color = Color(0xFF0F172A))

                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        QuickActionGridCard(
                            icon = Icons.Default.AddCircle,
                            iconBg = Color(0xFFE8F5E9),
                            iconTint = Color(0xFF00875A),
                            title = "Donate Food",
                            subtitle = "Share surplus meals",
                            onClick = onOpenDonate,
                            modifier = Modifier.weight(1f)
                        )
                        QuickActionGridCard(
                            icon = Icons.Default.Storefront,
                            iconBg = Color(0xFFFEF3C7),
                            iconTint = Color(0xFFD97706),
                            title = "Marketplace",
                            subtitle = "Discounted surplus",
                            onClick = onOpenMarket,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        QuickActionGridCard(
                            icon = Icons.Default.LocalShipping,
                            iconBg = Color(0xFFE0F2FE),
                            iconTint = Color(0xFF0284C7),
                            title = "Pickups",
                            subtitle = "Active volunteer routes",
                            onClick = onOpenPickups,
                            modifier = Modifier.weight(1f)
                        )
                        QuickActionGridCard(
                            icon = Icons.Default.VerifiedUser,
                            iconBg = Color(0xFFFFEDD5),
                            iconTint = Color(0xFFEA580C),
                            title = "FSSAI Verify",
                            subtitle = "License validation",
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Bottom Navigation Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFE8F5E9).copy(alpha = 0.5f))
                .padding(horizontal = 20.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(Color(0xFFD1FAE5))
                    .padding(horizontal = 14.dp, vertical = 4.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(imageVector = Icons.Default.Home, contentDescription = "Home", tint = Color(0xFF00875A), modifier = Modifier.size(16.dp))
                    Text("Home", style = MaterialTheme.typography.labelSmall.copy(fontSize = 7.sp, fontWeight = FontWeight.Bold), color = Color(0xFF00875A))
                }
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.clickable { onOpenMarket() }) {
                Icon(imageVector = Icons.Default.Storefront, contentDescription = "Market", tint = Color(0xFF64748B), modifier = Modifier.size(16.dp))
                Text("Market", style = MaterialTheme.typography.labelSmall.copy(fontSize = 7.sp), color = Color(0xFF64748B))
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(imageVector = Icons.Default.List, contentDescription = "Donations", tint = Color(0xFF64748B), modifier = Modifier.size(16.dp))
                Text("Donations", style = MaterialTheme.typography.labelSmall.copy(fontSize = 7.sp), color = Color(0xFF64748B))
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(imageVector = Icons.Default.Person, contentDescription = "Profile", tint = Color(0xFF64748B), modifier = Modifier.size(16.dp))
                Text("Profile", style = MaterialTheme.typography.labelSmall.copy(fontSize = 7.sp), color = Color(0xFF64748B))
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// SCREEN 2: Post Food Donation Screen
// ─────────────────────────────────────────────────────────────────────────────
@Composable
fun FoodBridgePostDonationScreen(onBack: () -> Unit = {}) {
    var isPackaged by remember { mutableStateOf(true) }
    var selectedCategory by remember { mutableStateOf("Prepared Meals") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9FAF9))
            .verticalScroll(rememberScrollState())
    ) {
        // App Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color(0xFF1E293B),
                modifier = Modifier.size(20.dp).clickable { onBack() }
            )
            Column {
                Text("Post Food Donation", style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold), color = Color(0xFF0F172A))
                Text("Help feed those in need", style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.sp), color = Color(0xFF64748B))
            }
        }

        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Food Details Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
                    .background(Color.White)
                    .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(18.dp))
                    .padding(14.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFE8F5E9)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(imageVector = Icons.Default.Restaurant, contentDescription = null, tint = Color(0xFF00875A), modifier = Modifier.size(16.dp))
                        }
                        Text("Food Details", style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold), color = Color(0xFF0F172A))
                    }

                    Text("Category", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold), color = Color(0xFF475569))

                    // Categories scroll
                    Row(
                        modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        listOf("Prepared Meals", "Raw Produce", "Bakery Goods", "Packaged").forEach { cat ->
                            val isSel = selectedCategory == cat
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(50))
                                    .background(if (isSel) Color(0xFF00875A) else Color(0xFFF1F5F9))
                                    .clickable { selectedCategory = cat }
                                    .padding(horizontal = 10.dp, vertical = 6.dp)
                            ) {
                                Text(
                                    text = cat,
                                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp, fontWeight = if (isSel) FontWeight.Bold else FontWeight.Medium),
                                    color = if (isSel) Color.White else Color(0xFF475569)
                                )
                            }
                        }
                    }

                    // Input
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(10.dp))
                            .padding(12.dp)
                    ) {
                        Text("Food Description & Quantity", style = MaterialTheme.typography.bodySmall, color = Color(0xFF94A3B8))
                    }
                }
            }

            // Packaged & Sealed Switch Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
                    .background(Color.White)
                    .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(18.dp))
                    .padding(14.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFFEF3C7)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(imageVector = Icons.Default.Inventory2, contentDescription = null, tint = Color(0xFFD97706), modifier = Modifier.size(16.dp))
                        }
                        Column {
                            Text("Is Food Packaged & Sealed?", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold), color = Color(0xFF0F172A))
                            Text("Ready for immediate safe transport", style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.sp), color = Color(0xFF64748B))
                        }
                    }

                    // Toggle switch
                    Box(
                        modifier = Modifier
                            .width(40.dp)
                            .height(22.dp)
                            .clip(RoundedCornerShape(50))
                            .background(if (isPackaged) Color(0xFF00875A) else Color(0xFFCBD5E1))
                            .clickable { isPackaged = !isPackaged }
                            .padding(2.dp),
                        contentAlignment = if (isPackaged) Alignment.CenterEnd else Alignment.CenterStart
                    ) {
                        Box(
                            modifier = Modifier
                                .size(18.dp)
                                .clip(CircleShape)
                                .background(Color.White)
                        )
                    }
                }
            }

            // Donor Information Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
                    .background(Color.White)
                    .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(18.dp))
                    .padding(14.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFE0F2FE)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(imageVector = Icons.Default.Person, contentDescription = null, tint = Color(0xFF0284C7), modifier = Modifier.size(16.dp))
                        }
                        Text("Donor Information", style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold), color = Color(0xFF0F172A))
                    }

                    // Inputs
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(10.dp))
                            .padding(10.dp)
                    ) {
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.Apartment, contentDescription = null, tint = Color(0xFF00875A), modifier = Modifier.size(16.dp))
                            Text("Donor / Restaurant Name", style = MaterialTheme.typography.bodySmall, color = Color(0xFF64748B))
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(10.dp))
                            .padding(10.dp)
                    ) {
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.Call, contentDescription = null, tint = Color(0xFF00875A), modifier = Modifier.size(16.dp))
                            Text("Contact Phone Number", style = MaterialTheme.typography.bodySmall, color = Color(0xFF64748B))
                        }
                    }
                }
            }

            // Submit Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color(0xFF00875A))
                    .clickable { onBack() }
                    .padding(vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Default.VolunteerActivism, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
                    Text("Submit Food Donation", style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold), color = Color.White)
                }
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// SCREEN 3: Surplus Marketplace Screen
// ─────────────────────────────────────────────────────────────────────────────
@Composable
fun FoodBridgeMarketplaceScreen() {
    var selectedCat by remember { mutableStateOf("All") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9FAF9))
            .verticalScroll(rememberScrollState())
    ) {
        // App Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 14.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("Surplus Marketplace", style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold), color = Color(0xFF0F172A))
                Text("Affordable quality food, reduced waste", style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.sp), color = Color(0xFF64748B))
            }
            Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Cart", tint = Color(0xFF0F172A), modifier = Modifier.size(18.dp))
        }

        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Search Input
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White)
                    .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(12.dp))
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null, tint = Color(0xFF00875A), modifier = Modifier.size(16.dp))
                    Text("Search products, meals, groceries...", style = MaterialTheme.typography.bodySmall, color = Color(0xFF94A3B8))
                }
            }

            // Categories
            Row(
                modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                listOf("All", "Bakery", "Produce", "Pantry", "Prepared Meals").forEach { cat ->
                    val isSel = selectedCat == cat
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(if (isSel) Color(0xFF00875A) else Color.White)
                            .border(0.5.dp, if (isSel) Color.Transparent else Color(0xFFE2E8F0), RoundedCornerShape(50))
                            .clickable { selectedCat = cat }
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = cat,
                            style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp, fontWeight = if (isSel) FontWeight.Bold else FontWeight.Medium),
                            color = if (isSel) Color.White else Color(0xFF475569)
                        )
                    }
                }
            }

            // Available Items Section
            Text("Available Items (3)", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold), color = Color(0xFF475569))

            // 3 Market Items
            MarketplaceItemCard(
                tag = "Rescue Deal",
                title = "Vintage Leather Backpack",
                description = "A stylish and durable vintage leather backpack.",
                price = "$79.99",
                originalPrice = "$127.98"
            )
            MarketplaceItemCard(
                tag = "Rescue Deal",
                title = "Wireless Bluetooth Headphones",
                description = "High-fidelity wireless Bluetooth headphones.",
                price = "$129.50",
                originalPrice = "$207.20"
            )
            MarketplaceItemCard(
                tag = "Rescue Deal",
                title = "Organic Green Tea",
                description = "A refreshing and healthy pack of organic green tea leaves.",
                price = "$12.00",
                originalPrice = "$19.20"
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// SCREEN 4: Volunteer Pickups Screen
// ─────────────────────────────────────────────────────────────────────────────
@Composable
fun FoodBridgePickupsScreen(onBack: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9FAF9))
            .verticalScroll(rememberScrollState())
    ) {
        // App Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color(0xFF1E293B),
                modifier = Modifier.size(20.dp).clickable { onBack() }
            )
            Column {
                Text("Volunteer Pickups", style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold), color = Color(0xFF0F172A))
                Text("6 active pickup locations", style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.sp), color = Color(0xFF64748B))
            }
        }

        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            PickupCard("Dhruva", "Ready", null)
            PickupCard("Khatavkar", "Ready", "Pav Bahji")
            PickupCard("Dhruva", "Ready", "Khari")
            PickupCard("Rakshit", "Ready", "Paratha")
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Reusable Subcomponents
// ─────────────────────────────────────────────────────────────────────────────

@Composable
private fun TopFoodBridgeStatusBar(time: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF00875A))
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
private fun ImpactStatCard(
    icon: ImageVector,
    iconBg: Color,
    iconTint: Color,
    value: String,
    label: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(16.dp))
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
            Text(text = value, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), color = Color(0xFF0F172A))
            Text(text = label, style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.sp), color = Color(0xFF64748B))
        }
    }
}

@Composable
private fun QuickActionGridCard(
    icon: ImageVector,
    iconBg: Color,
    iconTint: Color,
    title: String,
    subtitle: String,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(12.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(iconBg),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = icon, contentDescription = null, tint = iconTint, modifier = Modifier.size(18.dp))
            }
            Text(text = title, style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp, fontWeight = FontWeight.Bold), color = Color(0xFF0F172A))
            Text(text = subtitle, style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.sp), color = Color(0xFF64748B))
        }
    }
}

@Composable
private fun MarketplaceItemCard(
    tag: String,
    title: String,
    description: String,
    price: String,
    originalPrice: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(Color.White)
            .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(18.dp))
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Thumbnail / Icon
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFF1F5F9)),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = Icons.Default.Fastfood, contentDescription = null, tint = Color(0xFF00875A), modifier = Modifier.size(28.dp))
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color(0xFFFEF3C7))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(text = tag, style = MaterialTheme.typography.labelSmall.copy(fontSize = 7.sp, fontWeight = FontWeight.Bold), color = Color(0xFFD97706))
                }
                Text(text = title, style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp, fontWeight = FontWeight.Bold), color = Color(0xFF0F172A))
                Text(text = description, style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.sp), color = Color(0xFF64748B), maxLines = 1)
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text(text = price, style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp, fontWeight = FontWeight.Bold), color = Color(0xFF00875A))
                    Text(text = originalPrice, style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.sp, textDecoration = TextDecoration.LineThrough), color = Color(0xFF94A3B8))
                }
            }

            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE8F5E9)),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, tint = Color(0xFF00875A), modifier = Modifier.size(14.dp))
            }
        }
    }
}

@Composable
private fun PickupCard(
    driver: String,
    status: String,
    foodDetails: String?
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(Color.White)
            .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(18.dp))
            .padding(12.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFE0F2FE)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(imageVector = Icons.Default.LocalShipping, contentDescription = null, tint = Color(0xFF0284C7), modifier = Modifier.size(16.dp))
                    }
                    Column {
                        Text(text = driver, style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp, fontWeight = FontWeight.Bold), color = Color(0xFF0F172A))
                        Text(text = "Pickup Route", style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.sp), color = Color(0xFF64748B))
                    }
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(Color(0xFFDCFCE7))
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(text = status, style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.sp, fontWeight = FontWeight.Bold), color = Color(0xFF16A34A))
                }
            }

            // Food details box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFF8FAFC))
                    .padding(8.dp)
            ) {
                Column {
                    Text(text = "Food Details", style = MaterialTheme.typography.labelSmall.copy(fontSize = 7.sp, fontWeight = FontWeight.Bold), color = Color(0xFF64748B))
                    if (foodDetails != null) {
                        Text(text = foodDetails, style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp, fontWeight = FontWeight.SemiBold), color = Color(0xFF0F172A))
                    }
                }
            }

            // Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFF00875A))
                        .padding(vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.Navigation, contentDescription = null, tint = Color.White, modifier = Modifier.size(12.dp))
                        Text("Navigate", style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp, fontWeight = FontWeight.Bold), color = Color.White)
                    }
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White)
                        .border(1.dp, Color(0xFFD97706), RoundedCornerShape(10.dp))
                        .padding(vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null, tint = Color(0xFFD97706), modifier = Modifier.size(12.dp))
                        Text("Picked Up", style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp, fontWeight = FontWeight.Bold), color = Color(0xFFD97706))
                    }
                }
            }
        }
    }
}
