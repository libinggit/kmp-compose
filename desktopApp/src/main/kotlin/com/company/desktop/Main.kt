package com.company.desktop

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.company.compose.ui.CounterPage


fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Compose Desktop App") {
        MaterialTheme {
            CounterPage()
        }
    }
}