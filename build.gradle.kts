plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidApplication).apply(false)
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    alias(libs.plugins.compose.compiler).apply(false)
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
}
repositories {
    google()         // 用于 AndroidX 库
    mavenCentral()   // 用于 Kotlin 和 Compose 多平台库
    // JetBrains 仓库（部分 Compose 组件需要）
    maven { url=uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }

}
