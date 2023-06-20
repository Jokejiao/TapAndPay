plugins {
    id("tapandpay.android.library")
    id("tapandpay.android.hilt")
    id("kotlinx-serialization")
    alias(libs.plugins.secrets)
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "com.codelab.tapandpay.data.weather"
}

dependencies {
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp)
}