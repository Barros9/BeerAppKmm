plugins {
    id("com.android.application")
    id("androidx.navigation.safeargs")
    kotlin("android")
}

android {
    compileSdk = 31
    defaultConfig {
        applicationId = "com.barros9.beerappkmm.android"
        minSdk = 28
        targetSdk = 31
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.5"
    }
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.appcompat:appcompat:1.4.0")
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.2")
    implementation("androidx.appcompat:appcompat:1.4.0")

    // Kodein
    implementation("org.kodein.di:kodein-di-framework-android-x:7.10.0")
    implementation("org.kodein.di:kodein-di-framework-android-core:7.10.0")
    implementation("org.kodein.di:kodein-di-framework-android-x-viewmodel:7.10.0")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")

    // Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.5")

    // Compose
    implementation("androidx.compose.ui:ui:1.0.5")
    implementation("androidx.compose.material:material:1.0.5")
    implementation("androidx.compose.ui:ui-tooling-preview:1.0.5")
    implementation("androidx.compose.runtime:runtime-livedata:1.0.5")

    // Glide
    implementation("com.github.skydoves:landscapist-glide:1.3.7")
}