package com.codelab.tapandpay.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.codelab.dataholder.DataHolder
import com.codelab.tapandpay.ui.DataScreen


const val dataNavigationRoute = "data_display"

fun NavController.navigateToData() {
    this.navigate(dataNavigationRoute)
}

fun NavGraphBuilder.dataScreen(onClick: () -> Unit, dataHolder: DataHolder) {
    composable(dataNavigationRoute) {
        DataScreen(
            onClick = onClick, dataHolder = dataHolder
        )
    }
}