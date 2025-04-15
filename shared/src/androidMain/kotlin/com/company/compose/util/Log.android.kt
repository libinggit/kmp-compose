package com.company.compose.util

import android.util.Log

class AndroidLog:LogI{
    override fun printLogD(tag: String, content: String) {
        Log.d(tag,content)
    }

    override fun printLogE(tag: String, content: String, exception: Exception) {
        Log.d(tag,content)
        exception.printStackTrace()
    }
}
actual fun getLog(): LogI {
    return AndroidLog()
}