package com.company.compose

class DesktopPlatform : Platform {
    override val name: String = "Desktop 1.0"
}

actual fun getPlatform(): Platform = DesktopPlatform()