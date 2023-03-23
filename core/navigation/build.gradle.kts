plugins {
    id("eosr14.android.library")
    id("eosr14.android.library.compose")
    id("eosr14.android.hilt")
    id("eosr14.spotless")
}

dependencies {
    implementation(libs.androidx.navigation.compose)
}
