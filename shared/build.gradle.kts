plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlinx-serialization")
}

kotlin {
    android()
    
    listOf(
        iosX64(),
        iosArm64(),
        //iosSimulatorArm64() sure all ios dependencies support this target
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                // Ktor
                implementation("io.ktor:ktor-client-core:1.6.7")
                implementation("io.ktor:ktor-client-cio:1.6.7")
                implementation("io.ktor:ktor-client-serialization:1.6.7")

                // Kodein
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
                implementation("org.kodein.di:kodein-di:7.10.0")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                // Ktor
                implementation("io.ktor:ktor-client-core:1.6.7")
                implementation("io.ktor:ktor-client-cio:1.6.7")
                implementation("io.ktor:ktor-client-serialization:1.6.7")

                // Kodein
                implementation("org.kodein.di:kodein-di-framework-android-x:7.10.0")
                implementation("org.kodein.di:kodein-di-framework-android-core:7.10.0")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        //val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            //iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation("io.ktor:ktor-client-ios:1.6.7")
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        //val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            //iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    compileSdk = 31
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 28
        targetSdk = 31
    }
}
dependencies {
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")
}
