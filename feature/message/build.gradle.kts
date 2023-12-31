plugins {
    id("tapandpay.android.feature")
    id("tapandpay.android.library.compose")
}

android {
    namespace = "com.codelab.tapandpay.feature.message"
}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(project(":core:data"))
    implementation(project(":domain:smssender"))
}