package com.example.portfolio.ui.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import com.example.portfolio.ui.components.PortfolioCard
import com.example.portfolio.ui.components.SectionHeader
import com.example.portfolio.ui.components.fadeInSlideUp
import com.example.portfolio.ui.theme.*

@Composable
fun ContactSection(modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var messageSent by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        SectionHeader(number = "06", title = "Contact")

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            // Left Column: Interactive Form Terminal
            PortfolioCard(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .fadeInSlideUp(delayMs = 0),
                contentPadding = 0.dp
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    // Terminal Header
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
                        Text(
                            text = "dhruva-contact.sh",
                            style = MaterialTheme.typography.labelSmall,
                            color = TextMuted
                        )
                    }

                    HorizontalDivider(color = BorderDark, thickness = 1.dp)

                    if (messageSent) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(32.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text(
                                text = "✓",
                                style = MaterialTheme.typography.displayMedium,
                                color = PrimaryGreen
                            )
                            Text(
                                text = "[SUCCESS] Message Sent!",
                                style = MaterialTheme.typography.titleMedium,
                                color = PrimaryGreen
                            )
                            Text(
                                text = "Thank you for reaching out. I will respond to your inquiry promptly.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = TextAlign.Center
                            )
                            Button(
                                onClick = { messageSent = false; name = ""; email = ""; message = "" },
                                colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text("Send Another Message", color = BackgroundDark)
                            }
                        }
                    } else {
                        Column(
                            modifier = Modifier.padding(24.dp),
                            verticalArrangement = Arrangement.spacedBy(20.dp)
                        ) {
                            Text(
                                text = "> ./initiate_contact.sh",
                                style = MaterialTheme.typography.labelMedium,
                                color = PrimaryGreen
                            )

                            ContactFormTextField(
                                value = name,
                                onValueChange = { name = it },
                                placeholder = "Your Name"
                            )

                            ContactFormTextField(
                                value = email,
                                onValueChange = { email = it },
                                placeholder = "Email Address"
                            )

                            ContactFormTextField(
                                value = message,
                                onValueChange = { message = it },
                                placeholder = "Project Inquiry / Message Details",
                                modifier = Modifier.height(120.dp),
                                singleLine = false
                            )

                            Button(
                                onClick = { if (name.isNotBlank() && message.isNotBlank()) messageSent = true },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = PrimaryGreen,
                                    contentColor = BackgroundDark
                                ),
                                shape = RoundedCornerShape(12.dp),
                                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 14.dp),
                                modifier = Modifier.align(Alignment.End)
                            ) {
                                Text(
                                    text = "Send Message ->",
                                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
                                )
                            }
                        }
                    }
                }
            }

            // Right Column: Contact Details & Links with icons
            Column(
                modifier = Modifier
                    .widthIn(min = 260.dp, max = 320.dp)
                    .fadeInSlideUp(delayMs = 200),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ContactDetailCard(
                    icon = "📧",
                    label = "Email",
                    labelColor = PrimaryGreen,
                    value = "dhruvakhatavkar29@gmail.com"
                )
                ContactDetailCard(
                    icon = "📍",
                    label = "Location",
                    labelColor = TertiaryBlue,
                    value = "Pune, Maharashtra, India\nOpen to Remote / Hybrid roles"
                )
                ContactDetailCard(
                    icon = "🔗",
                    label = "Socials",
                    labelColor = SecondaryWarm,
                    value = "GitHub: github.com/DhruvaKhatavkar\nLinkedIn: linkedin.com/in/dhruva-khatavkar"
                )
            }
        }

        // Footer — divider + copyright
        Column(
            modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            HorizontalDivider(color = BorderDark, thickness = 1.dp)
            Text(
                text = "© 2026 Dhruva Khatavkar // Built with Native Compose Multiplatform",
                style = MaterialTheme.typography.labelSmall,
                color = TextMuted,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun ContactDetailCard(
    icon: String,
    label: String,
    labelColor: Color,
    value: String
) {
    PortfolioCard(modifier = Modifier.fillMaxWidth()) {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = icon, style = MaterialTheme.typography.titleMedium)
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = labelColor
                )
            }
            Text(
                text = value,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ContactFormTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder, color = TextMuted) },
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(SurfaceDark)
            .border(1.dp, GlassBorder, RoundedCornerShape(12.dp)),
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
