package com.codelab.tap.ui

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.codelab.tapandpay.feature.tap.R


@Composable
fun CardDataScreen(
    modifier: Modifier,
    viewModel: CardDataViewModel = hiltViewModel(),
    cardData: String = viewModel.cardData,
    onDataAvailable: (String) -> Unit
) {

    LaunchedEffect(onDataAvailable) {
        viewModel.setDataCallback(onDataAvailable)
    }

    Column(modifier = modifier) {
        Text(text = stringResource(id = R.string.card_data_text), fontSize = 20.sp)
        Text(
            text = cardData, modifier = Modifier
                .horizontalScroll(state = rememberScrollState())
                .verticalScroll(state = rememberScrollState())
        )
    }
}