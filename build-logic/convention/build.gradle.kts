plugins {
    `kotlin-dsl`
}

group = "com.eosr14.example.deliveryapp.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.spotless.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "eosr14.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidApplication") {
            id = "eosr14.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "eosr14.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "eosr14.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidFeature") {
            id = "eosr14.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
      register("androidHilt") {
        id = "eosr14.android.hilt"
        implementationClass = "AndroidHiltConventionPlugin"
      }
        register("spotless") {
            id = "eosr14.spotless"
            implementationClass = "SpotlessConventionPlugin"
        }
    }
}