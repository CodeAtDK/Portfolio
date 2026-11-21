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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.portfolio.data.remote.GeminiResult
import com.example.portfolio.data.remote.GeminiService
import com.example.portfolio.ui.components.PortfolioCard
import com.example.portfolio.ui.components.SectionHeader
import com.example.portfolio.ui.components.TechChip
import com.example.portfolio.ui.components.rememberPulseAlpha
import com.example.portfolio.ui.components.rememberTypingDots
import com.example.portfolio.ui.theme.*
import kotlinx.coroutines.launch

private data class ChatMessage(
    val isUser: Boolean,
    val text: String,
    val latency: String? = null,
    val modelVersion: String? = null,
    val tokenCount: String? = null,
    val isLive: Boolean = true,
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
                text = "Hi! I'm Dhruva AI, powered by Google Gemini. I'm connected to Dhruva's multiplatform codebase, project architecture, and career profile. Ask me anything!",
                modelVersion = "gemini-2.0-flash",
                tokenCount = "live",
                latency = "online",
                isLive = true,
                insightTitle = "Suggested questions:",
                tags = listOf(
                    "Tell me about Inkspace Notebook",
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

            val result: GeminiResult = try {
                GeminiService.generateResponse(cleanQuery)
            } catch (e: Exception) {
                GeminiResult(
                    reply = "I'm Dhruva's AI assistant. I can answer anything about his **Multiplatform & Android development**, **featured projects** (Inkspace Notebook, FoodBridge, Agri Connect, CareNest), or **technical skills**.",
                    tokenCount = "offline",
                    modelVersion = "offline-fallback",
                    latency = "0.1s",
                    isLive = false
                )
            }

            isThinking = false

            messages.add(
                ChatMessage(
                    isUser = false,
                    text = result.reply,
                    latency = result.latency,
                    modelVersion = result.modelVersion,
                    tokenCount = result.tokenCount,
                    isLive = result.isLive
                )
            )
            listState.animateScrollToItem(messages.size - 1)
        }
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        SectionHeader(number = "05", title = "Dhruva AI")

        // Chat Container (Terminal Styled)
        PortfolioCard(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 420.dp, max = 580.dp),
            contentPadding = 0.dp // Padding handled internally
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                // Terminal Header with status indicator and API Key configuration button
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(SurfaceContainerHighest)
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            Box(modifier = Modifier.size(10.dp).clip(CircleShape).background(Color(0xFFFF5F56)))
                            Box(modifier = Modifier.size(10.dp).clip(CircleShape).background(Color(0xFFFFBD2E)))
                            Box(modifier = Modifier.size(10.dp).clip(CircleShape).background(Color(0xFF27C93F)))
                        }
                        Text(
                            text = "dhruva-ai.sh // gemini-2.0-flash",
                            style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                            color = TextMuted
                        )
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
                            text = "gemini-2.0-flash • live",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontSize = 11.sp,
                                fontFamily = FontFamily.Monospace
                            ),
                            color = PrimaryGreen
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
                        placeholder = { Text("Ask about Inkspace, KMP, or Android skills...", color = TextMuted) },
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
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Send,
                            contentDescription = "Send",
                            tint = BackgroundDark,
                            modifier = Modifier.size(18.dp)
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
                .widthIn(max = 680.dp)
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
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Header line with model info & telemetry
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (message.isUser) "> user_query" else "> dhruva_ai_response",
                        style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
                        color = if (message.isUser) PrimaryGreen else TertiaryBlue
                    )

                    if (!message.isUser && message.latency != null) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (message.modelVersion != null) {
                                Text(
                                    text = message.modelVersion,
                                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp, fontFamily = FontFamily.Monospace),
                                    color = TextDim
                                )
                            }
                            if (!message.tokenCount.isNullOrBlank()) {
                                Text(
                                    text = "•",
                                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                                    color = TextDim
                                )
                                Text(
                                    text = message.tokenCount,
                                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp, color = TertiaryBlue, fontFamily = FontFamily.Monospace)
                                )
                            }
                            Text(
                                text = "•",
                                style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                                color = TextDim
                            )
                            Text(
                                text = message.latency,
                                style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp, color = PrimaryGreen, fontFamily = FontFamily.Monospace)
                            )
                        }
                    }
                }

                Text(
                    text = message.text,
                    style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 22.sp),
                    color = MaterialTheme.colorScheme.onSurface
                )

                if (message.insightTitle != null) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(top = 4.dp)) {
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
        Icon(
            imageVector = Icons.Filled.AutoAwesome,
            contentDescription = "Thinking",
            tint = PrimaryGreen,
            modifier = Modifier.size(14.dp)
        )
        Text(
            text = "Gemini is thinking$dots",
            style = MaterialTheme.typography.labelSmall.copy(fontFamily = FontFamily.Monospace),
            color = TextMuted
        )
    }
}
