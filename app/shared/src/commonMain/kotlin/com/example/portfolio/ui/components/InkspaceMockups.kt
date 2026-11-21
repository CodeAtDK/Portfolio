package com.example.portfolio.ui.components

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.portfolio.ui.theme.PrimaryGreen
import com.example.portfolio.ui.theme.SurfaceDark

enum class InkspaceTab(val title: String, val subtitle: String) {
    EDITOR("Studio Canvas", "3-tier GoodNotes toolbar & inking engine"),
    TEMPLATES("Templates & Pages", "15+ vector paper grids & page organizer"),
    AUDIO_COPILOT("Audio & AI Copilot", "Timestamped audio sync & study notes"),
    CLOUD_SYNC("Supabase Sync", "Real-time 3-way conflict resolution")
}

enum class InkingTool(val label: String, val icon: ImageVector) {
    FOUNTAIN_PEN("Fountain Pen", Icons.Default.Edit),
    BRUSH_PEN("Brush Pen", Icons.Default.Brush),
    BALLPOINT("Ballpoint", Icons.Default.Create),
    HIGHLIGHTER("Highlighter", Icons.Default.FormatColorFill),
    ERASER("Eraser", Icons.Default.AutoFixNormal),
    LASSO("Lasso Select", Icons.Default.CropFree),
    SHAPES("Shape Snapping", Icons.Default.Category),
    STICKER("Stickers", Icons.Default.Favorite),
    IMAGE("Media Import", Icons.Default.Image),
    TEXT("Text Box", Icons.Default.TextFields),
    LASER("Laser Pointer", Icons.Default.Highlight)
}

enum class PaperGridStyle(val label: String) {
    DOT_MATRIX("Dot Matrix"),
    RULED_LINES("Ruled Lines"),
    CORNELL("Cornell Notes"),
    ENGINEERING("Engineering Grid"),
    BLANK("Plain Blank")
}

/**
 * High-performance interactive showcase simulating the GoodNotes 5-grade
 * Inkspace Notebook application on Compose Multiplatform.
 */
@Composable
fun InkspaceInteractiveShowcase(
    modifier: Modifier = Modifier,
    initialTab: InkspaceTab = InkspaceTab.EDITOR
) {
    var selectedTab by remember { mutableStateOf(initialTab) }
    val isMobile = com.example.portfolio.ui.theme.LocalIsMobile.current

    // Studio Inking State
    var activeTool by remember { mutableStateOf(InkingTool.FOUNTAIN_PEN) }
    var selectedColorIndex by remember { mutableStateOf(0) }
    var strokeThickness by remember { mutableStateOf(1) } // 0=0.3mm, 1=0.5mm, 2=1.0mm, 3=2.5mm
    var selectedPaperStyle by remember { mutableStateOf(PaperGridStyle.DOT_MATRIX) }
    var isDarkPaper by remember { mutableStateOf(true) }
    var isRecordingAudio by remember { mutableStateOf(true) }
    var activeNotebookTitle by remember { mutableStateOf("KMP & Inking Architecture.ink") }

    val presetColors = listOf(
        Color(0xFF3B82F6), // Studio Blue
        Color(0xFF10B981), // Emerald
        Color(0xFFEF4444), // Crimson
        Color(0xFFF59E0B), // Amber
        Color(0xFF8B5CF6), // Purple
        Color(0xFF06B6D4), // Cyan
        Color(0xFFF43F5E), // Rose
        Color(0xFFFFFFFF)  // Chalk White
    )
    val activeInkColor = presetColors[selectedColorIndex]

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(if (isMobile) 14.dp else 20.dp)
    ) {
        // ─── Mode Tab Selector Pills ──────────────────────────────────────────
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .background(SurfaceDark)
                .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(50))
                .padding(4.dp)
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            InkspaceTab.entries.forEach { tab ->
                val isSelected = selectedTab == tab
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(if (isSelected) Color(0xFF2563EB) else Color.Transparent)
                        .clickable { selectedTab = tab }
                        .padding(horizontal = if (isMobile) 10.dp else 16.dp, vertical = if (isMobile) 6.dp else 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = tab.title,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            fontSize = if (isMobile) 10.sp else 11.sp
                        ),
                        color = if (isSelected) Color.White else Color(0xFF94A3B8)
                    )
                }
            }
        }

        // ─── Tablet Frame Container (GoodNotes / iPad Style) ──────────────────
        Box(
            modifier = Modifier
                .widthIn(max = if (isMobile) 340.dp else 680.dp)
                .fillMaxWidth()
                .shadow(
                    elevation = 32.dp,
                    shape = RoundedCornerShape(if (isMobile) 28.dp else 36.dp),
                    spotColor = Color(0xFF2563EB).copy(alpha = 0.35f)
                )
                .clip(RoundedCornerShape(if (isMobile) 28.dp else 36.dp))
                .background(Color(0xFF0F172A))
                .border(3.dp, Color(0xFF1E293B), RoundedCornerShape(if (isMobile) 28.dp else 36.dp))
                .padding(if (isMobile) 6.dp else 10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(if (isMobile) 22.dp else 28.dp))
                    .background(Color(0xFF090D16))
            ) {
                // Tier 1 Header: System & Document Bar
                InkspaceTier1Header(
                    notebookTitle = activeNotebookTitle,
                    isDarkPaper = isDarkPaper,
                    onToggleDarkPaper = { isDarkPaper = !isDarkPaper },
                    isRecording = isRecordingAudio,
                    onToggleRecording = { isRecordingAudio = !isRecordingAudio }
                )

                // Tier 2 Header: Tab Strip for Multi-Documents
                InkspaceMultiTabStrip(
                    activeTitle = activeNotebookTitle,
                    onSelectTitle = { activeNotebookTitle = it }
                )

                // Tier 3 Header: GoodNotes 5-Grade Inking Tools Toolbar
                if (selectedTab == InkspaceTab.EDITOR) {
                    InkspaceTier3Toolbar(
                        activeTool = activeTool,
                        onSelectTool = { activeTool = it },
                        presetColors = presetColors,
                        selectedColorIndex = selectedColorIndex,
                        onSelectColor = { selectedColorIndex = it },
                        strokeThickness = strokeThickness,
                        onSelectThickness = { strokeThickness = it }
                    )
                }

                // Interactive Main Viewport Canvas
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(if (isMobile) 440.dp else 460.dp)
                        .background(if (isDarkPaper) Color(0xFF111827) else Color(0xFFF8FAFC))
                ) {
                    Crossfade(targetState = selectedTab, animationSpec = tween(300)) { tab ->
                        when (tab) {
                            InkspaceTab.EDITOR -> {
                                InkspaceCanvasViewport(
                                    isDarkPaper = isDarkPaper,
                                    gridStyle = selectedPaperStyle,
                                    activeColor = activeInkColor,
                                    strokeThickness = strokeThickness,
                                    activeTool = activeTool
                                )
                            }
                            InkspaceTab.TEMPLATES -> {
                                InkspaceTemplatesViewport(
                                    selectedStyle = selectedPaperStyle,
                                    onSelectStyle = { selectedPaperStyle = it },
                                    isDark = isDarkPaper,
                                    onToggleDark = { isDarkPaper = it }
                                )
                            }
                            InkspaceTab.AUDIO_COPILOT -> {
                                InkspaceAudioCopilotViewport(
                                    isRecording = isRecordingAudio,
                                    isDarkPaper = isDarkPaper
                                )
                            }
                            InkspaceTab.CLOUD_SYNC -> {
                                InkspaceCloudSyncViewport(
                                    isDarkPaper = isDarkPaper
                                )
                            }
                        }
                    }
                }

                // Bottom Status Strip
                InkspaceBottomStatusFooter(
                    activeTool = activeTool,
                    activeColor = activeInkColor,
                    paperStyle = selectedPaperStyle
                )
            }
        }
    }
}

// ─── Tier 1 Header: Document Title, Audio Sync & Cloud Status ───────────────
@Composable
private fun InkspaceTier1Header(
    notebookTitle: String,
    isDarkPaper: Boolean,
    onToggleDarkPaper: () -> Unit,
    isRecording: Boolean,
    onToggleRecording: () -> Unit
) {
    val isMobile = com.example.portfolio.ui.theme.LocalIsMobile.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF0F172A))
            .padding(horizontal = if (isMobile) 10.dp else 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Left actions: Back, Page Manager, Title
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color(0xFF94A3B8),
                modifier = Modifier.size(18.dp)
            )
            Icon(
                imageVector = Icons.Default.GridView,
                contentDescription = "Pages",
                tint = Color(0xFF94A3B8),
                modifier = Modifier.size(18.dp)
            )
            Text(
                text = if (isMobile) notebookTitle.take(18) + "..." else notebookTitle,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = if (isMobile) 11.sp else 12.sp
                ),
                color = Color.White
            )
        }

        // Right actions: Audio Waveform Pill, Theme Toggle, Share
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Audio recording pill
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(if (isRecording) Color(0xFFDC2626).copy(alpha = 0.2f) else Color.White.copy(alpha = 0.05f))
                    .border(1.dp, if (isRecording) Color(0xFFDC2626).copy(alpha = 0.5f) else Color.White.copy(alpha = 0.1f), RoundedCornerShape(50))
                    .clickable { onToggleRecording() }
                    .padding(horizontal = 8.dp, vertical = 3.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(if (isRecording) Color(0xFFEF4444) else Color(0xFF64748B))
                )
                Text(
                    text = if (isRecording) "04:18" else "Rec",
                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp, fontWeight = FontWeight.Bold),
                    color = if (isRecording) Color(0xFFFCA5A5) else Color(0xFF94A3B8)
                )
            }

            // Paper Theme toggle (Light/Dark)
            Box(
                modifier = Modifier
                    .size(26.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.08f))
                    .clickable { onToggleDarkPaper() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = if (isDarkPaper) Icons.Default.LightMode else Icons.Default.DarkMode,
                    contentDescription = "Theme",
                    tint = Color(0xFFF59E0B),
                    modifier = Modifier.size(14.dp)
                )
            }

            // Sync Status Dot
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(PrimaryGreen)
            )
        }
    }
}

// ─── Tier 2 Header: Multi-Tab Notebook Switcher ──────────────────────────────
@Composable
private fun InkspaceMultiTabStrip(
    activeTitle: String,
    onSelectTitle: (String) -> Unit
) {
    val tabs = listOf(
        "KMP & Inking Architecture.ink",
        "Physics Chapter 4.pdf",
        "Weekly Sprint Planner.ink"
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF090D16))
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        tabs.forEach { title ->
            val isActive = activeTitle == title
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                    .background(if (isActive) Color(0xFF1E293B) else Color(0xFF0F172A))
                    .clickable { onSelectTitle(title) }
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Icon(
                    imageVector = if (title.endsWith(".pdf")) Icons.Default.PictureAsPdf else Icons.Default.Description,
                    contentDescription = null,
                    tint = if (isActive) Color(0xFF60A5FA) else Color(0xFF64748B),
                    modifier = Modifier.size(12.dp)
                )
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontSize = 10.sp,
                        fontWeight = if (isActive) FontWeight.SemiBold else FontWeight.Normal
                    ),
                    color = if (isActive) Color.White else Color(0xFF94A3B8)
                )
                if (isActive) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Tab",
                        tint = Color(0xFF94A3B8),
                        modifier = Modifier.size(10.dp)
                    )
                }
            }
        }
    }
}

// ─── Tier 3 Header: 11 Precision Inking Tools & Color Studio ────────────────
@Composable
private fun InkspaceTier3Toolbar(
    activeTool: InkingTool,
    onSelectTool: (InkingTool) -> Unit,
    presetColors: List<Color>,
    selectedColorIndex: Int,
    onSelectColor: (Int) -> Unit,
    strokeThickness: Int,
    onSelectThickness: (Int) -> Unit
) {
    val isMobile = com.example.portfolio.ui.theme.LocalIsMobile.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF1E293B))
            .padding(horizontal = 8.dp, vertical = 6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        // Tool Icons Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            InkingTool.entries.forEach { tool ->
                val isSelected = activeTool == tool
                Box(
                    modifier = Modifier
                        .size(if (isMobile) 30.dp else 34.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (isSelected) Color(0xFF2563EB) else Color.Transparent)
                        .clickable { onSelectTool(tool) },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = tool.icon,
                        contentDescription = tool.label,
                        tint = if (isSelected) Color.White else Color(0xFF94A3B8),
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }

        // Secondary Row: Thickness Presets + Color Dots
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Thickness indicators (0.3mm, 0.5mm, 1.0mm, 2.5mm)
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val thicknesses = listOf(2.dp, 4.dp, 6.dp, 9.dp)
                thicknesses.forEachIndexed { index, dpSize ->
                    val isSelected = strokeThickness == index
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(if (isSelected) Color.White.copy(alpha = 0.2f) else Color.Transparent)
                            .clickable { onSelectThickness(index) },
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(dpSize)
                                .clip(CircleShape)
                                .background(if (isSelected) Color(0xFF38BDF8) else Color(0xFF94A3B8))
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Color preset dots
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                presetColors.forEachIndexed { index, color ->
                    val isSelected = selectedColorIndex == index
                    Box(
                        modifier = Modifier
                            .size(if (isSelected) 20.dp else 16.dp)
                            .clip(CircleShape)
                            .background(color)
                            .border(
                                width = if (isSelected) 2.dp else 1.dp,
                                color = if (isSelected) Color.White else Color.Black.copy(alpha = 0.3f),
                                shape = CircleShape
                            )
                            .clickable { onSelectColor(index) }
                    )
                }
            }
        }
    }
}

// ─── Inking Canvas Viewport (Simulating Skiko Quadratic Bézier Inking) ───────
@Composable
private fun InkspaceCanvasViewport(
    isDarkPaper: Boolean,
    gridStyle: PaperGridStyle,
    activeColor: Color,
    strokeThickness: Int,
    activeTool: InkingTool
) {
    val paperBg = if (isDarkPaper) Color(0xFF111827) else Color(0xFFF8FAFC)
    val gridLineColor = if (isDarkPaper) Color.White.copy(alpha = 0.07f) else Color.Black.copy(alpha = 0.08f)
    val textMainColor = if (isDarkPaper) Color(0xFFE2E8F0) else Color(0xFF1E293B)
    val inkStrokeWidth = when (strokeThickness) {
        0 -> 1.5f
        1 -> 3.0f
        2 -> 5.0f
        else -> 8.0f
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Vector Paper Grid Background
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height
            val spacing = 28.dp.toPx()

            when (gridStyle) {
                PaperGridStyle.DOT_MATRIX -> {
                    var x = spacing
                    while (x < width) {
                        var y = spacing
                        while (y < height) {
                            drawCircle(
                                color = gridLineColor,
                                radius = 1.5f,
                                center = Offset(x, y)
                            )
                            y += spacing
                        }
                        x += spacing
                    }
                }
                PaperGridStyle.RULED_LINES -> {
                    var y = spacing
                    while (y < height) {
                        drawLine(
                            color = gridLineColor,
                            start = Offset(0f, y),
                            end = Offset(width, y),
                            strokeWidth = 1f
                        )
                        y += spacing
                    }
                }
                PaperGridStyle.ENGINEERING -> {
                    var x = spacing
                    while (x < width) {
                        drawLine(color = gridLineColor, start = Offset(x, 0f), end = Offset(x, height), strokeWidth = 1f)
                        x += spacing
                    }
                    var y = spacing
                    while (y < height) {
                        drawLine(color = gridLineColor, start = Offset(0f, y), end = Offset(width, y), strokeWidth = 1f)
                        y += spacing
                    }
                }
                PaperGridStyle.CORNELL -> {
                    // Left margin line
                    drawLine(
                        color = Color(0xFFEF4444).copy(alpha = 0.4f),
                        start = Offset(70.dp.toPx(), 0f),
                        end = Offset(70.dp.toPx(), height),
                        strokeWidth = 1.5f
                    )
                    // Ruled lines
                    var y = spacing
                    while (y < height) {
                        drawLine(color = gridLineColor, start = Offset(0f, y), end = Offset(width, y), strokeWidth = 1f)
                        y += spacing
                    }
                }
                PaperGridStyle.BLANK -> {}
            }

            // Draw Simulated Quadratic Bézier Handwriting Vector Paths
            val path1 = Path().apply {
                moveTo(width * 0.15f, height * 0.22f)
                quadraticTo(width * 0.35f, height * 0.18f, width * 0.55f, height * 0.24f)
                quadraticTo(width * 0.70f, height * 0.29f, width * 0.82f, height * 0.23f)
            }
            drawPath(
                path = path1,
                color = activeColor,
                style = Stroke(width = inkStrokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round)
            )

            // Highlighter Alpha Underlay
            val highlightPath = Path().apply {
                moveTo(width * 0.14f, height * 0.38f)
                lineTo(width * 0.62f, height * 0.38f)
            }
            drawPath(
                path = highlightPath,
                color = Color(0xFFFBBF24).copy(alpha = 0.35f),
                style = Stroke(width = 18f, cap = StrokeCap.Square)
            )

            // Vector Architecture Box Snap
            val rectPath = Path().apply {
                moveTo(width * 0.14f, height * 0.52f)
                lineTo(width * 0.48f, height * 0.52f)
                lineTo(width * 0.48f, height * 0.76f)
                lineTo(width * 0.14f, height * 0.76f)
                close()
            }
            drawPath(
                path = rectPath,
                color = Color(0xFF06B6D4),
                style = Stroke(width = 2.5f, cap = StrokeCap.Round, join = StrokeJoin.Round)
            )

            // Arrow to Cloud Node
            val arrowPath = Path().apply {
                moveTo(width * 0.48f, height * 0.64f)
                quadraticTo(width * 0.60f, height * 0.62f, width * 0.68f, height * 0.64f)
                lineTo(width * 0.65f, height * 0.60f)
                moveTo(width * 0.68f, height * 0.64f)
                lineTo(width * 0.65f, height * 0.68f)
            }
            drawPath(
                path = arrowPath,
                color = Color(0xFF10B981),
                style = Stroke(width = 2.5f, cap = StrokeCap.Round, join = StrokeJoin.Round)
            )

            // Cloud Node Box Snap
            val cloudRect = Path().apply {
                moveTo(width * 0.68f, height * 0.55f)
                lineTo(width * 0.88f, height * 0.55f)
                lineTo(width * 0.88f, height * 0.73f)
                lineTo(width * 0.68f, height * 0.73f)
                close()
            }
            drawPath(
                path = cloudRect,
                color = Color(0xFF3B82F6),
                style = Stroke(width = 2.5f, cap = StrokeCap.Round, join = StrokeJoin.Round)
            )
        }

        // Overlay Text Label Annotations
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0xFF2563EB).copy(alpha = 0.2f))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "KMP + Skiko Inking Core",
                            style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp, fontWeight = FontWeight.Bold),
                            color = Color(0xFF60A5FA)
                        )
                    }
                    Text(
                        text = "latency: < 7.8ms",
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp, color = PrimaryGreen)
                    )
                }

                Text(
                    text = "∫ (Smooth quadratic Bézier curve interpolation) dt",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontFamily = FontFamily.Monospace,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = textMainColor
                )
            }

            // Architecture Flow diagram labels
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (isDarkPaper) Color(0xFF1E293B).copy(alpha = 0.85f) else Color.White.copy(alpha = 0.9f))
                        .border(1.dp, Color(0xFF06B6D4).copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                        .padding(8.dp)
                ) {
                    Column {
                        Text(
                            text = "Compose Multiplatform",
                            style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp, fontWeight = FontWeight.Bold),
                            color = Color(0xFF06B6D4)
                        )
                        Text(
                            text = "SQLDelight SQLite Local",
                            style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp),
                            color = textMainColor
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (isDarkPaper) Color(0xFF1E293B).copy(alpha = 0.85f) else Color.White.copy(alpha = 0.9f))
                        .border(1.dp, Color(0xFF3B82F6).copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                        .padding(8.dp)
                ) {
                    Column {
                        Text(
                            text = "Supabase Realtime",
                            style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp, fontWeight = FontWeight.Bold),
                            color = Color(0xFF3B82F6)
                        )
                        Text(
                            text = "3-Way Conflict Resolution",
                            style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp),
                            color = textMainColor
                        )
                    }
                }
            }
        }
    }
}

// ─── Templates Viewport ──────────────────────────────────────────────────────
@Composable
private fun InkspaceTemplatesViewport(
    selectedStyle: PaperGridStyle,
    onSelectStyle: (PaperGridStyle) -> Unit,
    isDark: Boolean,
    onToggleDark: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            text = "15+ Vector Paper Templates & Grid Engine",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = if (isDark) Color.White else Color(0xFF0F172A)
        )
        Text(
            text = "Instant GPU vector-rendered templates customizable in Light, Sepia, and Dark tones with standard ISO A4/Letter margins.",
            style = MaterialTheme.typography.bodySmall,
            color = if (isDark) Color(0xFF94A3B8) else Color(0xFF64748B)
        )

        // Template Grid Cards
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            PaperGridStyle.entries.forEach { style ->
                val isSelected = selectedStyle == style
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (isSelected) Color(0xFF2563EB).copy(alpha = 0.2f) else if (isDark) Color(0xFF1E293B) else Color(0xFFE2E8F0))
                        .border(
                            width = if (isSelected) 2.dp else 1.dp,
                            color = if (isSelected) Color(0xFF3B82F6) else Color.White.copy(alpha = 0.1f),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clickable { onSelectStyle(style) }
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .background(if (isDark) Color(0xFF0F172A) else Color.White)
                                .border(1.dp, Color(0xFF64748B), RoundedCornerShape(6.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.MenuBook,
                                contentDescription = null,
                                tint = if (isSelected) Color(0xFF38BDF8) else Color(0xFF94A3B8),
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        Column {
                            Text(
                                text = style.label,
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                color = if (isDark) Color.White else Color(0xFF0F172A)
                            )
                            Text(
                                text = when (style) {
                                    PaperGridStyle.DOT_MATRIX -> "5mm dotted spacing, ideal for bullet journals"
                                    PaperGridStyle.RULED_LINES -> "7mm ruled lines with red margin border"
                                    PaperGridStyle.CORNELL -> "Cue column + summary footer for lecture notes"
                                    PaperGridStyle.ENGINEERING -> "Isometric & Cartesian millimeter grid"
                                    PaperGridStyle.BLANK -> "Infinite white/dark sketching canvas"
                                },
                                style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp),
                                color = if (isDark) Color(0xFF94A3B8) else Color(0xFF64748B)
                            )
                        }
                    }

                    if (isSelected) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "Selected",
                            tint = Color(0xFF38BDF8),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}

// ─── Audio Sync & AI Copilot Viewport ────────────────────────────────────────
@Composable
private fun InkspaceAudioCopilotViewport(
    isRecording: Boolean,
    isDarkPaper: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Audio Sync Header Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(if (isDarkPaper) Color(0xFF1E293B) else Color(0xFFEEF2F6))
                .padding(14.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Mic,
                            contentDescription = "Audio Sync",
                            tint = Color(0xFFEF4444),
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = "Time-Stamped Audio Sync",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                            color = if (isDarkPaper) Color.White else Color(0xFF0F172A)
                        )
                    }
                    Text(
                        text = "12 Inking Markers Tracked",
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp, color = PrimaryGreen)
                    )
                }

                // Simulated Waveform Bars
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val heights = listOf(14, 28, 42, 18, 36, 48, 22, 10, 32, 45, 20, 38, 16, 29, 44, 24, 12, 34, 46, 22)
                    heights.forEach { h ->
                        Box(
                            modifier = Modifier
                                .width(6.dp)
                                .height(h.dp)
                                .clip(RoundedCornerShape(3.dp))
                                .background(if (isRecording) Color(0xFF38BDF8) else Color(0xFF64748B))
                        )
                    }
                }
            }
        }

        // AI Study Copilot Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFF2563EB).copy(alpha = 0.15f))
                .border(1.dp, Color(0xFF3B82F6).copy(alpha = 0.3f), RoundedCornerShape(12.dp))
                .padding(14.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.AutoAwesome,
                        contentDescription = "AI",
                        tint = Color(0xFF38BDF8),
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "AI Study Assistant Copilot",
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                        color = Color(0xFF38BDF8)
                    )
                }
                Text(
                    text = "Page Summary: Explains KMP shared business logic with Skiko vector handwriting interpolation pipeline, achieving < 8ms render loop.",
                    style = MaterialTheme.typography.bodySmall,
                    color = if (isDarkPaper) Color(0xFFE2E8F0) else Color(0xFF1E293B)
                )

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(Color(0xFF3B82F6))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text("3 Flashcards Generated", style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp, color = Color.White))
                    }
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(Color.White.copy(alpha = 0.1f))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text("Explain Math Formula", style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp, color = if (isDarkPaper) Color.White else Color(0xFF0F172A)))
                    }
                }
            }
        }
    }
}

// ─── Cloud Sync & Conflict Viewport ──────────────────────────────────────────
@Composable
private fun InkspaceCloudSyncViewport(
    isDarkPaper: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Sync Status Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(if (isDarkPaper) Color(0xFF1E293B) else Color(0xFFEEF2F6))
                .padding(14.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.CloudDone,
                            contentDescription = "Sync",
                            tint = PrimaryGreen,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = "Supabase Realtime Stream",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                            color = if (isDarkPaper) Color.White else Color(0xFF0F172A)
                        )
                    }
                    Text(
                        text = "100% Offline-First",
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp, color = PrimaryGreen)
                    )
                }

                Text(
                    text = "Local SQLite changes are delta-buffered via SQLDelight and pushed seamlessly across iPad, Android tablet, and Desktop web clients upon reconnection.",
                    style = MaterialTheme.typography.bodySmall,
                    color = if (isDarkPaper) Color(0xFF94A3B8) else Color(0xFF64748B)
                )
            }
        }

        // 3-Way Merge Visualizer
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFF10B981).copy(alpha = 0.12f))
                .border(1.dp, Color(0xFF10B981).copy(alpha = 0.3f), RoundedCornerShape(12.dp))
                .padding(14.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.CallSplit,
                        contentDescription = "Merge",
                        tint = Color(0xFF10B981),
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "Automated 3-Way Vector Stroke Merge",
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                        color = Color(0xFF10B981)
                    )
                }
                Text(
                    text = "Strokes carry UUIDs and Lamport logical clocks. Concurrent edits on separate pages or non-overlapping canvas regions merge automatically with zero data overwrite.",
                    style = MaterialTheme.typography.bodySmall,
                    color = if (isDarkPaper) Color(0xFFE2E8F0) else Color(0xFF1E293B)
                )
            }
        }
    }
}

// ─── Bottom Status Footer ────────────────────────────────────────────────────
@Composable
private fun InkspaceBottomStatusFooter(
    activeTool: InkingTool,
    activeColor: Color,
    paperStyle: PaperGridStyle
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF0F172A))
            .padding(horizontal = 14.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(activeColor)
            )
            Text(
                text = "${activeTool.label} • ${paperStyle.label}",
                style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                color = Color(0xFF94A3B8)
            )
        }

        Text(
            text = "Skiko GPU Engine // 120 FPS",
            style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp, fontWeight = FontWeight.Bold),
            color = Color(0xFF38BDF8)
        )
    }
}
