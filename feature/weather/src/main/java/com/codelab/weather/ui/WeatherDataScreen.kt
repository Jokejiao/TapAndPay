package com.codelab.weather

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
fun WeatherDataScreen(
    modifier: Modifier,
    viewModel: WeatherDataViewModel = hiltViewModel(),
    onDataAvailable: (String) -> Unit
) {
    val weatherState by viewModel.weatherState.collectAsStateWithLifecycle()

    LaunchedEffect(onDataAvailable) {
        viewModel.setDataCallback(onDataAvailable)
    }

    Column(modifier = modifier) {
        Text("Weather Data:", fontSize = 20.sp)

        Text(
            text = weatherState, modifier = Modifier
                .horizontalScroll(state = rememberScrollState())
                .verticalScroll(state = rememberScrollState())
        )
    }
}