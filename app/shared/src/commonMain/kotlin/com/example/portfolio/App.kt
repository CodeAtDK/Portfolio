package com.example.portfolio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.unit.dp
import com.example.portfolio.model.CaseStudy
import com.example.portfolio.model.CaseStudyRegistry
import com.example.portfolio.ui.components.AnimatedMeshBackground
import com.example.portfolio.ui.components.TopNavBar
import com.example.portfolio.ui.sections.*
import com.example.portfolio.ui.theme.BackgroundDark
import com.example.portfolio.ui.theme.LocalIsMobile
import com.example.portfolio.ui.theme.PortfolioTheme
import kotlinx.coroutines.launch

@Composable
fun App() {
    PortfolioTheme {
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            val isMobile = maxWidth < 840.dp
            CompositionLocalProvider(LocalIsMobile provides isMobile) {
                // Navigation state: null = home, non-null = case study screen
                var activeCaseStudy by remember { mutableStateOf<CaseStudy?>(null) }
                var isResumeFullScreen by remember { mutableStateOf(false) }

                if (isResumeFullScreen) {
                    ResumeFullScreen(onBack = { isResumeFullScreen = false })
                } else if (activeCaseStudy != null) {
                    CaseStudyScreen(
                        caseStudy = activeCaseStudy!!,
                        onBack = { activeCaseStudy = null }
                    )
                } else {
                    PortfolioHome(
                        onOpenCaseStudy = { id ->
                            CaseStudyRegistry[id]?.let { activeCaseStudy = it }
                        },
                        onOpenResumeFullScreen = { isResumeFullScreen = true }
                    )
                }
            }
        }
    }
}

@Composable
private fun PortfolioHome(
    onOpenCaseStudy: (String) -> Unit,
    onOpenResumeFullScreen: () -> Unit
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
            val isMobile = LocalIsMobile.current
            val sectionSpacing = if (isMobile) 44.dp else 80.dp
            val projectSpacing = if (isMobile) 52.dp else 100.dp

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 1100.dp)
                    .padding(horizontal = if (isMobile) 16.dp else 40.dp)
            ) {
                // Top Navigation Bar
                TopNavBar(
                    onNavClick = { scrollToSection(it) },
                    isDesktop = !isMobile
                )

                // Hero — extra top breathing room
                HomeSection(
                    modifier = Modifier.onGloballyPositioned {
                        sectionPositions["Home"] = it.positionInParent().y.toInt()
                    },
                    onExploreWork = { scrollToSection("Projects") },
                    onViewResume = onOpenResumeFullScreen,
                    onAskAI = { scrollToSection("AI") },
                    onContact = { scrollToSection("Contact") }
                )

                // Major sections with responsive spacing
                Spacer(modifier = Modifier.height(sectionSpacing))
                AboutSection(
                    modifier = Modifier.onGloballyPositioned {
                        sectionPositions["About"] = it.positionInParent().y.toInt()
                    }
                )

                Spacer(modifier = Modifier.height(sectionSpacing))
                SkillsSection(
                    modifier = Modifier.onGloballyPositioned {
                        sectionPositions["Skills"] = it.positionInParent().y.toInt()
                    }
                )

                Spacer(modifier = Modifier.height(projectSpacing))
                ProjectsSection(
                    modifier = Modifier.onGloballyPositioned {
                        sectionPositions["Projects"] = it.positionInParent().y.toInt()
                    },
                    onOpenCaseStudy = onOpenCaseStudy
                )

                Spacer(modifier = Modifier.height(sectionSpacing))
                ExperienceSection(
                    modifier = Modifier.onGloballyPositioned {
                        sectionPositions["Experience"] = it.positionInParent().y.toInt()
                    }
                )

                Spacer(modifier = Modifier.height(sectionSpacing))
                ResumeSection(
                    modifier = Modifier.onGloballyPositioned {
                        sectionPositions["Resume"] = it.positionInParent().y.toInt()
                    },
                    onOpenFullScreen = onOpenResumeFullScreen
                )

                Spacer(modifier = Modifier.height(sectionSpacing))
                AIWorkspaceSection(
                    modifier = Modifier.onGloballyPositioned {
                        sectionPositions["AI"] = it.positionInParent().y.toInt()
                    }
                )

                Spacer(modifier = Modifier.height(if (isMobile) 36.dp else 60.dp))
                ContactSection(
                    modifier = Modifier.onGloballyPositioned {
                        sectionPositions["Contact"] = it.positionInParent().y.toInt()
                    }
                )

                Spacer(modifier = Modifier.height(if (isMobile) 24.dp else 40.dp))
            }
        }
    }
}
