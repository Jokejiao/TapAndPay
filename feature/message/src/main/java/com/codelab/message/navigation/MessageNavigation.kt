package com.codelab.message.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.codelab.dataholder.DataHolder
import com.codelab.message.ui.MessageScreen


const val messageNavigationRoute = "message_user"

fun NavController.navigateToMessage() {
    this.navigate(messageNavigationRoute)
}

fun NavGraphBuilder.messageScreen(dataHolder: DataHolder) {
    composable(messageNavigationRoute) {
        MessageScreen(dataHolder = dataHolder)
    }
}