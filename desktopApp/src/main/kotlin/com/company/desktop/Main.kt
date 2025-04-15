package com.company.desktop

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.company.compose.data.Track
import com.company.compose.ui.PlayListView
import com.company.compose.util.music.PlayList
import com.company.compose.util.music.PlayerController


fun main() = application {
    val playlist = PlayList(
        name = "本地音乐",
        tracks = listOf(
            Track("1", "青花瓷", "周杰伦", "C:\\Users\\chowl\\Documents\\WeChat Files\\wxid_t28kq57lksw822\\FileStorage\\File\\2024-11\\2年级上听力全文.mp3", 200_000),
            Track("2", "无所谓", "杨坤", "C:\\Users\\chowl\\Documents\\WeChat Files\\wxid_t28kq57lksw822\\FileStorage\\File\\2024-12\\8月10日 - 2024年.mov", 180_000),
            Track("3", "童话", "光良", "C:\\Users\\chowl\\Documents\\WeChat Files\\wxid_t28kq57lksw822\\FileStorage\\File\\2024-12\\117115_2023秋_小学黄冈小状元作业本英语BJ二年级上2023秋-057-Lesson19听力音频.mp3", 240_000)
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