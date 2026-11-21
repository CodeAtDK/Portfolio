package com.example.portfolio.data.remote

import io.ktor.client.HttpClient

actual fun createPlatformHttpClient(): HttpClient = HttpClient()
