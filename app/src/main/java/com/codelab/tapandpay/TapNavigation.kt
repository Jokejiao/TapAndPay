package com.codelab.tapandpay

import androidx.navigation.NavController
import com.codelab.tapandpay.TapScreens.DATA_SCREEN
import com.codelab.tapandpay.TapScreens.MESSAGE_SCREEN
import com.codelab.tapandpay.TapScreens.TAP_SCREEN


/**
 * Screens used in [TodoDestinations]
 */
private object TapScreens {
    const val TAP_SCREEN = "tap_card"
    const val DATA_SCREEN = "data_display"
    const val MESSAGE_SCREEN = "message_user"
}

/**
 * Arguments used in [TodoDestinations] routes
 */
object TodoDestinationsArgs {
    const val USER_MESSAGE_ARG = "userMessage"
    const val TASK_ID_ARG = "taskId"
    const val TITLE_ARG = "title"
}

/**
 * Destinations used in the [TodoActivity]
 */
object TapDestinations {
    const val TAP_ROUTE = TAP_SCREEN
    const val DATA_ROUTE = DATA_SCREEN
    const val MESSAGE_ROUTE = MESSAGE_SCREEN
}

var currentRoute: String? = TapDestinations.TAP_ROUTE

class TapNavigationActions(private val navController: NavController) {

    fun navigateToData() {
        navController.navigate(TapDestinations.DATA_ROUTE)
        currentRoute = TapDestinations.DATA_ROUTE
    }

    fun navigateToMessage() {
        navController.navigate(TapDestinations.MESSAGE_ROUTE)
        currentRoute = TapDestinations.MESSAGE_ROUTE
    }
}