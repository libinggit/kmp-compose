package com.company.compose.router

import VideoSearchUI
import WebsiteManagerUI
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.company.compose.ui.VideoListPage

@Composable
fun AppRouter() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.WebsiteManager) }
    var lastScreen: Screen? = Screen.WebsiteManager
    when (val screen = currentScreen)  {
        is Screen.WebsiteManager -> WebsiteManagerUI(
            onNavigateToVideoSearch = { currentScreen = Screen.VideoSearchPage },
            onNavigateToVideoList = { website ->
                currentScreen = Screen.VideoListPage(website)
                lastScreen = currentScreen
            },
        )

        is Screen.VideoSearchPage -> VideoSearchUI(
            onNavigateBack = { currentScreen = lastScreen!! },
            onNavigateToVideoList = { website ->
                currentScreen = Screen.VideoListPage(website)
                lastScreen = currentScreen
            },
        )

        is Screen.VideoListPage -> VideoListPage(
            website = screen.website,
            onNavigateBack = {
                currentScreen = lastScreen!!
            }
        )

    }
}
