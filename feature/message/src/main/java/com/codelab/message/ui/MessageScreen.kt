package com.codelab.message.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.codelab.dataholder.DataHolder
import com.codelab.tapandpay.feature.message.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageScreen(
    dataHolder: DataHolder,
    viewModel: MessageViewModel = hiltViewModel()
) {
    Column(modifier = Modifier.fillMaxHeight()) {
        var number by rememberSaveable { mutableStateOf("") }
        Text(text = stringResource(id = R.string.type_in_phone_no))

        OutlinedTextField(
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            value = number,
            onValueChange = { number = it },
            label = { Text(text = stringResource(id = R.string.phone_no)) })

        Button(
            onClick = { viewModel.sendSms(number, dataHolder.getData()) },
            enabled = number.isNotEmpty()
        ) {
            Text(text = stringResource(id = R.string.send))
        }
    }
}