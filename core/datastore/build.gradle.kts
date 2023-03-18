plugins {
    id("eosr14.android.library")
    id("eosr14.android.feature")
    id("eosr14.android.hilt")
    id("eosr14.spotless")
}

dependencies {
    implementation(project(":core:model"))
    implementation(libs.androidx.room.ktx)
    kapt(libs.androidx.room.compiler)
}