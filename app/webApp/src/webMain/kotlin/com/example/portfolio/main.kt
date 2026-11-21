package com.example.portfolio

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    val loading = document.getElementById("loading")
    loading?.remove()
    ComposeViewport(viewportContainerId = "root") {
        App()
    }
}
