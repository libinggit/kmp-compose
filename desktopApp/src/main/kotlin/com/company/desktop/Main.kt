package com.company.desktop

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.company.compose.ui.CounterPageBy


fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Compose桌面应用") {
        MaterialTheme {
            CounterPageBy()
        }
    }
}