plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    // KSP plugin for Room library
    id("com.google.devtools.ksp")
    // Kotlin serialization plugin for type safe routes and navigation arguments
    kotlin("plugin.serialization") version "2.0.21"
    //[FIREBASE][google_services_plugin]
}

android {
    namespace = "asmrtraders.babycare.asmrlittlesteps"
    compileSdk {
        version = release(36)
    }

    ndkVersion = "29.0.014206865"

    defaultConfig {
        applicationId = "asmrtraders.babycare.asmrlittlesteps"
        minSdk = 29
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {

        //[COMMON][debugBuildType]

        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            //[COMMON][DexFileKey]

            //[APPSFLYER][DevKey]
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
        //[COMMON][buildConfigFlag]
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    //[COMMON][retrofit_dependencies]

    //[APPSFLYER][appsFlyer_dependency]

    //[FIREBASE][firebase_dependencies]

    //[FIREBASE][referrer_dependency]

    //[COMMON][in_app_review_dependency]

    // Fonts
    implementation("androidx.compose.ui:ui-text-google-fonts:1.11.2")

    // Coil
    implementation("io.coil-kt.coil3:coil-compose:3.4.0")
    implementation("io.coil-kt.coil3:coil-network-okhttp:3.4.0")

    // Icons Extended
    implementation("androidx.compose.material:material-icons-extended:1.7.8")

    // ConstraintLayout
    implementation("androidx.constraintlayout:constraintlayout-compose:1.1.1")

    // Compose Navigation
    implementation("androidx.navigation:navigation-compose:2.9.7")

    // Koin
    implementation(platform("io.insert-koin:koin-bom:4.2.0"))
    implementation("io.insert-koin:koin-android")
    implementation("io.insert-koin:koin-compose")
    implementation("io.insert-koin:koin-compose-viewmodel")
    implementation("io.insert-koin:koin-androidx-compose-navigation")
    //[FIREBASE][workmanager_dependency]

    // Room
    ksp("androidx.room:room-compiler:2.8.4")
    implementation("androidx.room:room-runtime:2.8.4")
    implementation("androidx.room:room-ktx:2.8.4")

    // Preferences DataStore
    implementation("androidx.datastore:datastore-preferences:1.2.0")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}