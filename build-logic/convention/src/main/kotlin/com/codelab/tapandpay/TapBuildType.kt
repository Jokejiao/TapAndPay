package com.codelab.tapandpay


@Suppress("unused")
enum class TapBuildType(val applicationIdSuffix: String? = null) {
    DEBUG(".debug"),
    RELEASE,
}
