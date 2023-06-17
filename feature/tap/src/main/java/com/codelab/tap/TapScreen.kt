package com.codelab.tap

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.codelab.nfcreader.NfcStatus


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TapScreen(
    activity: Activity,
    modifier: Modifier = Modifier,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    viewModel: TapViewModel = hiltViewModel(),
    nfcStatus: NfcStatus = viewModel.uiState,
) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        snackbarHost = {SnackbarHost(hostState = snackbarHostState)}
    ) { paddingValues ->
        Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(paddingValues)) {
            Text("Tap Card")
        }

        DisposableEffect(lifecycleOwner) {
            // Create an observer that triggers our remembered callbacks
            // for sending analytics events
            val observer = LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_RESUME) {
                    Log.i("Alex", "startNfcReader")
                    viewModel.startNfcReader(activity)
                } else if (event == Lifecycle.Event.ON_PAUSE) {
                    Log.i("Alex", "stopNfcReader")
                    viewModel.stopNfcReader(activity)
                }
            }

            // Add the observer to the lifecycle
            lifecycleOwner.lifecycle.addObserver(observer)

            // When the effect leaves the Composition, remove the observer
            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
            }
        }

        // TODO: Use LONG snackbar with label
        LaunchedEffect(nfcStatus) {
            when (nfcStatus) {
                NfcStatus.NFC_UNSUPPORTED -> {
                    snackbarHostState.showSnackbar("NFC is unsupported")
                }

                NfcStatus.NFC_DISABLED -> {
                    Log.i("Alex", "NFC is disabled")
                    snackbarHostState.showSnackbar("NFC is disabled")
                }

                else -> {
//                Log.i("Alex", "NFC is ready")
                }
            }
        }
    }

}
