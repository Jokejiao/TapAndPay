package com.codelab.tap

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun CardDataScreen(
    modifier: Modifier,
    viewModel: CardDataViewModel = hiltViewModel(),
    cardData: String = viewModel.cardData,

) {
    // TODO: Using stringResource
    Column(modifier = modifier) {
        Text("Card Data:", fontSize = 20.sp)
        Text(text = cardData, modifier = Modifier
            .horizontalScroll(state = rememberScrollState())
            .verticalScroll(state = rememberScrollState()))
    }
}