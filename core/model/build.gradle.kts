plugins {
    id("eosr14.android.library")
    id("eosr14.spotless")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.android")
}

dependencies {
    implementation(project(":core:model"))
    implementation(libs.moshi)
    ksp(libs.moshi.codegen)
}
