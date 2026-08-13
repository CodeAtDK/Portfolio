package com.example.portfolio.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.portfolio.ui.theme.BorderDark
import com.example.portfolio.ui.theme.PrimaryGreen
import com.example.portfolio.ui.theme.SecondaryWarm

@Composable
fun SectionHeader(
    number: String,
    title: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(bottom = 40.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            // Accent dot before the number
            Text(
                text = "—",
                style = MaterialTheme.typography.labelSmall,
                color = PrimaryGreen
            )
            Text(
                text = "$number //",
                style = MaterialTheme.typography.labelSmall,
                color = SecondaryWarm
            )
            Text(
                text = title,
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        // Thin horizontal divider
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(BorderDark)
        )
    }
}
