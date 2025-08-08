package com.company.desktop

//import WebsiteManagerUI
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import com.company.compose.db.DatabaseDriverFactory
import com.company.compose.db.SqlDelightDatabase
import com.company.compose.router.AppRouter


fun main() = application {
    SqlDelightDatabase.init(DatabaseDriverFactory()) // 不用传参

    Window(onCloseRequest = ::exitApplication, title = "Compose桌面应用",state = WindowState(width = 1000.dp, height = 800.dp)){
        MaterialTheme (){
            AppRouter()
        }
    }
}