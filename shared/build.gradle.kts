import org.gradle.kotlin.dsl.implementation
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Locale

plugins {
    alias(libs.plugins.kotlinMultiplatform)
//    kotlin("multiplatform") version "2.0.0"

    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.compose.compiler)
    id("com.squareup.sqldelight") // 这里不写 version，也不写 apply false
    kotlin("plugin.serialization") version "1.9.0"

}
sqldelight {
    database("WebsiteDatabase") {
        packageName = "com.company.compose.data"
        sourceFolders = listOf("sqldelight")
    }
}
val osName = System.getProperty("os.name").lowercase(Locale.getDefault())
val platform = when {
    osName.contains("win") -> "win"
    osName.contains("mac") -> "mac"
    osName.contains("nux") -> "linux"
    else -> throw GradleException("Unknown OS: $osName")
}
val javafxVersion = "21"


kotlin {
    androidTarget {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_1_8)
                }
            }
        }
    }
    jvm("desktop"){
//        withJava()
    }
    
//    listOf(
//        iosX64(),
//        iosArm64(),
//        iosSimulatorArm64()
//    ).forEach {
//        it.binaries.framework {
//            baseName = "shared"
//            isStatic = true
//        }
//    }

    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here
            implementation(libs.runtime)
            implementation(libs.compose.foundation)
            // 若需 Material Design 组件
            implementation(libs.compose.material3)
            implementation(libs.sqlite.driver) // sqlite 驱动
            implementation(libs.sqldelight.runtime)       // core runtime
            implementation(libs.coroutines.extensions) // flow 支持
            // Ktor 客户端核心库
            implementation("io.ktor:ktor-client-core:2.3.3")
            implementation("io.ktor:ktor-client-content-negotiation:2.3.3")
            implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.3")
            implementation("io.ktor:ktor-client-cio:2.3.4")        // CIO 引擎依赖

            // jsoup (跨平台) 解析html，如果想用更严谨的html解析器，推荐这个
            implementation("org.jsoup:jsoup:1.16.1")

        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        val desktopMain by getting {
            dependencies {
                implementation(libs.desktop)  // Compose for Desktop
                implementation("org.openjfx:javafx-base:$javafxVersion:$platform")
                implementation("org.openjfx:javafx-graphics:$javafxVersion:$platform")
                implementation("org.openjfx:javafx-media:$javafxVersion:$platform")
                implementation("org.openjfx:javafx-swing:$javafxVersion:$platform")
                implementation("io.ktor:ktor-client-cio:2.3.3")  // Desktop 网络引擎

            }
        }

        val androidMain by getting {
            dependencies {
                implementation("com.squareup.sqldelight:android-driver:1.5.5")
                implementation("io.ktor:ktor-client-okhttp:2.3.3") // Android 网络引擎
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

            }
        }


    }
}

android {
    namespace = "com.company.compose"
    compileSdk = 35
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
