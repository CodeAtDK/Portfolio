package com.example.portfolio.ui.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import com.example.portfolio.ui.theme.*

@Composable
fun ContactSection(
    modifier: Modifier = Modifier
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var isSubmitted by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        // Header
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(
                text = "CONTACT",
                style = MaterialTheme.typography.labelSmall,
                color = TertiaryCyan
            )
            Text(
                text = "Let's connect & collaborate.",
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "I'm actively seeking full-time Android and Kotlin Multiplatform engineering opportunities. Have an exciting product or problem to solve? Send a message below!",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // Contact Form Card
        GlassCard(
            modifier = Modifier.fillMaxWidth()
        ) {
            if (isSubmitted) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "🎉 Message Sent Successfully!",
                        style = MaterialTheme.typography.headlineMedium,
                        color = TertiaryCyan
                    )
                    Text(
                        text = "Thank you for reaching out, $name! Dhruva will get back to you soon at $email.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Button(
                        onClick = { isSubmitted = false; name = ""; email = ""; message = "" },
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryContainer)
                    ) {
                        Text("Send Another Message", color = Color.White)
                    }
                }
            } else {
                Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                    Text(
                        text = "SEND A MESSAGE",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    TextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Your Name") },
                        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)),
                        colors = contactTextFieldColors()
                    )

                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email Address") },
                        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)),
                        colors = contactTextFieldColors()
                    )

                    TextField(
                        value = message,
                        onValueChange = { message = it },
                        label = { Text("How can we collaborate?") },
                        modifier = Modifier.fillMaxWidth().height(140.dp).clip(RoundedCornerShape(12.dp)),
                        colors = contactTextFieldColors()
                    )

                    Button(
                        onClick = { if (name.isNotBlank() && email.isNotBlank() && message.isNotBlank()) isSubmitted = true },
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryContainer),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth().height(50.dp)
                    ) {
                        Text(
                            text = "Send Message →",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                            color = Color.White
                        )
                    }
                }
            }
        }

        // Quick Links & Footer info
        GlassCard(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = SurfaceContainerLowest.copy(alpha = 0.8f)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    text = "QUICK CONNECT",
                    style = MaterialTheme.typography.labelSmall,
                    color = PrimaryBlue
                )
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("📧 Email", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
                    Text("khatavkardhruva@gmail.com", style = MaterialTheme.typography.bodyMedium, color = PrimaryBlue)
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("📍 Location", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
                    Text("Pune, India (Available Globally / Remote)", style = MaterialTheme.typography.bodyMedium, color = TertiaryCyan)
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("💻 Tech Profiles", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
                    Text("GitHub / LinkedIn @ DhruvaKhatavkar", style = MaterialTheme.typography.bodyMedium, color = SecondaryPurple)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text(
                        text = "© 2026 Dhruva Khatavkar • Built with Compose Multiplatform Logic",
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp),
                        color = OnSurfaceVariant.copy(alpha = 0.6f)
                    )
                }
            }
        }
    }
}

@Composable
private fun contactTextFieldColors() = TextFieldDefaults.colors(
    focusedContainerColor = SurfaceContainerHigh.copy(alpha = 0.5f),
    unfocusedContainerColor = SurfaceContainerLow.copy(alpha = 0.6f),
    focusedIndicatorColor = PrimaryBlue,
    unfocusedIndicatorColor = Color.Transparent,
    focusedTextColor = OnSurface,
    unfocusedTextColor = OnSurface
)
