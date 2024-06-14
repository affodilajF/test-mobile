plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
//    id("dagger.hilt.android.plugin")
    id("kotlin-android")
    id("dagger.hilt.android.plugin")

//    id("androidx.navigation.safeargs.kotlin")
}
kapt {
    correctErrorTypes = true
}

android {
    namespace = "com.example.one"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.one"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

//    def nav_version = "2.4.0" // Atur versi sesuai yang Anda inginkan
    implementation("androidx.navigation:navigation-compose:2.4.0")

    implementation ("androidx.navigation:navigation-fragment-ktx:2.4.0")
    implementation ("androidx.navigation:navigation-ui-ktx:2.4.0")

//    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.0.0-alpha07")

    implementation ("androidx.compose.material:material:1.0.5")

//    official docs
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.2.0-alpha01")

    implementation ("com.google.accompanist:accompanist-swiperefresh:0.18.0")


//
//    implementation ("androidx.compose.foundation:foundation:1.0.5")

    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:okhttp:4.9.1")


//    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    implementation ("com.google.dagger:hilt-android:2.46")
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.firebase.firestore.ktx)
//    implementation ("com.google.dagger:hilt-android:2.44")
//    kapt ("com.google.dagger:hilt-compiler:2.44")

    kapt ("com.google.dagger:hilt-android-compiler:2.46")

    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")

    implementation ("androidx.navigation:navigation-compose:2.7.7")

//    masalah image
//    implementation ("com.google.accompanist:accompanist-coil:0.18.0")
//    implementation ("dev.chrisbanes.accompanist:accompanist-coil:0.19.0")
//    implementation ("io.coil-kt:coil-compose:1.4.0")
    implementation ("io.coil-kt:coil-compose:1.4.0")

//    appbar
    implementation ("androidx.compose.material:material:1.1.0")






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