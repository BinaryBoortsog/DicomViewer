// Top-level build.gradle file

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.4.0") // Adjust version as needed
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.0") // Adjust version as needed
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
}
