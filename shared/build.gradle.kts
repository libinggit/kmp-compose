import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Locale

plugins {
    alias(libs.plugins.kotlinMultiplatform)
//    kotlin("multiplatform") version "2.0.0"

    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.compose.compiler)
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
