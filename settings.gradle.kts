pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "TapAndPay"
include(":app")
include(":feature:tap")
include(":data:nfc")
include(":domain:nfcreader")
include(":feature:weather")
include(":data:weather")
include(":feature:message")
include(":core:data")
include(":domain:smssender")
