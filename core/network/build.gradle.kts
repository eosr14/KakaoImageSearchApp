plugins {
    id("eosr14.android.library")
    id("eosr14.android.hilt")
    id("eosr14.spotless")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.android")
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:designsystem"))
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.moshi)
    implementation(libs.moshi.adapter)
    ksp(libs.moshi.codegen)
}