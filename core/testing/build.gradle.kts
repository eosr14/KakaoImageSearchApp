plugins {
    id("eosr14.android.library")
    id("com.google.devtools.ksp")
}

dependencies {
    implementation(libs.moshi)
    ksp(libs.moshi.codegen)

    api(libs.junit4)
    api(libs.androidx.test.core)
    api(libs.kotlinx.coroutines.test)
    api(libs.turbine)

    api(libs.androidx.test.espresso.core)
    api(libs.androidx.test.runner)
    api(libs.androidx.test.rules)
    api(libs.hilt.android.testing)
}