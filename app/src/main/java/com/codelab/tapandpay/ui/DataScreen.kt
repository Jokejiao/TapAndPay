package com.codelab.tapandpay.ui


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.codelab.dataholder.DataHolder
import com.codelab.tap.ui.CardDataScreen
import com.codelab.tapandpay.R
import com.codelab.weather.ui.WeatherDataScreen


@Composable
fun DataScreen(
    onClick: () -> Unit,
    dataHolder: DataHolder,
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
    ) {
        Button(
            modifier = Modifier.align(Alignment.BottomEnd),
            onClick = {
                dataHolder.setData(data)
                onClick()
            },
            enabled = data.isNotEmpty(),
        ) {
            Text(text = stringResource(id = R.string.message_button_text))
        }
    }
}



