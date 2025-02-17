// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false

    id("com.android.library") version "8.0.2" apply false
    id("com.google.dagger.hilt.android") version "2.49" apply false // Update to match module-level
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.0" // Update to match your Kotlin version
}