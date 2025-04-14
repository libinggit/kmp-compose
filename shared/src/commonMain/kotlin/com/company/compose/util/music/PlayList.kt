package com.company.compose.util.music

import com.company.compose.data.Track

/**
 * 播放列表。
 */
data class PlayList(
    val name:String,
    val tracks:List<Track>
)
