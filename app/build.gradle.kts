import com.codelab.tapandpay.TapBuildType

plugins {
    id("tapandpay.android.application")
    id("tapandpay.android.application.compose")
    id("tapandpay.android.hilt")
    id("tapandpay.android.application.flavors")
}

android {
    defaultConfig {
        applicationId = "com.codelab.tapandpay"
        versionCode = 1
        versionName = "1.0.0" // X.Y.Z; X = Major, Y = minor, Z = Patch level
    }

    buildTypes {
        debug {
            applicationIdSuffix = TapBuildType.DEBUG.applicationIdSuffix
        }
    }

    namespace = "com.codelab.tapandpay"
}

dependencies {
    implementation(project(":feature:tap"))
    implementation(project(":feature:weather"))
    implementation(project(":feature:message"))
    implementation(project(":core:data"))

    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.compose.material3)
}