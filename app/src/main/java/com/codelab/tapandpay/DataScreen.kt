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
import androidx.hilt.navigation.compose.hiltViewModel
import com.codelab.tap.CardDataScreen
import com.codelab.weather.WeatherDataScreen


@Composable
fun DataScreen(
    onClick: () -> Unit,
    viewModel: DataScreenViewModel = hiltViewModel(),
    data: String = viewModel.combinedData
) {
    Column(
        modifier = Modifier.fillMaxHeight()
    ) {
        val splitModifier = Modifier.weight(1f)
        CardDataScreen(splitModifier, onDataAvailable = viewModel::setCardData)
        WeatherDataScreen(splitModifier, onDataAvailable = viewModel::setWeatherData)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Button(
            modifier = Modifier.align(Alignment.BottomEnd),
            onClick = onClick,
            enabled = data.isNotEmpty(),
        ) {
            Text("Message")
        }
    }
}



