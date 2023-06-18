package com.codelab.tapandpay


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.codelab.tap.CardDataScreen
import com.codelab.weather.WeatherDataScreen


@Composable
fun DataScreen(
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxHeight()
    ) {
        val splitModifier = Modifier.weight(1f)
        CardDataScreen(splitModifier)
        WeatherDataScreen(splitModifier)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Button(
            modifier = Modifier.align(Alignment.BottomEnd),
            onClick = {}
        ) {
            Text("Message")
        }
    }
}

