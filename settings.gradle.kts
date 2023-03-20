pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
        maven(url = "https://jitpack.io")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
        maven(url = "https://jitpack.io")
    }
}

rootProject.name = "KaKaoImageSearchApp"
include(":app")
include(":core:navigation")
include(":core:preferences")
include(":core:designsystem")
include(":core:model")
include(":core:network")
include(":core:extension")
include(":feature:home")
include(":feature:bookmark")
include(":feature:bookmark2")
