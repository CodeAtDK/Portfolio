package com.example.portfolio.ui.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.portfolio.ui.components.PortfolioCard
import com.example.portfolio.ui.components.SectionHeader
import com.example.portfolio.ui.components.TechChip
import com.example.portfolio.ui.components.rememberPulseAlpha
import com.example.portfolio.ui.components.rememberTypingDots
import com.example.portfolio.ui.theme.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private data class ChatMessage(
    val isUser: Boolean,
    val text: String,
    val insightTitle: String? = null,
    val tags: List<String> = emptyList()
)

@Composable
fun AIWorkspaceSection(modifier: Modifier = Modifier) {
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    var inputQuery by remember { mutableStateOf("") }
    var isThinking by remember { mutableStateOf(false) }

    val messages = remember {
        mutableStateListOf(
            ChatMessage(
                isUser = false,
                text = "Hi! I'm Dhruva AI. I'm trained on Dhruva's codebase, projects, and resume. How can I help you?",
                insightTitle = "Suggested questions:",
                tags = listOf(
                    "What is your tech stack?",
                    "Tell me about FoodBridge.",
                    "Why KMP over React Native?"
                )
            )
        )
    }

    fun submitQuery(queryText: String) {
        if (queryText.isBlank() || isThinking) return
        val cleanQuery = queryText.trim()
        inputQuery = ""

        messages.add(ChatMessage(isUser = true, text = cleanQuery))

        coroutineScope.launch {
            listState.animateScrollToItem(messages.size - 1)
            isThinking = true
            delay(1000)
            isThinking = false

            val q = cleanQuery.lowercase()
            val response = when {
                q.contains("stack") || q.contains("skills") || q.contains("technologies") -> ChatMessage(
                    isUser = false,
                    text = "My primary engineering domain resides within Kotlin, Jetpack Compose, and Kotlin Multiplatform (KMP). On the backend, I leverage Ktor and Firebase.",
                    insightTitle = "Key Capabilities:",
                    tags = listOf("Kotlin", "Jetpack Compose", "KMP", "Firebase", "Coroutines")
                )
                q.contains("foodbridge") -> ChatMessage(
                    isUser = false,
                    text = "FoodBridge is a platform designed to connect surplus food donors with active volunteer networks. Built on Clean Architecture with MVVM.",
                    insightTitle = "Tech:",
                    tags = listOf("Clean Architecture", "MVVM", "Compose", "Firebase")
                )
                q.contains("kmp") || q.contains("multiplatform") || q.contains("react") || q.contains("flutter") -> ChatMessage(
                    isUser = false,
                    text = "Kotlin Multiplatform allows native performance and direct API access across Android, iOS, and the Web (WASM). It shares business logic while compiling to native composables.",
                    insightTitle = "KMP Advantages:",
                    tags = listOf("Native Performance", "WASM", "Shared Domain Layer")
                )
                else -> ChatMessage(
                    isUser = false,
                    text = "I am a local placeholder AI for this native KMP version. Real Gemini API integration is coming soon! Try asking about 'skills', 'KMP', or 'FoodBridge'.",
                    insightTitle = "Try:",
                    tags = listOf("What is your tech stack?", "Tell me about FoodBridge.")
                )
            }

            messages.add(response)
            listState.animateScrollToItem(messages.size - 1)
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        SectionHeader(number = "05", title = "Dhruva AI")

        // Chat Container (Terminal Styled)
        PortfolioCard(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 400.dp, max = 540.dp),
            contentPadding = 0.dp // Padding handled internally
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                // Terminal Header with status indicator
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(SurfaceContainerHighest)
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        Box(modifier = Modifier.size(10.dp).clip(CircleShape).background(Color(0xFFFF5F56)))
                        Box(modifier = Modifier.size(10.dp).clip(CircleShape).background(Color(0xFFFFBD2E)))
                        Box(modifier = Modifier.size(10.dp).clip(CircleShape).background(Color(0xFF27C93F)))
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Pulsing status dot
                        val statusAlpha = rememberPulseAlpha(minAlpha = 0.3f, maxAlpha = 1f, durationMs = 2000)
                        Box(
                            modifier = Modifier
                                .size(6.dp)
                                .clip(CircleShape)
                                .background(PrimaryGreen.copy(alpha = statusAlpha))
                        )
                        Text(
                            text = "dhruva-ai-terminal",
                            style = MaterialTheme.typography.labelSmall,
                            color = TextMuted
                        )
                    }
                }

                HorizontalDivider(color = BorderDark, thickness = 1.dp)

                // Messages List
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(bottom = 16.dp)
                ) {
                    items(messages) { message ->
                        ChatMessageBubble(message = message, onTagClick = { submitQuery(it) })
                    }

                    if (isThinking) {
                        item {
                            ThinkingBubble()
                        }
                    }
                }

                HorizontalDivider(color = BorderDark, thickness = 1.dp)

                // Input Bar
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(SurfaceContainerHighest)
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    TextField(
                        value = inputQuery,
                        onValueChange = { inputQuery = it },
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(SurfaceDark)
                            .border(1.dp, GlassBorder, RoundedCornerShape(12.dp)),
                        placeholder = { Text("Ask about my skills or projects...", color = TextMuted) },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedTextColor = MaterialTheme.colorScheme.onSurface,
                            unfocusedTextColor = MaterialTheme.colorScheme.onSurface
                        ),
                        singleLine = true
                    )

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(48.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(PrimaryGreen)
                            .clickable { submitQuery(inputQuery) }
                    ) {
                        Text(
                            text = "->",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = BackgroundDark
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ChatMessageBubble(
    message: ChatMessage,
    onTagClick: (String) -> Unit
) {
    val align = if (message.isUser) Alignment.End else Alignment.Start
    val bgColor = if (message.isUser) PrimaryGreen.copy(alpha = 0.08f) else SurfaceContainerHigh

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = align
    ) {
        Row(
            modifier = Modifier
                .widthIn(max = 640.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(bgColor)
        ) {
            // Left accent border for user messages
            if (message.isUser) {
                Box(
                    modifier = Modifier
                        .width(3.dp)
                        .fillMaxHeight()
                        .background(PrimaryGreen.copy(alpha = 0.5f))
                )
            }

            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = if (message.isUser) "> user_query" else "> dhruva_ai_response",
                    style = MaterialTheme.typography.labelSmall,
                    color = if (message.isUser) PrimaryGreen else TertiaryBlue
                )

                Text(
                    text = message.text,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                if (message.insightTitle != null) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(
                            text = message.insightTitle,
                            style = MaterialTheme.typography.labelSmall,
                            color = TextMuted
                        )

                        @OptIn(ExperimentalLayoutApi::class)
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            message.tags.forEach { tag ->
                                TechChip(
                                    text = tag,
                                    textColor = PrimaryGreen,
                                    modifier = Modifier.clickable { onTagClick(tag) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ThinkingBubble() {
    val dots = rememberTypingDots()

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(SurfaceContainerHigh)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "> generating_response$dots",
            style = MaterialTheme.typography.labelSmall,
            color = TextMuted
        )
    }
}
