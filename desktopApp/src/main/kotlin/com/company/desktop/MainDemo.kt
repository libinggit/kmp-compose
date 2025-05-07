package com.company.desktop

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.company.compose.ui.NestedScrollDemo


fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Demo应用") {
        MaterialTheme (){
            NestedScrollDemo()
        }
    }
}