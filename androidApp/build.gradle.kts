plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("androidx.compose.foundation:foundation:1.0.4")
    implementation("androidx.compose.ui:ui:1.0.4")
    implementation("androidx.activity:activity-compose:1.3.1")
    implementation("androidx.compose.material:material:1.0.4")
}

android {
    compileSdkVersion(31)
    defaultConfig {
        applicationId = "com.vinks.iosflowtest.android"
        minSdkVersion(23)
        targetSdkVersion(31)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        compose = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.0-alpha06" //Versions.Compose.compose
    }
}