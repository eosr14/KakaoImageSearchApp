plugins {
    id("eosr14.android.library")
    id("eosr14.android.library.compose")
    id("eosr14.android.hilt")
    id("eosr14.spotless")
}

dependencies {
    implementation(project(":core:extension"))

    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material.iconsExtended)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.constraintlayout)
    implementation(libs.timber)
    implementation(libs.hilt.android)
    api(libs.androidx.compose.material)
    api(libs.androidx.compose.material3)
    api(libs.coil.kt)
    api(libs.coil.kt.compose)
    kapt(libs.hilt.compiler)
}
