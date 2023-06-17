plugins {
    id("nowinandroid.android.library")
    id("nowinandroid.android.library.jacoco")
    kotlin("kapt")
}

android {
    namespace = "com.codelab.tapandpay.domain.nfc"
}

dependencies {
    implementation(libs.hilt.android)
    implementation(libs.kotlinx.coroutines.android)

    kapt(libs.hilt.compiler)
}