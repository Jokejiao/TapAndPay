package com.codelab.tapandpay


import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codelab.dataholder.DataHolder
import com.codelab.message.MessageScreen
import com.codelab.tap.TapScreen
import kotlinx.coroutines.CoroutineScope


@Composable
fun TapNavGraph(
    activity: MainActivity,
    intent: Intent? = null,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    startDestination: String = TapDestinations.TAP_ROUTE,
//    startDestination: String = TapDestinations.DATA_ROUTE,
    navActions: TapNavigationActions = remember(navController) {
        TapNavigationActions(navController)
    }
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        // TODO: move the navigation to the features, like ForYouNavigation.kt
        composable(TapDestinations.TAP_ROUTE) {
            TapScreen(activity = activity, intent = intent, onCardDataAvailable = {
                navActions.navigateToData()
            })
        }
        composable(TapDestinations.DATA_ROUTE) {
            DataScreen(onClick = { navActions.navigateToMessage() }, dataHolder = dataHolder)
        }
        composable(TapDestinations.MESSAGE_ROUTE) {
            MessageScreen(dataHolder)
        }
    }
}

val dataHolder = DataHolder()