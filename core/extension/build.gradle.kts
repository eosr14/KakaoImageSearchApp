plugins {
    id("eosr14.android.library")
    id("eosr14.spotless")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.android")
}

dependencies {
    implementation(libs.moshi)
    implementation(libs.moshi.adapter)
    implementation(libs.timber)
    ksp(libs.moshi.codegen)
}