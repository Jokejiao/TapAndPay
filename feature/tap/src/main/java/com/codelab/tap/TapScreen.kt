package com.codelab.tap

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

@Preview
@Composable
fun TapScreen(
    viewModel: TapViewModel = hiltViewModel()
) {
    Box(contentAlignment = Alignment.Center) {
        Text("Tap Card")
    }
}
