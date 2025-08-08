package com.company.desktop

//import WebsiteManagerUI
import WebsiteManagerUI
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.company.compose.db.DatabaseDriverFactory
import com.company.compose.db.SqlDelightDatabase


fun main() = application {
    SqlDelightDatabase.init(DatabaseDriverFactory()) // 不用传参

    Window(onCloseRequest = ::exitApplication, title = "Compose桌面应用") {
        MaterialTheme (){
            WebsiteManagerUI()
        }
    }
}