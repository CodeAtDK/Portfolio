package com.example.portfolio.ui.sections

import androidx.compose.foundation.background
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
import com.example.portfolio.ui.components.GlassCard
import com.example.portfolio.ui.components.TechChip
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
                text = "Hi! I'm Dhruva AI, trained directly on Dhruva Khatavkar's codebase skills, project case studies, and engineering philosophy. How can I assist you today?",
                insightTitle = "Quick Suggested Inquiries:",
                tags = listOf(
                    "What is your tech stack?",
                    "Explain AI integration experience.",
                    "Tell me about FoodBridge architecture.",
                    "Why Kotlin Multiplatform over React Native?"
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
            delay(1100)
            isThinking = false

            val q = cleanQuery.lowercase()
            val response = when {
                q.contains("stack") || q.contains("skills") || q.contains("technologies") -> ChatMessage(
                    isUser = false,
                    text = "My primary engineering domain resides within Kotlin, Jetpack Compose, and Kotlin Multiplatform (KMP). On the backend, I leverage Ktor and Supabase for real-time synchronization, supplemented by Firebase Auth and custom RESTful endpoints.",
                    insightTitle = "Key Capabilities:",
                    tags = listOf("Kotlin", "Jetpack Compose", "KMP", "Ktor", "Supabase", "Coroutines")
                )
                q.contains("ai") || q.contains("ml") || q.contains("model") -> ChatMessage(
                    isUser = false,
                    text = "I integrate edge AI capabilities directly into Android environments using ONNX Runtime and TensorFlow Lite, while connecting cloud reasoning engines (OpenAI & Gemini API) to power dynamic, context-aware mobile features like automated crop anomaly detection in Agri Connect.",
                    insightTitle = "AI Architecture Focus:",
                    tags = listOf("ONNX", "TensorFlow Lite", "OpenAI API", "Gemini API", "Edge ML")
                )
                q.contains("foodbridge") -> ChatMessage(
                    isUser = false,
                    text = "FoodBridge is a real-time humanitarian application designed to connect surplus food donors with active volunteer networks. Built on Clean Architecture with MVVM, it uses Supabase Realtime WebSocket pipelines to broadcast expiring food supplies with zero latency.",
                    insightTitle = "Architectural Pillars:",
                    tags = listOf("Clean Architecture", "MVVM", "WebSockets", "Jetpack Compose")
                )
                q.contains("kmp") || q.contains("multiplatform") || q.contains("react") || q.contains("flutter") -> ChatMessage(
                    isUser = false,
                    text = "Kotlin Multiplatform allows native performance and direct API access across Android, iOS, and the Web (WASM) without bridging bottlenecks or rendering compromises seen in hybrid JS layers. Sharing business logic while compiling to native composables is the modern standard for scaling apps.",
                    insightTitle = "KMP Strategic Advantages:",
                    tags = listOf("Native Performance", "WASM Compilation", "Zero Bridge Latency", "Shared Domain Layer")
                )
                else -> ChatMessage(
                    isUser = false,
                    text = "I am specifically engineered to detail Dhruva Khatavkar's development experience, Android architectures, and open-source contributions! Try exploring his projects or technical stack.",
                    insightTitle = "Recommended Inquiries:",
                    tags = listOf("What is your tech stack?", "Explain AI integration experience.", "Why Kotlin Multiplatform over React Native?")
                )
            }

            messages.add(response)
            listState.animateScrollToItem(messages.size - 1)
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Section Header
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = "Interactive AI Workspace",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Engage with a conversational simulator built on Dhruva's engineering philosophy and technical repository.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // Chat Container
        GlassCard(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 400.dp, max = 540.dp),
            contentPadding = 16.dp
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                // Messages List
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
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

                // Input Bar
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .background(SurfaceContainerHigh)
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    TextField(
                        value = inputQuery,
                        onValueChange = { inputQuery = it },
                        modifier = Modifier.weight(1f),
                        placeholder = { Text("Ask about my skills, projects, or KMP philosophy...", color = TextSecondary) },
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
                            .size(40.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(PrimaryContainer)
                            .clickable { submitQuery(inputQuery) }
                    ) {
                        Text(
                            text = ">>",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = Color.White
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
    val bgColor = if (message.isUser) PrimaryBlue.copy(alpha = 0.22f) else SurfaceContainerLow

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = align
    ) {
        Box(
            modifier = Modifier
                .widthIn(max = 640.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(bgColor)
                .padding(16.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    text = if (message.isUser) "USER" else "DHRUVA AI",
                    style = MaterialTheme.typography.labelSmall,
                    color = if (message.isUser) PrimaryBlue else TertiaryCyan
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
                            color = TextSecondary
                        )

                        @OptIn(ExperimentalLayoutApi::class)
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            message.tags.forEach { tag ->
                                TechChip(
                                    text = tag,
                                    textColor = PrimaryBlue,
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
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(SurfaceContainerLow)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Dhruva AI is analyzing repository context...",
            style = MaterialTheme.typography.labelSmall,
            color = TextSecondary
        )
    }
}
