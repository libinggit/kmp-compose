plugins {
//    id ("application")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.jetbrainsCompose)
//    application
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}

dependencies {
    implementation(compose.desktop.currentOs)

    implementation(projects.shared)
    implementation(libs.desktop) // 或更高版本
    implementation(libs.ui) // 确保UI库的版本匹配
    implementation(libs.runtime)
    implementation(libs.compose.material3)

}
//
//application {
//    mainClass.set("com.company.desktop.MainKt") // 你的主类
//}

compose.desktop {
    application {
        mainClass = "com.company.desktop.MainKt" // 替换成你的主类
        nativeDistributions {
            targetFormats(org.jetbrains.compose.desktop.application.dsl.TargetFormat.Exe,
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Dmg,
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Deb,
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.AppImage)
            packageName = "MyApp"
            packageVersion = "1.0.1"

            windows {
                iconFile.set(project.file("src/main/resources/icon.ico"))
            }
            macOS {
                iconFile.set(project.file("src/main/resources/icon.icns"))
            }
            linux {
                iconFile.set(project.file("src/main/resources/icon.png"))
            }
        }
    }
}


