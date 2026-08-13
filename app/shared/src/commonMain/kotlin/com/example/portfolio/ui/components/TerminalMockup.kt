package com.example.portfolio.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.portfolio.ui.theme.BorderDark
import com.example.portfolio.ui.theme.GlassBorder
import com.example.portfolio.ui.theme.PrimaryGreen
import com.example.portfolio.ui.theme.SecondaryWarm
import com.example.portfolio.ui.theme.SurfaceDark
import com.example.portfolio.ui.theme.TertiaryBlue
import com.example.portfolio.ui.theme.TextMuted

@Composable
fun TerminalMockup(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .shadow(
                elevation = 40.dp,
                shape = RoundedCornerShape(20.dp),
                spotColor = PrimaryGreen.copy(alpha = 0.08f),
                ambientColor = PrimaryGreen.copy(alpha = 0.04f)
            )
            .clip(RoundedCornerShape(20.dp))
            .background(SurfaceDark)
            .border(1.dp, GlassBorder, RoundedCornerShape(20.dp))
    ) {
        // Window bar — proper divider, no internal border
        Row(
            modifier = Modifier
                .fillMaxWidth()
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
                text = "DhruvaKhatavkar.kt",
                style = MaterialTheme.typography.labelSmall,
                color = TextMuted
            )
        }

        // Divider between window bar and code
        HorizontalDivider(color = BorderDark, thickness = 1.dp)

        // Code block with line numbers
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            CodeLineWithNumber(1, "val", "name", "\"Dhruva Khatavkar\"")
            CodeLineWithNumber(2, "val", "role", "\"Android Developer\"")
            CodeLineWithNumber(3, "val", "location", "\"Beed, India\"")

            Spacer(modifier = Modifier.height(2.dp))

            // Line 4: val stack = listOf(
            Row {
                LineNumber(4)
                Text(
                    text = buildAnnotatedString {
                        withStyle(SpanStyle(color = PrimaryGreen, fontWeight = FontWeight.Medium)) { append("val ") }
                        append("stack = ")
                        withStyle(SpanStyle(color = TertiaryBlue)) { append("listOf") }
                        append("(")
                    },
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Lines 5-6: items
            Row {
                LineNumber(5)
                Text(
                    text = "    \"Kotlin\", \"Jetpack Compose\",",
                    style = MaterialTheme.typography.labelMedium,
                    color = SecondaryWarm
                )
            }
            Row {
                LineNumber(6)
                Text(
                    text = "    \"MVVM\", \"Hilt\", \"Room\"",
                    style = MaterialTheme.typography.labelMedium,
                    color = SecondaryWarm
                )
            }

            // Line 7: closing paren
            Row {
                LineNumber(7)
                Text(
                    text = ")",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            CodeLineWithNumber(8, "val", "exploring", "\"Kotlin Multiplatform\"")

            Row(verticalAlignment = Alignment.CenterVertically) {
                CodeLineWithNumber(9, "val", "status", "\"Open to roles\"")
                Spacer(modifier = Modifier.width(4.dp))
                BlinkingCursor()
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        // Stats strip
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                .clip(RoundedCornerShape(12.dp))
                .border(1.dp, GlassBorder, RoundedCornerShape(12.dp)),
        ) {
            StatItem("3+", "Apps", Modifier.weight(1f))
            Box(modifier = Modifier.width(1.dp).fillMaxHeight().background(BorderDark))
            StatItem("2+", "Years", Modifier.weight(1f))
            Box(modifier = Modifier.width(1.dp).fillMaxHeight().background(BorderDark))
            StatItem("1", "Intern", Modifier.weight(1f))
            Box(modifier = Modifier.width(1.dp).fillMaxHeight().background(BorderDark))
            StatItem("KMP", "Learning", Modifier.weight(1f))
        }
    }
}

@Composable
private fun LineNumber(number: Int) {
    Text(
        text = "$number".padStart(2, ' '),
        style = MaterialTheme.typography.labelMedium.copy(fontSize = 11.sp),
        color = TextMuted.copy(alpha = 0.4f),
        modifier = Modifier.width(28.dp)
    )
}

@Composable
private fun CodeLineWithNumber(lineNum: Int, keyword: String, name: String, value: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        LineNumber(lineNum)
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(color = PrimaryGreen, fontWeight = FontWeight.Medium)) { append("$keyword ") }
                append("$name = ")
                withStyle(SpanStyle(color = SecondaryWarm)) { append(value) }
            },
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun StatItem(value: String, label: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(SurfaceDark)
            .padding(vertical = 14.dp, horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            color = PrimaryGreen
        )
        Text(
            text = label.uppercase(),
            style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun BlinkingCursor() {
    val infiniteTransition = rememberInfiniteTransition()
    val alpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 800
                1f at 0
                1f at 400
                0f at 401
                0f at 800
            },
            repeatMode = RepeatMode.Restart
        )
    )

    Box(
        modifier = Modifier
            .size(width = 8.dp, height = 15.dp)
            .alpha(alpha)
            .background(PrimaryGreen)
    )
}
