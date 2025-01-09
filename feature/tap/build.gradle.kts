plugins {
    id("tapandpay.android.feature")
    id("tapandpay.android.library.compose")
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.codelab.tapandpay.feature.tap"
}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(project(":data:nfc"))
    implementation(project(":domain:nfcreader"))
}