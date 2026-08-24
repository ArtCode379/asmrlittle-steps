// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    // KSP plugin for Room library
    id("com.google.devtools.ksp").version("2.3.6")
    //[FIREBASE][google_services_plugin]
}