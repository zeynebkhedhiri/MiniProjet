import org.gradle.internal.impldep.org.junit.experimental.categories.Categories.CategoryFilter.include


plugins {
        id("com.android.application") version "8.1.1" apply false
        id("org.jetbrains.kotlin.android") version "1.9.10" apply false
        id("com.google.gms.google-services") version "4.4.0" apply false
    }


    buildscript {
        repositories {
            google()
            mavenCentral()
        }
        dependencies {
            classpath ("com.android.tools.build:gradle:8.1.1") // Use the correct version
            classpath ("com.google.gms:google-services:4.4.0")
        }

    }



