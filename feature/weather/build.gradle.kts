plugins {
    id("tapandpay.android.feature")
    id("tapandpay.android.library.compose")
}

android {
    namespace = "com.codelab.tapandpay.feature.weather"
}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(project(":data:weather"))
}