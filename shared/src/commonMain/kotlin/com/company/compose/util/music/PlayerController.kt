package com.company.compose.util.music

import com.company.compose.data.Track
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class PlayerController(private val scope: CoroutineScope = CoroutineScope(Dispatchers.Default + SupervisorJob())){
    /**
     * 播放器
     */
    private val audioPlayer = getAudioPlayer()

    private val _playlist = MutableStateFlow<PlayList?>(null)
    val playList:StateFlow<PlayList?> =_playlist.asStateFlow()

    private val _currentIndex=MutableStateFlow<Int?>(null)

    val currentTrack: StateFlow<Track?> = combine(_playlist,_currentIndex){playList,index->
        playList?.tracks?.getOrNull(index?:-1)
    }.stateIn(scope=scope, started = SharingStarted.WhileSubscribed(5000), initialValue = null)

    fun setPlayList(playList: PlayList){
        _playlist.value = playList
        _currentIndex.value = 0
        playList.tracks.firstOrNull()?.let {
            audioPlayer.play(it) }
    }


    fun play(index:Int){
        _currentIndex.value =index
        _playlist.value?.tracks?.getOrNull(index)?.let {
            audioPlayer.play(it)
        }
    }


    fun pause()=audioPlayer.pause()

    fun resume() =audioPlayer.resume()

    fun stop() =audioPlayer.stop()

    fun next(){
        val index =_currentIndex.value?:return

        val nextIndex=index+1

        if(nextIndex< (_playlist.value?.tracks?.size?:0)){
            play(nextIndex)
        }
    }

    fun previous(){
        val index =_currentIndex.value?:return

        val previousIndex=index+1

        if(previousIndex>=0){
            play(previousIndex)
        }
    }


    fun isPlaying():Boolean = audioPlayer.isPlaying()


}