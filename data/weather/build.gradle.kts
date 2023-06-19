plugins {
    id("tapandpay.android.library")
    id("tapandpay.android.library.jacoco")
    id("tapandpay.android.hilt")
    id("kotlinx-serialization")
}

android {
    namespace = "com.codelab.tapandpay.data.weather"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }
}

dependencies {
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp)
}