package com.example.portfolio.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.portfolio.model.NavSection

@Composable
fun MobileBottomNav(
    currentSection: NavSection,
    onSectionSelected: (NavSection) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(Color(0xFF0A0A0A).copy(alpha = 0.6f))
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        NavSection.values().forEach { section ->
            val isSelected = section == currentSection
            
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onSectionSelected(section) },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(4.dp)
                        .background(if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = section.title.take(4), // Shortened for mobile
                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp),
                    color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
