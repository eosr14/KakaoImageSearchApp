plugins {
    id("eosr14.android.application")
    id("eosr14.android.application.compose")
    id("eosr14.android.hilt")
    id("eosr14.spotless")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.eosr14.kakao.search"
    compileSdk = Configurations.compileSdk

    defaultConfig {
        applicationId = "com.eosr14.kakao.search"
        minSdk = Configurations.minSdk
        targetSdk = Configurations.targetSdk
        versionCode = Configurations.versionCode
        versionName = Configurations.versionName
    }

    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.constraintlayout)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.material3)
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.timber)
    kapt(libs.hilt.compiler)
}