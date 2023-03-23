plugins {
    id("eosr14.android.library")
    id("eosr14.android.library.compose")
    id("eosr14.spotless")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.android")
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(libs.moshi)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    ksp(libs.moshi.codegen)
}
