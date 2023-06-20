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

//    testOptions {
//        unitTests {
//            isIncludeAndroidResources = true
//        }
//    }
    namespace = "com.codelab.tapandpay"
}

dependencies {
    implementation(project(":feature:tap"))
    implementation(project(":feature:weather"))
    implementation(project(":feature:message"))
    implementation(project(":core:data"))
    implementation(project(":domain:nfcreader"))

//    androidTestImplementation(libs.androidx.navigation.testing)
//    androidTestImplementation(libs.accompanist.testharness)
//    androidTestImplementation(kotlin("test"))
//    debugImplementation(libs.androidx.compose.ui.testManifest)
//    debugImplementation(project(":ui-test-hilt-manifest"))

    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.compose.runtime.tracing)
    implementation(libs.androidx.compose.material3.windowSizeClass)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.window.manager)
    implementation(libs.androidx.profileinstaller)
    implementation(libs.coil.kt)

    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.runtime.livedata)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.ui.util)
    api(libs.androidx.metrics)
    api(libs.androidx.tracing.ktx)
}

// androidx.test is forcing JUnit, 4.12. This forces it to use 4.13
configurations.configureEach {
    resolutionStrategy {
        force(libs.junit4)
        // Temporary workaround for https://issuetracker.google.com/174733673
        force("org.objenesis:objenesis:2.6")
    }
}
