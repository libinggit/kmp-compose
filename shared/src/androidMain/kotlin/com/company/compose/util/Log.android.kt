package com.company.compose.util

import android.util.Log

class AndroidLog:LogI{
    override fun printLogD(tag: String, content: String) {
        Log.d(tag,content)
    }
}
actual fun getLog(): LogI {
    return AndroidLog()
}