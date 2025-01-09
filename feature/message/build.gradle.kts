plugins {
    id("tapandpay.android.feature")
    id("tapandpay.android.library.compose")
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.codelab.tapandpay.feature.message"
}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(project(":core:data"))
    implementation(project(":domain:smssender"))
}