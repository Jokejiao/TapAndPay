package com.codelab.tap.navigation

import android.app.Activity
import android.content.Intent
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.codelab.tap.ui.TapScreen


const val tapNavigationRoute = "tap_card"

fun NavGraphBuilder.tapScreen(activity: Activity, intent: Intent, onCardDataAvailable: () -> Unit) {
    composable(tapNavigationRoute) {
        TapScreen(
            activity = activity, intent = intent, onCardDataAvailable = onCardDataAvailable
        )
    }
}