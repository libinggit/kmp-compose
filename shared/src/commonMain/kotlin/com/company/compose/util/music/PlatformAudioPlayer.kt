package com.company.compose.util.music

import com.company.compose.data.Track

/**
 * 音乐播放器的抽象类。
 */
interface AudioPlayer {
    fun play(track: Track)
    fun pause()
    fun resume()
    fun stop()
    fun seekTo(position:Long)
    fun getCurrentPosition():Long
    fun isPlaying():Boolean
}


expect fun getAudioPlayer():AudioPlayer