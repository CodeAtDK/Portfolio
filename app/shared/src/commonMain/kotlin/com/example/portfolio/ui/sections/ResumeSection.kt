package com.example.portfolio.ui.sections

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.portfolio.ui.components.SectionHeader
import com.example.portfolio.ui.theme.*
import org.jetbrains.compose.resources.painterResource
import portfolio.app.shared.generated.resources.Res
import portfolio.app.shared.generated.resources.resume_preview

import androidx.compose.foundation.clickable

@Composable
fun ResumeSection(
    modifier: Modifier = Modifier,
    onOpenFullScreen: () -> Unit
) {
    val uriHandler = LocalUriHandler.current
    val isMobile = LocalIsMobile.current

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SectionHeader(number = "05", title = "Resume")

        Spacer(modifier = Modifier.height(32.dp))

        // Resume Preview Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(if (isMobile) 440.dp else 600.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(SurfaceElevated)
                .border(1.dp, GlassBorder, RoundedCornerShape(16.dp))
        ) {
            Image(
                painter = painterResource(Res.drawable.resume_preview),
                contentDescription = "Resume Preview",
                modifier = Modifier.fillMaxSize().clickable { onOpenFullScreen() },
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopCenter
            )
            
            // Overlay gradient at bottom
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, SurfaceDark.copy(alpha = 0.95f)),
                            startY = if (isMobile) 200f else 400f
                        )
                    )
            )

            // Responsive Buttons
            if (isMobile) {
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = onOpenFullScreen,
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen, contentColor = BackgroundDark),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(vertical = 12.dp)
                    ) {
                        Text("View Full Resume", fontWeight = FontWeight.SemiBold)
                    }
                    OutlinedButton(
                        onClick = { uriHandler.openUri("https://raw.githubusercontent.com/CodeAtDK/Portfolio/main/Dhruva_Khatavkar_Resume.pdf") },
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = PrimaryGreen),
                        border = androidx.compose.foundation.BorderStroke(1.dp, PrimaryGreen),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(vertical = 12.dp)
                    ) {
                        Text("Download PDF", fontWeight = FontWeight.SemiBold)
                    }
                }
            } else {
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(32.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = onOpenFullScreen,
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen, contentColor = BackgroundDark),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
                    ) {
                        Text("View Full Resume", fontWeight = FontWeight.SemiBold)
                    }
                    OutlinedButton(
                        onClick = { uriHandler.openUri("https://raw.githubusercontent.com/CodeAtDK/Portfolio/main/Dhruva_Khatavkar_Resume.pdf") },
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = PrimaryGreen),
                        border = androidx.compose.foundation.BorderStroke(1.dp, PrimaryGreen),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
                    ) {
                        Text("Download PDF", fontWeight = FontWeight.SemiBold)
                    }
                }
            }
        }
    }
}
