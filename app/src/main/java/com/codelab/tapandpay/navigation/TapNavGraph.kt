package com.codelab.tapandpay


import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.codelab.dataholder.DataHolder
import com.codelab.message.messageScreen
import com.codelab.message.navigateToMessage
import com.codelab.tap.tapNavigationRoute
import com.codelab.tap.tapScreen


@Composable
fun TapNavGraph(
    activity: MainActivity,
    intent: Intent,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = tapNavigationRoute,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        tapScreen(
            activity = activity,
            intent = intent,
            onCardDataAvailable = navController::navigateToData
        )
        dataScreen(onClick = navController::navigateToMessage, dataHolder = dataHolder)
        messageScreen(dataHolder = dataHolder)
    }
}

/* Since the data screen combined data is merely a String,
   hence directly passing it to the SMS screen.
   For more complex data, it should pass through more sophisticated data layer  */
val dataHolder = DataHolder()