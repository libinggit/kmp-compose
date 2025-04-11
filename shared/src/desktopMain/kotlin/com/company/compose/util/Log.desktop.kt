package com.company.compose.util


class DesktopLog:LogI{
    override fun printLogD(tag: String, content: String) {
        println("$tag:$content")
    }
}
actual fun getLog(): LogI {
    return DesktopLog()
}