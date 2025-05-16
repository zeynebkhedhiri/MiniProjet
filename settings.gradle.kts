pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS) // 🔵 Bien écrit ici
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MiniProjet"
include(":app")
