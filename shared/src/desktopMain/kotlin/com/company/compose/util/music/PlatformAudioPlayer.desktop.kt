package com.company.compose.util.music

import com.company.compose.data.Track
import com.company.compose.util.getLog

class DesktopPlatformAudioPlayer:AudioPlayer {
    private val TAG = "PlatformAudioPlayer"
    private val log = getLog()
    override fun play(track: Track) {
        log.printLogD(TAG,"play")
    }

    override fun pause() {
        log.printLogD(TAG,"pause")
    }

    override fun resume() {
        log.printLogD(TAG,"resume")
    }

    override fun stop() {
        log.printLogD(TAG,"stop")
    }

    override fun seekTo(position: Long) {
        log.printLogD(TAG,"seekTo")
    }

    override fun getCurrentPosition(): Long {
        log.printLogD(TAG,"getCurrentPosition")
        return 0
    }

    override fun isPlaying(): Boolean {
        log.printLogD(TAG,"isPlaying")
        return false
    }
}

actual fun getAudioPlayer():AudioPlayer = DesktopPlatformAudioPlayer()