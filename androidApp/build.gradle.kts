buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:8.10.1")
    }
}


plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.compose.compiler)
    id ("com.dz.bitmap.plugin")
}

android {
    namespace = "com.company.compose.android"
    compileSdk = 35
    defaultConfig {
        applicationId = "com.company.compose.android"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.6.10"
    }

}

dependencies {
    implementation(projects.shared)
    implementation(libs.ui)
    implementation(libs.compose.material3)
    implementation(libs.activity.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.compose.ui.tooling)
    implementation(libs.activity.compose)
    implementation("androidx.compose.ui:ui-tooling-preview:1.9.0")


}