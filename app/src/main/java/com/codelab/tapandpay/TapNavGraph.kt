package com.codelab.tapandpay


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    startDestination: String = TapDestinations.DATA_ROUTE,
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
            TapScreen()
        }
        composable(TapDestinations.DATA_ROUTE) {
            DataScreen { navActions.navigateToMessage() }
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

@Composable
private fun DataScreen(onClick: () -> Unit) {
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
}
