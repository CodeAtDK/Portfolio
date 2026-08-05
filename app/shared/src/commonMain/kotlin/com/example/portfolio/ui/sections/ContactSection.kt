package com.example.portfolio.ui.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.portfolio.ui.components.GlassCard
import com.example.portfolio.ui.theme.*

@Composable
fun ContactSection(modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var messageSent by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        // Section Header
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = "Get In Touch",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Open to collaborative opportunities, Android architecture discussions, and innovative AI engineering challenges.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            // Left Column: Interactive Message Form
            GlassCard(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                if (messageSent) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = "[SUCCESS] Message Sent Successfully!",
                            style = MaterialTheme.typography.titleMedium,
                            color = TertiaryCyan
                        )
                        Text(
                            text = "Thank you for reaching out. I will respond to your inquiry promptly.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Center
                        )
                        Button(
                            onClick = { messageSent = false; name = ""; email = ""; message = "" },
                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryContainer),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("Send Another Message", color = Color.White)
                        }
                    }
                } else {
                    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                        Text(
                            text = "Send a Direct Message",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        CustomFormTextField(
                            value = name,
                            onValueChange = { name = it },
                            placeholder = "Your Name"
                        )

                        CustomFormTextField(
                            value = email,
                            onValueChange = { email = it },
                            placeholder = "Email Address"
                        )

                        CustomFormTextField(
                            value = message,
                            onValueChange = { message = it },
                            placeholder = "Project Inquiry / Message Details",
                            modifier = Modifier.height(120.dp),
                            singleLine = false
                        )

                        Button(
                            onClick = { if (name.isNotBlank() && message.isNotBlank()) messageSent = true },
                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryContainer),
                            shape = RoundedCornerShape(12.dp),
                            contentPadding = PaddingValues(horizontal = 28.dp, vertical = 14.dp),
                            modifier = Modifier.align(Alignment.End)
                        ) {
                            Text(
                                text = "Send Message ->",
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                                color = Color.White
                            )
                        }
                    }
                }
            }

            // Right Column: Contact Details & Links (Stacked below on small widths via Column wrapper in responsive parent)
            Column(
                modifier = Modifier
                    .widthIn(min = 260.dp, max = 320.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                GlassCard(modifier = Modifier.fillMaxWidth()) {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        Text("Email", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold, color = PrimaryBlue)
                        Text("dhruva.khatavkar@example.com", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)

                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Location", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold, color = TertiaryCyan)
                        Text("Pune, Maharashtra, India\nOpen to Remote / Hybrid roles", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)

                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Tech Profiles", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold, color = Color(0xFF4CAF50))
                        Text("GitHub: github.com/dhruvasho\nLinkedIn: linkedin.com/in/dhruvasho", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }
        }

        // Footer Copyright
        Box(
            modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Copyright 2026 Dhruva Khatavkar | Built with Compose Multiplatform Logic",
                style = MaterialTheme.typography.labelSmall,
                color = TextSecondary,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun CustomFormTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder, color = TextSecondary) },
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(SurfaceContainerHigh),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurface
        ),
        singleLine = singleLine
    )
}
