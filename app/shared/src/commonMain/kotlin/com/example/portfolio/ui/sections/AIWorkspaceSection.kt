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
import androidx.compose.ui.unit.sp
import com.example.portfolio.ui.components.GlassCard
import com.example.portfolio.ui.components.TechChip
import com.example.portfolio.ui.theme.*
import kotlinx.coroutines.launch

data class ChatMessage(
    val text: String,
    val isUser: Boolean,
    val insightTitle: String? = null,
    val insightText: String? = null,
    val tags: List<String> = emptyList()
)

@Composable
fun AIWorkspaceSection(
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    var inputText by remember { mutableStateOf("") }
    val messages = remember {
        mutableStateListOf(
            ChatMessage(
                text = "👋 Hi! I'm Dhruva AI, trained directly on Dhruva Khatavkar's codebase skills, project case studies, and engineering philosophy. How can I assist you today?",
                isUser = false,
                insightTitle = "System Capability",
                insightText = "Ask about Kotlin development, Jetpack Compose architectures, AI integrations, or Kotlin Multiplatform (KMP) capabilities.",
                tags = listOf("Kotlin", "Jetpack Compose", "KMP", "Gemini AI")
            )
        )
    }

    fun handleSend(query: String) {
        if (query.isBlank()) return
        messages.add(ChatMessage(text = query, isUser = true))
        inputText = ""

        val response = generateAIResponse(query)
        messages.add(response)

        coroutineScope.launch {
            if (messages.isNotEmpty()) {
                listState.animateScrollToItem(messages.size - 1)
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Header
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = "AI WORKSPACE",
                style = MaterialTheme.typography.labelSmall,
                color = TertiaryCyan
            )
            Text(
                text = "Chat with Dhruva AI.",
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "An interactive neural simulation trained on my portfolio expertise and application architectures.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // Suggested Prompt Chips
        FlowRowWrapper {
            val prompts = listOf(
                "What is your tech stack?",
                "Explain AI integration experience.",
                "Show me your best UI work."
            )
            prompts.forEach { prompt ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(100.dp))
                        .background(PrimaryContainer.copy(alpha = 0.25f))
                        .border(1.dp, PrimaryBlue.copy(alpha = 0.45f), RoundedCornerShape(100.dp))
                        .clickable { handleSend(prompt) }
                        .padding(horizontal = 14.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "⚡ $prompt",
                        style = MaterialTheme.typography.labelMedium,
                        color = PrimaryBlue
                    )
                }
            }
        }

        // Chat Box Container
        GlassCard(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 380.dp, max = 500.dp),
            contentPadding = 16.dp
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(messages) { msg ->
                        ChatBubble(message = msg)
                    }
                }

                // Input Bar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = inputText,
                        onValueChange = { inputText = it },
                        placeholder = { Text("Ask about my skills or Android projects...", color = OnSurfaceVariant.copy(alpha = 0.6f)) },
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(16.dp))
                            .border(1.dp, GlassBorder, RoundedCornerShape(16.dp)),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = SurfaceContainerHighest.copy(alpha = 0.4f),
                            unfocusedContainerColor = SurfaceContainerLow.copy(alpha = 0.6f),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedTextColor = OnSurface,
                            unfocusedTextColor = OnSurface
                        )
                    )

                    IconButton(
                        onClick = { handleSend(inputText) },
                        modifier = Modifier
                            .size(52.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(PrimaryContainer)
                    ) {
                        Text(
                            text = "➤",
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
private fun ChatBubble(message: ChatMessage) {
    val bg = if (message.isUser) PrimaryContainer.copy(alpha = 0.25f) else SurfaceContainerHighest.copy(alpha = 0.6f)
    val align = if (message.isUser) Alignment.End else Alignment.Start

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = align
    ) {
        Box(
            modifier = Modifier
                .widthIn(max = 680.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(bg)
                .border(1.dp, if (message.isUser) PrimaryBlue.copy(alpha = 0.35f) else GlassBorder, RoundedCornerShape(20.dp))
                .padding(16.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = if (message.isUser) "👤 You" else "🤖 Dhruva AI",
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 13.sp),
                        color = if (message.isUser) PrimaryBlue else TertiaryCyan,
                        fontWeight = FontWeight.Bold
                    )
                }

                Text(
                    text = message.text,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                if (message.insightTitle != null && message.insightText != null) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(PrimaryContainer.copy(alpha = 0.15f))
                            .border(1.dp, PrimaryBlue.copy(alpha = 0.25f), RoundedCornerShape(12.dp))
                            .padding(12.dp)
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text(
                                text = "💡 ${message.insightTitle}",
                                style = MaterialTheme.typography.labelMedium,
                                color = PrimaryBlue,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = message.insightText,
                                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 13.sp),
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            if (message.tags.isNotEmpty()) {
                                FlowRowWrapper {
                                    message.tags.forEach { t -> TechChip(text = t) }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun generateAIResponse(query: String): ChatMessage {
    val q = query.lowercase()
    return when {
        q.contains("tech") || q.contains("stack") || q.contains("tool") || q.contains("kotlin") -> {
            ChatMessage(
                text = "My primary engineering toolkit revolves around Kotlin and the Android ecosystem. I build responsive user interfaces with Jetpack Compose, asynchronous flows with Coroutines + StateFlow, Dependency Injection via Hilt, and local database persistence with Room.",
                isUser = false,
                insightTitle = "Core Android & KMP Architecture",
                insightText = "All state management is designed around unidirectional data flow (UDF) patterns to ensure deterministic UI rendering across platforms.",
                tags = listOf("Kotlin", "Jetpack Compose", "Coroutines", "Hilt", "Room")
            )
        }
        q.contains("ai") || q.contains("gemini") || q.contains("neural") || q.contains("integration") -> {
            ChatMessage(
                text = "I integrate conversational and analytical AI capabilities directly into native mobile apps using Google's Gemini AI SDKs and real-time backend REST endpoints. For example, in CareNest, AI assistant models assist caregivers in parsing medication regimens and summarizing elderly patient health metrics.",
                isUser = false,
                insightTitle = "AI Integration Case Study: CareNest",
                insightText = "Utilized prompt engineering and structural JSON schemas to provide real-time healthcare inferences with minimal network latency.",
                tags = listOf("Gemini AI", "REST API", "JSON Parsing", "Retrofit")
            )
        }
        q.contains("ui") || q.contains("design") || q.contains("compose") || q.contains("best") -> {
            ChatMessage(
                text = "My UI philosophy centers on creating fluid, state-driven interfaces with smooth micro-interactions, responsive adaptability, and deep visual polish like the Synthetic Noir theme you are experiencing right now in this Compose Multiplatform application!",
                isUser = false,
                insightTitle = "Synthetic Noir Design System",
                insightText = "Constructed with atomic design tokens, custom Compose Canvas animations, and high-contrast OLED typography.",
                tags = listOf("Compose UI", "Material 3", "Canvas", "Animations")
            )
        }
        else -> {
            ChatMessage(
                text = "I'm equipped to discuss Dhruva Khatavkar's development career, architectural skills, Android apps (FoodBridge, Agri Connect, CareNest), or Kotlin Multiplatform initiatives. What would you like to explore in detail?",
                isUser = false,
                tags = listOf("Android SDK", "KMP", "UI/UX Architecture", "Open to Roles")
            )
        }
    }
}
