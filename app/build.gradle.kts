plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.21"
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "fr.yjk.mobility.health"
    compileSdk = 35

    defaultConfig {
        applicationId = "fr.yjk.mobility.health"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    val room_version = "2.6.1"

    //noinspection UseTomlInstead
    implementation("androidx.room:room-runtime:$room_version")
    //noinspection UseTomlInstead
    annotationProcessor("androidx.room:room-compiler:$room_version")
    //noinspection UseTomlInstead
    ksp("androidx.room:room-compiler:$room_version")
    //noinspection UseTomlInstead
    implementation("androidx.room:room-ktx:$room_version")

    //noinspection UseTomlInstead
    implementation("com.google.dagger:hilt-android:2.52")
    //noinspection UseTomlInstead
    ksp("com.google.dagger:hilt-android-compiler:2.52")


    //noinspection UseTomlInstead
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")

    //noinspection UseTomlInstead
    implementation("androidx.navigation:navigation-compose:2.8.5")
    //noinspection UseTomlInstead
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    //noinspection UseTomlInstead
    implementation("androidx.core:core-splashscreen:1.0.1")

    //noinspection UseTomlInstead
    implementation("androidx.datastore:datastore-preferences:1.1.2")

    implementation("io.coil-kt:coil-compose:2.7.0")
    // Kotlin serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}