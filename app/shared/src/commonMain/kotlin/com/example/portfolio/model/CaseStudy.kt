package com.example.portfolio.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.*
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Data model for project case studies displayed in-app.
 */
data class CaseStudy(
    val id: String,
    val title: String,
    val tagline: String,
    val category: String,
    val categoryColor: Long, // Color as ARGB long
    val description: String,
    val challenge: String,
    val solution: String,
    val features: List<Feature>,
    val techStack: List<TechTag>,
    val githubUrl: String?,
    val accentColorHex: Long,
    val liveDemoUrl: String? = null,
    val playStoreUrl: String? = null,
    val role: String? = null
) {
    data class Feature(
        val title: String,
        val description: String,
        val icon: ImageVector = Icons.Default.Star
    )

    data class TechTag(
        val name: String,
        val colorHex: Long
    )
}

// ─── Inkspace Notebook ──────────────────────────────────────────────────
val InkspaceNotebookCaseStudy = CaseStudy(
    id = "inkspace",
    title = "Inkspace Notebook",
    tagline = "Cross-Platform Digital Inking, Multi-Page PDF & Cloud Studio • Sub-8ms inking latency, 95%+ shared codebase, 100% offline-first sync.",
    category = "Cross-Platform Studio & Inking Engine",
    categoryColor = 0xFF3B82F6,
    role = "Lead Mobile & Multiplatform Engineer",
    liveDemoUrl = "https://notes.dhruva.website",
    playStoreUrl = "https://play.google.com/store/apps/details?id=dev.dhruva.notebook",
    githubUrl = "https://github.com/CodeAtDK/NotesApplications",
    description = "Inkspace Notebook is a professional, GoodNotes 5-grade digital handwriting and document annotation suite built from the ground up on Kotlin Multiplatform. Features 11 inking tools, responsive 3-tier toolbar, audio-synchronized note recording, multi-page PDF annotation, and real-time cloud synchronization across Android, iOS, Web, and Desktop.",
    challenge = "Traditional digital handwriting apps like GoodNotes and Notability are locked into the Apple ecosystem, leaving Android, Web, and Desktop users with fragmented, high-latency alternatives that lack real-time multi-device cloud synchronization and offline durability.",
    solution = "Inkspace Notebook delivers an uncompromised zero-latency digital notebook experience across Android, iOS, Web browsers, and Desktop from a single shared Kotlin codebase—featuring GPU-accelerated Skiko vector handwriting, time-stamped audio sync, multi-page PDF annotation, and Supabase offline-first cloud synchronization.",
    features = listOf(
        CaseStudy.Feature(
            title = "Sub-8ms Inking Engine",
            description = "GPU-accelerated Skiko Canvas with quadratic Bézier smoothing, dynamic velocity taper, and pressure curve interpolation.",
            icon = Icons.Default.Edit
        ),
        CaseStudy.Feature(
            title = "11 Precision Inking Tools",
            description = "Fountain Pen, Brush, Ballpoint, Eraser with stroke split, Highlighter, Shape detection, Lasso select, Stickers, and Laser pointer.",
            icon = Icons.Default.Brush
        ),
        CaseStudy.Feature(
            title = "Responsive 3-Tier Toolbar",
            description = "Adaptive tool selection, custom color palettes, stroke width presets, and secondary quick-action trays.",
            icon = Icons.Default.Tune
        ),
        CaseStudy.Feature(
            title = "Audio-Synchronized Note Recording",
            description = "Record lectures or meetings while taking notes; audio timestamps dynamically correlate with written strokes for synced playback.",
            icon = Icons.Default.Mic
        ),
        CaseStudy.Feature(
            title = "Multi-Page PDF Annotation",
            description = "High-fidelity vector PDF rendering, split-screen viewing, margin annotations, and page organizer.",
            icon = Icons.AutoMirrored.Filled.MenuBook
        ),
        CaseStudy.Feature(
            title = "Real-Time Cloud Synchronization",
            description = "100% offline-first architecture with Supabase real-time sync, SQLDelight caching, and 3-way logical clock conflict resolution.",
            icon = Icons.Default.Sync
        )
    ),
    techStack = listOf(
        CaseStudy.TechTag("Kotlin Multiplatform", 0xFF3B82F6),
        CaseStudy.TechTag("Compose Multiplatform", 0xFF3B82F6),
        CaseStudy.TechTag("Supabase", 0xFF4CAF50),
        CaseStudy.TechTag("SQLDelight SQLite", 0xFFBBC3FF),
        CaseStudy.TechTag("Skiko Canvas", 0xFF00DAF3),
        CaseStudy.TechTag("Ktor", 0xFFCFBCFF)
    ),
    accentColorHex = 0xFF3B82F6
)

// ─── FoodBridge ──────────────────────────────────────────────────────────
val FoodBridgeCaseStudy = CaseStudy(
    id = "foodbridge",
    title = "FoodBridge",
    tagline = "Connecting food donors with NGOs and vulnerable communities to eliminate food waste.",
    category = "Social Impact Platform",
    categoryColor = 0xFFFF9E44,
    description = "FoodBridge is an Android application designed to make surplus food recovery fast and effortless by bridging the gap between restaurant donors, local NGOs, and volunteers through real-time location matching.",
    challenge = "Vast amounts of fresh, edible food from restaurants and events go to waste daily simply because there isn't a frictionless, real-time communication bridge to alert nearby shelters and volunteers before food spoils.",
    solution = "FoodBridge solves logistics friction by offering a responsive Android platform where donors broadcast surplus meals instantly, and location-based algorithms alert nearby verified NGOs to coordinate immediate food rescue.",
    features = listOf(
        CaseStudy.Feature(
            title = "Real-Time Food Broadcast",
            description = "Donors can post surplus meals in under 30 seconds with photos, shelf life indicators, and pickup windows.",
            icon = Icons.Default.Share
        ),
        CaseStudy.Feature(
            title = "Location-Based Rescue Matching",
            description = "Automated geo-fence alerts notifying nearby verified food shelters and volunteers within a 5km radius.",
            icon = Icons.Default.Place
        ),
        CaseStudy.Feature(
            title = "Surplus Marketplace",
            description = "Discounted surplus items catalog for community members to purchase edible food before disposal.",
            icon = Icons.Default.ShoppingBag
        ),
        CaseStudy.Feature(
            title = "Volunteer Dispatch Routing",
            description = "Turn-by-turn routing with Google Maps API for real-time delivery tracking and safety check-ins.",
            icon = Icons.Default.DirectionsCar
        )
    ),
    techStack = listOf(
        CaseStudy.TechTag("Kotlin", 0xFFFF9E44),
        CaseStudy.TechTag("Jetpack Compose", 0xFFFF9E44),
        CaseStudy.TechTag("Google Maps API", 0xFFBBC3FF),
        CaseStudy.TechTag("Location Services", 0xFFBBC3FF),
        CaseStudy.TechTag("Firebase Auth", 0xFFCFBCFF),
        CaseStudy.TechTag("Cloud Firestore", 0xFFCFBCFF),
        CaseStudy.TechTag("Coroutines & Flow", 0xFF4CAF50),
        CaseStudy.TechTag("MVVM Architecture", 0xFF00DAF3),
        CaseStudy.TechTag("Material 3 Design", 0xFFE6EDF3)
    ),
    githubUrl = "https://github.com/CodeAtDK/FoodBridge",
    accentColorHex = 0xFFFF9E44
)

// ─── Agri Connect ────────────────────────────────────────────────────────
val AgriConnectCaseStudy = CaseStudy(
    id = "agriconnect",
    title = "Agri Connect",
    tagline = "Connecting farmers with buyers, weather intelligence, and AI-powered support.",
    category = "Agriculture Technology",
    categoryColor = 0xFF4CAF50,
    description = "Agri Connect is an Android application designed to support farmers by making it easier to buy and sell agricultural products, check weather conditions, and receive helpful answers through an integrated AI assistant.",
    challenge = "Farmers often need simple, accessible tools to connect with buyers without middlemen, understand changing weather conditions, and get quick technical advice when making crucial daily decisions.",
    solution = "Agri Connect combines a direct farm-to-consumer marketplace flow, live weather forecasting from OpenWeatherMap, Firebase cloud data, and a conversational AI assistant into one unified platform.",
    features = listOf(
        CaseStudy.Feature(
            title = "Direct Farmer Marketplace",
            description = "Transparent crop catalog and direct messaging to connect producers with buyers without middlemen.",
            icon = Icons.Default.Storefront
        ),
        CaseStudy.Feature(
            title = "OpenWeatherMap Forecasting",
            description = "Hyper-local weather metrics, rain alerts, and 7-day precipitation forecasts for crop planning.",
            icon = Icons.Default.WbSunny
        ),
        CaseStudy.Feature(
            title = "Gemini Crop AI Assistant",
            description = "Conversational agronomy advice providing diagnosis for plant diseases and soil maintenance tips.",
            icon = Icons.Default.Psychology
        ),
        CaseStudy.Feature(
            title = "Offline Room Database",
            description = "Local SQLite caching ensuring farmers can review order books and prices even in low-connectivity areas.",
            icon = Icons.Default.Storage
        )
    ),
    techStack = listOf(
        CaseStudy.TechTag("Kotlin", 0xFF4CAF50),
        CaseStudy.TechTag("MVVM Architecture", 0xFF4CAF50),
        CaseStudy.TechTag("OpenWeatherMap API", 0xFF00DAF3),
        CaseStudy.TechTag("Retrofit", 0xFF00DAF3),
        CaseStudy.TechTag("Room Database", 0xFFBBC3FF),
        CaseStudy.TechTag("Coroutines & Flow", 0xFFBBC3FF),
        CaseStudy.TechTag("Firebase Auth", 0xFFCFBCFF),
        CaseStudy.TechTag("Firebase Storage", 0xFFCFBCFF),
        CaseStudy.TechTag("Gemini AI", 0xFFFFC107),
        CaseStudy.TechTag("Material 3 Design", 0xFFE6EDF3)
    ),
    githubUrl = "https://github.com/CodeAtDK/AGRI-CONNECT",
    accentColorHex = 0xFF4CAF50
)

// ─── CareNest ────────────────────────────────────────────────────────────
val CareNestCaseStudy = CaseStudy(
    id = "carenest",
    title = "CareNest",
    tagline = "A connected healthcare-assistance experience for support, communication, and consultations.",
    category = "Healthcare Technology",
    categoryColor = 0xFF00DAF3,
    description = "CareNest is an Android application built to bring symptom support, secure access, real-time doctor-patient communication, and video consultations into one mobile experience.",
    challenge = "Healthcare communication can be fragmented. Users need a simpler way to access support, communicate securely, and move seamlessly from an initial health concern to a real conversation or consultation.",
    solution = "CareNest brings together intuitive symptom support, Firebase-based real-time chat, robust authentication, and instant video consultation capabilities into a focused, modern Android experience.",
    features = listOf(
        CaseStudy.Feature(
            title = "AI Symptom Triage",
            description = "Interactive health assessment questionnaire powered by Google Gemini AI for pre-consultation insights.",
            icon = Icons.Default.Healing
        ),
        CaseStudy.Feature(
            title = "ZegoCloud Video Calls",
            description = "Encrypted, low-latency WebRTC video consultations with certified specialists.",
            icon = Icons.Default.Videocam
        ),
        CaseStudy.Feature(
            title = "Real-Time Patient Chat",
            description = "Instant messaging with attachments, prescription viewing, and consultation history.",
            icon = Icons.AutoMirrored.Filled.Chat
        ),
        CaseStudy.Feature(
            title = "Secure Health Records",
            description = "Encrypted storage on Firebase for lab reports, medication schedules, and clinical notes.",
            icon = Icons.Default.Security
        )
    ),
    techStack = listOf(
        CaseStudy.TechTag("Kotlin", 0xFFBBC3FF),
        CaseStudy.TechTag("MVVM Architecture", 0xFFBBC3FF),
        CaseStudy.TechTag("Kotlin Coroutines", 0xFFBBC3FF),
        CaseStudy.TechTag("Firebase Auth", 0xFFCFBCFF),
        CaseStudy.TechTag("Firebase Realtime DB", 0xFFCFBCFF),
        CaseStudy.TechTag("Cloud Firestore", 0xFFCFBCFF),
        CaseStudy.TechTag("Firebase Storage", 0xFFCFBCFF),
        CaseStudy.TechTag("ZegoCloud", 0xFF00DAF3),
        CaseStudy.TechTag("Gemini AI", 0xFF00DAF3),
        CaseStudy.TechTag("Retrofit", 0xFFE6EDF3)
    ),
    githubUrl = "https://github.com/CodeAtDK/CareNest",
    accentColorHex = 0xFF00DAF3
)

/** Map from project ID to case study data */
val CaseStudyRegistry = mapOf(
    "inkspace" to InkspaceNotebookCaseStudy,
    "inkspace-notebook" to InkspaceNotebookCaseStudy,
    "foodbridge" to FoodBridgeCaseStudy,
    "agriconnect" to AgriConnectCaseStudy,
    "carenest" to CareNestCaseStudy
)
