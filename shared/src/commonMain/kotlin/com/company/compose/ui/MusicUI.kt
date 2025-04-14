package com.company.compose.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.company.compose.util.music.PlayerController

@Composable
fun PlayerView(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column (modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally){
            Text(text = "没有播放的音乐")
            Spacer(modifier = Modifier.height(8.dp))

            Row {
                Button(onClick = {

                }){
                    Text("暂停")
                }

                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {

                }){
                    Text("播放")
                }
            }
        }
    }
}

@Composable
fun PlayListView(controller: PlayerController){
    val playList by controller.playList.collectAsState()
    val currentTrack by controller.currentTrack.collectAsState()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column (modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally){
            Text(text = "播放列表:${playList?.name?:"列表为空"}", fontSize = 20.sp)

            Spacer(modifier = Modifier.height(8.dp))
            playList?.tracks?.forEachIndexed { index, track ->
                val isPlaying =track==currentTrack
                Row {
                    Button(onClick = {
                        controller.play(index)
                    }){
                        Text(text = "${track.title}-${track.artist}")
                    }

                    if(isPlaying){
                        Text("正在播放", modifier = Modifier.padding(start = 8.dp))
                    }
                }
            }
        }
    }
}