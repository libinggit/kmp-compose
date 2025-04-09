package com.company.compose

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform