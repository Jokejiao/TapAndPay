package com.codelab.tapandpay


import android.content.Intent
import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
        composable(TapDestinations.TAP_ROUTE) {
            TapScreen(activity = activity, intent = intent, onCardDataAvailable = {
                Log.i("Alex", "navigate to data screen")
                navActions.navigateToData()
            })
        }
        composable(TapDestinations.DATA_ROUTE) {
            DataScreen {
                navActions.navigateToMessage()
            }
        }
        composable(TapDestinations.MESSAGE_ROUTE) {
            MessageScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MessageScreen() {
    OutlinedTextField(value = "", onValueChange = {newValue ->  Unit})
}