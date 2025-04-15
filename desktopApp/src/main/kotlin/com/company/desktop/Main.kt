package com.company.desktop

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.company.compose.data.Track
import com.company.compose.ui.CounterPageBy
import com.company.compose.ui.HeroInfo
import com.company.compose.ui.PlayListView
import com.company.compose.ui.PlayerView
import com.company.compose.util.music.PlayList
import com.company.compose.util.music.PlayerController


fun main() = application {
    val playlist = PlayList(
        name = "本地音乐",
        tracks = listOf(
            Track("1", "青花瓷", "周杰伦", "D:/Aria.mp3", 200_000),
            Track("2", "无所谓", "杨坤", "D:/Aria.mp3", 180_000),
            Track("3", "童话", "光良", "D:/Aria.mp3", 240_000)
        )
    )
    val controller = remember { PlayerController() }
    Window(onCloseRequest = ::exitApplication, title = "Compose桌面应用") {
        controller.setPlayList(playlist)
        MaterialTheme (){
            PlayListView(controller)
        }
    }
}