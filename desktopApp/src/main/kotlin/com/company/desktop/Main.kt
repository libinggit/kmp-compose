package com.company.desktop
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.window.application
import androidx.compose.ui.window.Window
import com.company.compose.Greeting


fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Compose Desktop App") {
        Text(Greeting().greet())
    }
}