package com.codelab.tapandpay


import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.codelab.tap.TapScreen
import kotlinx.coroutines.CoroutineScope


@Composable
fun TapNavGraph(
    activity: MainActivity,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    startDestination: String = TapDestinations.TAP_ROUTE,
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
            TapScreen(activity = activity)
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

//TODO: Move the two displays to the corresponding feature modules
@Composable
private fun CardDataDisplay(modifier: Modifier) {
    // TODO: Using stringResource
    Text("Card Data:", modifier = modifier)
}

@Composable
private fun WeatherDataDisplay(modifier: Modifier) {
    Text("Weather Data:", modifier = modifier)
}

sealed interface SettingsUiState {
    object Loading : SettingsUiState
    object disposing: SettingsUiState
}
//@Singleton
//class a @Inject constructor(@ActivityContext context: Context) {
//fun d(){
//
//}
//}
//@Inject
//lateinit var v: a

@Composable
private fun DataScreen( lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
                       onClick: () -> Unit) {
//    Box {
        Column (
            modifier = Modifier.fillMaxHeight()
        ) {
//            Text("Card Data:", modifier = Modifier.weight(1f))
//            Text("Weather Data:", modifier = Modifier.weight(1f))
            val splitModifier = Modifier.weight(1f)
            CardDataDisplay(splitModifier)
            WeatherDataDisplay(splitModifier)
        }
//    }
        Button(onClick = { onClick() }) {
        }
    DisposableEffect(lifecycleOwner) {
        // Create an observer that triggers our remembered callbacks
        // for sending analytics events
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                Log.i("Alex", "aaaa")
            } else if (event == Lifecycle.Event.ON_PAUSE) {
                Log.i("Alex", "bbbb1")
            }
        }

        // Add the observer to the lifecycle
        lifecycleOwner.lifecycle.addObserver(observer)

        // When the effect leaves the Composition, remove the observer
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
//            Log.i("Alex", "bbbb2")
        }
    }

//        DisposableEffect(Unit) {
//            Log.i("Alex", "aaaa")
//            onDispose {
//                Log.i("Alex", "bbbb")
//            }
//        }
}


//fun startNfc(navController: NavHostController) {
//    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentRoute = currentNavBackStackEntry?.destination?.route ?: TapDestinations.TAP_ROUTE
//
//    if (currentRoute == TapDestinations.TAP_ROUTE) {
//        Log.i("Alex","start NFC")
//    }
//}

@Composable
fun startNfc() {
    val navController = rememberNavController()
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavBackStackEntry?.destination?.route ?: TapDestinations.TAP_ROUTE

    if (currentRoute == TapDestinations.TAP_ROUTE) {
        Log.i("Alex","start NFC 1")
    }
}