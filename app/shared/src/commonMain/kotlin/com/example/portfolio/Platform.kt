package com.example.portfolio

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform