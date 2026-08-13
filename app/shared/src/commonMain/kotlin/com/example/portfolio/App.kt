package com.example.portfolio

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.portfolio.model.NavSection
import com.example.portfolio.ui.components.AnimatedMeshBackground
import com.example.portfolio.ui.components.DesktopSideNav
import com.example.portfolio.ui.components.MobileBottomNav
import com.example.portfolio.ui.sections.*
import com.example.portfolio.ui.theme.PortfolioTheme

@Composable
fun App() {
    PortfolioTheme {
        var currentSection by remember { mutableStateOf(NavSection.HOME) }

        Box(modifier = Modifier.fillMaxSize().background(Color(0xFF0A0A0A))) {
            // Native Multiplatform Animated Floating Boxes & Mesh Background
            AnimatedMeshBackground(modifier = Modifier.fillMaxSize())

            // Responsive Layout Switcher
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize()
                    .safeContentPadding()
            ) {
                val isDesktop = maxWidth >= 768.dp

                if (isDesktop) {
                    Row(modifier = Modifier.fillMaxSize()) {
                        DesktopSideNav(
                            currentSection = currentSection,
                            onSectionSelected = { currentSection = it }
                        )

                        Box(
                            contentAlignment = Alignment.TopCenter,
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .verticalScroll(rememberScrollState())
                                .padding(horizontal = 48.dp, vertical = 32.dp)
                        ) {
                            SectionContent(
                                section = currentSection,
                                onNavigate = { currentSection = it },
                                modifier = Modifier.widthIn(max = 940.dp).fillMaxWidth()
                            )
                        }
                    }
                } else {
                    Scaffold(
                        containerColor = Color.Transparent,
                        bottomBar = {
                            MobileBottomNav(
                                currentSection = currentSection,
                                onSectionSelected = { currentSection = it }
                            )
                        }
                    ) { innerPadding ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding)
                                .verticalScroll(rememberScrollState())
                                .padding(horizontal = 20.dp, vertical = 16.dp)
                        ) {
                            SectionContent(
                                section = currentSection,
                                onNavigate = { currentSection = it }
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun SectionContent(
    section: NavSection,
    onNavigate: (NavSection) -> Unit,
    modifier: Modifier = Modifier
) {
    // Smooth sliding entrance and fade transitions inspired by modern animated portfolios
    AnimatedContent(
        targetState = section,
        transitionSpec = {
            (fadeIn(animationSpec = tween(450)) + slideInVertically(
                animationSpec = tween(450),
                initialOffsetY = { fullHeight -> fullHeight / 14 }
            )).togetherWith(
                fadeOut(animationSpec = tween(250)) + slideOutVertically(
                    animationSpec = tween(250),
                    targetOffsetY = { fullHeight -> -fullHeight / 20 }
                )
            )
        },
        modifier = modifier
    ) { target ->
        when (target) {
            NavSection.HOME -> HomeSection(onNavigate = onNavigate)
            NavSection.ABOUT -> AboutSection()
            NavSection.SKILLS -> SkillsSection()
            NavSection.PROJECTS -> ProjectsSection()
            NavSection.EXPERIENCE -> ExperienceSection()
            NavSection.AI_WORKSPACE -> AIWorkspaceSection()
            NavSection.CONTACT -> ContactSection()
        }
    }
}
