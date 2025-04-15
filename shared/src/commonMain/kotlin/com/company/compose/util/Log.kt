package com.company.compose.util

interface LogI {
    fun printLogD(tag:String,content:String)
    fun printLogE(tag:String,content:String,exception: Exception)
}

expect fun getLog():LogI