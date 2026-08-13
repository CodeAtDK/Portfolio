package com.example.portfolio.model

import androidx.compose.ui.graphics.Color

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
    val emoji: String,
    val accentColorHex: Long
) {
    data class Feature(
        val icon: String, // emoji
        val title: String,
        val description: String
    )

    data class TechTag(
        val name: String,
        val colorHex: Long
    )
}

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
        CaseStudy.Feature("🍽️", "Real-Time Food Listings", "Allows donors to post surplus food details, quantity, ingredients, and preparation timestamps in seconds for rapid community discovery."),
        CaseStudy.Feature("📍", "Location-Based Matching", "Integrates geo-location services and interactive mapping to connect food donors with the nearest active volunteer drivers and shelter centers."),
        CaseStudy.Feature("🔄", "Firebase Cloud Backend", "Provides instantaneous data synchronization, secure role-based authentication (Donor vs. NGO), and real-time pickup status tracking."),
        CaseStudy.Feature("💛", "Community Impact Dashboard", "Quantifies social contribution by tracking total meals rescued, kilogram weight of prevented food waste, and carbon footprint reduction.")
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
    emoji = "🍽️",
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
        CaseStudy.Feature("🏪", "Agricultural Marketplace", "Enables farmers to list, price, and sell crops directly to consumers and businesses through an easy-to-use mobile catalog without middlemen."),
        CaseStudy.Feature("☀️", "Weather Forecasting", "Provides real-time localized atmospheric conditions, precipitation warnings, and multi-day farming forecasts via OpenWeatherMap integration."),
        CaseStudy.Feature("🤖", "AI Farmer Assistant", "Empowers farmers with instant guidance on soil management, pest control, and optimal harvesting cycles through an embedded smart AI chatbot."),
        CaseStudy.Feature("☁️", "Firebase Cloud Engine", "Manages responsive real-time inventory updates, secure profile credentials, and lightning-fast product media storage through Google Firebase.")
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
    emoji = "🌾",
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
        CaseStudy.Feature("🤖", "AI Symptom Support", "Guides users through initial symptom-related questions and triages health concerns efficiently before connecting to specialists."),
        CaseStudy.Feature("💬", "Real-Time Messaging", "Enables instant doctor-patient communication with real-time syncing and media sharing powered by Firebase Cloud data."),
        CaseStudy.Feature("📹", "Video Consultations", "Supports high-definition live consultation experiences through ZegoCloud integration for seamless remote virtual appointments."),
        CaseStudy.Feature("🔒", "Secure Access", "Uses Firebase Authentication for protected user sign-in, ensuring sensitive personal health data remains strictly confidential.")
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
    emoji = "❤️",
    accentColorHex = 0xFF00DAF3
)

/** Map from project ID to case study data */
val CaseStudyRegistry = mapOf(
    "foodbridge" to FoodBridgeCaseStudy,
    "agriconnect" to AgriConnectCaseStudy,
    "carenest" to CareNestCaseStudy
)
