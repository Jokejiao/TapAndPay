plugins {
    id("tapandpay.android.library")
    id("tapandpay.android.hilt")
    id("kotlinx-serialization")
}

android {
    namespace = "com.codelab.tapandpay.domain.nfcreader"
}

dependencies {
    implementation(libs.guava.android)
    implementation(project(":data:nfc"))
}