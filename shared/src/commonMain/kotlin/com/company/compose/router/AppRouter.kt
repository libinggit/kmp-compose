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

    when (val screen = currentScreen)  {
        is Screen.WebsiteManager -> WebsiteManagerUI(
            onNavigateToVideoSearch = { currentScreen = Screen.VideoSearchPage }
        )

        is Screen.VideoSearchPage -> VideoSearchUI(
            onNavigateBack = { currentScreen = Screen.WebsiteManager },
            onNavigateToVideoList = { website ->
                currentScreen = Screen.VideoListPage(website)
            },
        )

        is Screen.VideoListPage -> VideoListPage(
            website = screen.website,
            onNavigateBack = {
                currentScreen = Screen.VideoSearchPage
            }
        )

    }
}
