package com.company.compose.util.music

import com.company.compose.data.Track
import com.company.compose.util.getLog
import javafx.application.Platform
import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer
import javafx.util.Duration
import java.io.File


class DesktopPlatformAudioPlayer:AudioPlayer {

    private var mediaPlayer:MediaPlayer?=null

    init {
        Platform.startup {  }
    }

    private val TAG = "PlatformAudioPlayer"
    private val log = getLog()
    override fun play(track: Track) {
        log.printLogD(TAG,"play")
        stop()
        val media = if(track.url.startsWith("http")){
            Media(track.url)
        }else{
            val file = File(track.url)
            Media(file.toURI().toString())
        }
        mediaPlayer = MediaPlayer(media)
        mediaPlayer?.play()
    }

    override fun pause() {
        log.printLogD(TAG,"pause")
        mediaPlayer?.pause()
    }

    override fun resume() {
        log.printLogD(TAG,"resume")
        mediaPlayer?.play()
    }

    override fun stop() {
        log.printLogD(TAG,"stop")
        mediaPlayer?.stop()
        mediaPlayer?.dispose()
        mediaPlayer =null
    }

    override fun seekTo(position: Long) {
        log.printLogD(TAG,"seekTo")
        mediaPlayer?.seek(Duration.millis(position.toDouble()))
    }

    override fun getCurrentPosition(): Long {
        log.printLogD(TAG,"getCurrentPosition")
        return mediaPlayer?.currentTime?.toMillis()?.toLong()?:-1
    }

    override fun isPlaying(): Boolean {
        log.printLogD(TAG,"isPlaying")
        return mediaPlayer?.status==MediaPlayer.Status.PLAYING
    }
}

actual fun getAudioPlayer():AudioPlayer = DesktopPlatformAudioPlayer()