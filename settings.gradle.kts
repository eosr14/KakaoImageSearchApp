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
include(":core:model")
include(":core:extension")
include(":core:preferences")
include(":core:navigation")
include(":core:designsystem")
include(":core:network")
include(":core:testing")
include(":feature:home")
include(":feature:bookmark")
