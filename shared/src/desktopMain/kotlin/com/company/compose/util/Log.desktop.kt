package com.company.compose.util


class DesktopLog:LogI{
    override fun printLogD(tag: String, content: String) {
        println("$tag:$content")
    }

    override fun printLogE(tag: String, content: String, exception: Exception) {
        println("$tag:$content")
        exception.printStackTrace()
    }
}
actual fun getLog(): LogI {
    return DesktopLog()
}