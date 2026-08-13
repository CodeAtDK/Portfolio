package com.example.portfolio

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.unit.dp
import com.example.portfolio.model.CaseStudy
import com.example.portfolio.model.CaseStudyRegistry
import com.example.portfolio.ui.components.AnimatedMeshBackground
import com.example.portfolio.ui.sections.*
import com.example.portfolio.ui.theme.BackgroundDark
import com.example.portfolio.ui.theme.PrimaryGreen
import com.example.portfolio.ui.theme.PortfolioTheme
import com.example.portfolio.ui.theme.TextMuted
import kotlinx.coroutines.launch

@Composable
fun App() {
    PortfolioTheme {
        // Navigation state: null = home, non-null = case study screen
        var activeCaseStudy by remember { mutableStateOf<CaseStudy?>(null) }

        if (activeCaseStudy != null) {
            CaseStudyScreen(
                caseStudy = activeCaseStudy!!,
                onBack = { activeCaseStudy = null }
            )
        } else {
            PortfolioHome(
                onOpenCaseStudy = { id ->
                    CaseStudyRegistry[id]?.let { activeCaseStudy = it }
                }
            )
        }
    }
}

@Composable
private fun PortfolioHome(
    onOpenCaseStudy: (String) -> Unit
) {
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    // Track section Y positions for scroll-to navigation
    val sectionPositions = remember { mutableStateMapOf<String, Int>() }

    fun scrollToSection(sectionKey: String) {
        sectionPositions[sectionKey]?.let { position ->
            coroutineScope.launch {
                scrollState.animateScrollTo(position)
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize().background(BackgroundDark)) {
        // Native Multiplatform Animated Floating Boxes & Mesh Background
        AnimatedMeshBackground(modifier = Modifier.fillMaxSize())

        // Single Page Scrollable Layout for Desktop/Web
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            // Wrapper limiting max width to exactly match CSS `.wrap` (1100px)
            Column(
                modifier = Modifier
                    .width(1100.dp)
                    .padding(horizontal = 40.dp)
            ) {
                // Top Navigation Bar
                TopNavBar(onNavClick = { scrollToSection(it) })

                // Hero — extra top breathing room
                HomeSection(
                    modifier = Modifier.onGloballyPositioned {
                        sectionPositions["Home"] = it.positionInParent().y.toInt()
                    }
                )

                // Major sections with varied spacing
                Spacer(modifier = Modifier.height(80.dp))
                AboutSection(
                    modifier = Modifier.onGloballyPositioned {
                        sectionPositions["About"] = it.positionInParent().y.toInt()
                    }
                )

                Spacer(modifier = Modifier.height(80.dp))
                SkillsSection(
                    modifier = Modifier.onGloballyPositioned {
                        sectionPositions["Skills"] = it.positionInParent().y.toInt()
                    }
                )

                Spacer(modifier = Modifier.height(100.dp))
                ProjectsSection(
                    modifier = Modifier.onGloballyPositioned {
                        sectionPositions["Projects"] = it.positionInParent().y.toInt()
                    },
                    onOpenCaseStudy = onOpenCaseStudy
                )

                Spacer(modifier = Modifier.height(80.dp))
                ExperienceSection(
                    modifier = Modifier.onGloballyPositioned {
                        sectionPositions["Experience"] = it.positionInParent().y.toInt()
                    }
                )

                Spacer(modifier = Modifier.height(80.dp))
                AIWorkspaceSection(
                    modifier = Modifier.onGloballyPositioned {
                        sectionPositions["AI"] = it.positionInParent().y.toInt()
                    }
                )

                Spacer(modifier = Modifier.height(60.dp))
                ContactSection(
                    modifier = Modifier.onGloballyPositioned {
                        sectionPositions["Contact"] = it.positionInParent().y.toInt()
                    }
                )

                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

@Composable
private fun TopNavBar(onNavClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 32.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "dhruva.dev",
            style = MaterialTheme.typography.titleLarge,
            color = PrimaryGreen
        )
        Row(horizontalArrangement = Arrangement.spacedBy(32.dp)) {
            val navItems = listOf("About", "Skills", "Projects", "Experience", "AI", "Contact")
            navItems.forEach { item ->
                NavItem(text = item, onClick = { onNavClick(item) })
            }
        }
    }
}

@Composable
private fun NavItem(text: String, onClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    val textColor by animateColorAsState(
        targetValue = if (isHovered) PrimaryGreen else TextMuted,
        animationSpec = tween(200),
        label = "nav_color"
    )

    Column(
        modifier = Modifier
            .hoverable(interactionSource)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = textColor
        )
        // Hover underline indicator
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            modifier = Modifier
                .width(if (isHovered) 20.dp else 0.dp)
                .height(2.dp)
                .background(PrimaryGreen)
        )
    }
}
