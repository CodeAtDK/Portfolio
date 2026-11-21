package com.example.portfolio.ui.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.portfolio.data.remote.ContactService
import com.example.portfolio.ui.components.PortfolioCard
import com.example.portfolio.ui.components.SectionHeader
import com.example.portfolio.ui.components.fadeInSlideUp
import com.example.portfolio.ui.theme.*
import kotlinx.coroutines.launch

@Composable
fun ContactSection(modifier: Modifier = Modifier) {
    val coroutineScope = rememberCoroutineScope()
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var isSubmitting by remember { mutableStateOf(false) }
    var messageSent by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val isMobile = LocalIsMobile.current

    fun submitContactForm() {
        if (name.isBlank() || email.isBlank() || message.isBlank() || isSubmitting) return
        if (!email.contains("@") || !email.contains(".")) {
            errorMessage = "Please enter a valid email address."
            return
        }

        errorMessage = null
        isSubmitting = true

        coroutineScope.launch {
            val result = ContactService.sendMessage(name, email, message)
            isSubmitting = false
            if (result.isSuccess) {
                messageSent = true
                name = ""
                email = ""
                message = ""
            } else {
                errorMessage = "Failed to send: ${result.exceptionOrNull()?.message ?: "Network error"}. Please try again or email directly."
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(if (isMobile) 24.dp else 40.dp)
    ) {
        SectionHeader(number = "07", title = "Contact")

        val formCard = @Composable {
            // Interactive Form Terminal
            PortfolioCard(
                modifier = Modifier
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
                            text = "dhruva-contact.sh // formspree",
                            style = MaterialTheme.typography.labelSmall,
                            color = TextMuted
                        )
                    }

                    HorizontalDivider(color = BorderDark, thickness = 1.dp)

                    if (messageSent) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(36.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(64.dp)
                                    .clip(CircleShape)
                                    .background(PrimaryGreen.copy(alpha = 0.15f))
                                    .border(1.5.dp, PrimaryGreen, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = "Success",
                                    tint = PrimaryGreen,
                                    modifier = Modifier.size(36.dp)
                                )
                            }
                            Text(
                                text = "[SUCCESS] Message Sent!",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                color = PrimaryGreen
                            )
                            Text(
                                text = "Thank you for reaching out! Your message was delivered directly via Formspree. I will respond to you promptly.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = TextAlign.Center
                            )
                            Button(
                                onClick = {
                                    messageSent = false
                                    errorMessage = null
                                    name = ""
                                    email = ""
                                    message = ""
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text("Send Another Message", color = BackgroundDark, fontWeight = FontWeight.SemiBold)
                            }
                        }
                    } else {
                        Column(
                            modifier = Modifier.padding(24.dp),
                            verticalArrangement = Arrangement.spacedBy(18.dp)
                        ) {
                            Text(
                                text = "> ./initiate_contact.sh --target formspree",
                                style = MaterialTheme.typography.labelMedium,
                                color = PrimaryGreen
                            )

                            if (errorMessage != null) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(Color(0xFFFF5F56).copy(alpha = 0.12f))
                                        .border(1.dp, Color(0xFFFF5F56).copy(alpha = 0.4f), RoundedCornerShape(8.dp))
                                        .padding(12.dp)
                                ) {
                                    Text(
                                        text = "✕ $errorMessage",
                                        color = Color(0xFFFF8B85),
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }

                            ContactFormTextField(
                                value = name,
                                onValueChange = { name = it; errorMessage = null },
                                placeholder = "Your Name",
                                enabled = !isSubmitting
                            )

                            ContactFormTextField(
                                value = email,
                                onValueChange = { email = it; errorMessage = null },
                                placeholder = "Email Address (e.g. name@company.com)",
                                enabled = !isSubmitting
                            )

                            ContactFormTextField(
                                value = message,
                                onValueChange = { message = it; errorMessage = null },
                                placeholder = "Project Inquiry / Message Details",
                                modifier = Modifier.height(120.dp),
                                singleLine = false,
                                enabled = !isSubmitting
                            )

                            Button(
                                onClick = { submitContactForm() },
                                enabled = !isSubmitting && name.isNotBlank() && email.isNotBlank() && message.isNotBlank(),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = PrimaryGreen,
                                    disabledContainerColor = PrimaryGreen.copy(alpha = 0.3f),
                                    contentColor = BackgroundDark,
                                    disabledContentColor = BackgroundDark.copy(alpha = 0.5f)
                                ),
                                shape = RoundedCornerShape(12.dp),
                                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 14.dp),
                                modifier = Modifier.align(Alignment.End)
                            ) {
                                if (isSubmitting) {
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        CircularProgressIndicator(
                                            modifier = Modifier.size(16.dp),
                                            color = BackgroundDark,
                                            strokeWidth = 2.dp
                                        )
                                        Text(
                                            text = "Sending...",
                                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
                                        )
                                    }
                                } else {
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = "Send Message",
                                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
                                        )
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.Send,
                                            contentDescription = "Send",
                                            tint = BackgroundDark,
                                            modifier = Modifier.size(15.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        val detailsCard = @Composable {
            // Contact Details & Links with icons
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fadeInSlideUp(delayMs = 200),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                val uriHandler = LocalUriHandler.current
                ContactDetailCard(
                    icon = Icons.Default.Email,
                    label = "Email",
                    labelColor = PrimaryGreen,
                    value = "khatavkardhruva@gmail.com",
                    onClick = { uriHandler.openUri("mailto:khatavkardhruva@gmail.com") }
                )
                ContactDetailCard(
                    icon = Icons.Default.Person,
                    label = "LinkedIn",
                    labelColor = SecondaryWarm,
                    value = "linkedin.com/in/dhruva-khatavkar",
                    onClick = { uriHandler.openUri("https://www.linkedin.com/in/dhruva-khatavkar") }
                )
                ContactDetailCard(
                    icon = Icons.Default.Code,
                    label = "GitHub",
                    labelColor = PrimaryGreen,
                    value = "github.com/CodeAtDK",
                    onClick = { uriHandler.openUri("https://github.com/CodeAtDK") }
                )
                ContactDetailCard(
                    icon = Icons.Default.Build,
                    label = "LeetCode",
                    labelColor = TertiaryBlue,
                    value = "leetcode.com/DhruvaKhatavkar",
                    onClick = { uriHandler.openUri("https://leetcode.com/DhruvaKhatavkar") }
                )
                ContactDetailCard(
                    icon = Icons.Default.LocationOn,
                    label = "Location",
                    labelColor = TertiaryBlue,
                    value = "Pune, Maharashtra, India • Open to work"
                )
            }
        }

        if (isMobile) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                formCard()
                detailsCard()
            }
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                Box(modifier = Modifier.weight(1f)) { formCard() }
                Box(modifier = Modifier.widthIn(min = 260.dp, max = 320.dp)) { detailsCard() }
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
    icon: ImageVector,
    label: String,
    labelColor: Color,
    value: String,
    onClick: (() -> Unit)? = null
) {
    val cardModifier = if (onClick != null) {
        Modifier.fillMaxWidth().clickable(onClick = onClick)
    } else {
        Modifier.fillMaxWidth()
    }
    PortfolioCard(modifier = cardModifier) {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    tint = labelColor,
                    modifier = Modifier.size(20.dp)
                )
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
    singleLine: Boolean = true,
    enabled: Boolean = true
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder, color = TextMuted) },
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(SurfaceDark)
            .border(1.dp, GlassBorder, RoundedCornerShape(12.dp)),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
            disabledTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        ),
        singleLine = singleLine
    )
}
