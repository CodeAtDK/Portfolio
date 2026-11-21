package com.example.portfolio.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlinx.serialization.Serializable

@Serializable
data class FormspreeRequest(
    val name: String,
    val email: String,
    val message: String,
    val _subject: String = "New message from KMP Portfolio 🚀"
)

object ContactService {
    private const val FORMSPREE_ENDPOINT = "https://formspree.io/f/xyegykdg"
    private const val PROXY_ENDPOINT = "/api/contact"

    suspend fun sendMessage(
        name: String,
        email: String,
        message: String,
        client: HttpClient = HttpClientProvider.client
    ): Result<Boolean> {
        val payload = FormspreeRequest(
            name = name.trim(),
            email = email.trim(),
            message = message.trim()
        )

        // 1. Primary: Direct Formspree submission
        try {
            val response = client.post(FORMSPREE_ENDPOINT) {
                contentType(ContentType.Application.Json)
                header("Accept", "application/json")
                setBody(payload)
            }
            if (response.status.isSuccess()) {
                return Result.success(true)
            }
        } catch (_: Exception) {
            // Direct submission encountered network or browser ad-blocker restriction, try serverless proxy
        }

        // 2. Secondary fallback: Same-origin serverless proxy (/api/contact)
        return try {
            val response = client.post(PROXY_ENDPOINT) {
                contentType(ContentType.Application.Json)
                header("Accept", "application/json")
                setBody(payload)
            }
            if (response.status.isSuccess()) {
                Result.success(true)
            } else {
                Result.failure(Exception("Message could not be delivered (status ${response.status.value})."))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

