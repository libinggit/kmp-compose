package com.company.compose.util

interface LogI {
    fun printLogD(tag:String,content:String)
}

expect fun getLog():LogI