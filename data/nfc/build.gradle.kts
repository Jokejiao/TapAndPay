plugins {
    id("tapandpay.android.library")
    id("tapandpay.android.library.jacoco")
    id("tapandpay.android.hilt")
    id("kotlinx-serialization")
}

android {
    namespace = "com.codelab.tapandpay.data.nfc"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }
}