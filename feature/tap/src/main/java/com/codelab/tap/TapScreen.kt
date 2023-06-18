package com.codelab.tap

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.codelab.nfcreader.NfcStateReceiver
import com.codelab.nfcreader.NfcStatus


var nfcIntent: Intent by mutableStateOf(Intent())

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TapScreen(
    activity: Activity,
    modifier: Modifier = Modifier,
    intent: Intent? = null,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    viewModel: TapViewModel = hiltViewModel(),
    nfcStatus: NfcStatus = viewModel.nfcState,
    dataStatus: Boolean = viewModel.dataState,
    onCardDataAvailable: () -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) {
        Box(
            contentAlignment = Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = it)
        ) {
            Text(
                text = "Tap Card", fontSize = 30.sp,
                modifier = Modifier.background(Color(0xffb3e5fc))
            )
        }

        DisposableEffect(lifecycleOwner) {
            // The NFC reader has to restart itself to work if the user turned off and on the NFC
            val receiver = NfcStateReceiver(onNfcEnabled = {
                Log.i("Alex1", "Restart NFCReader")
                viewModel.stopNfcReader(activity)
                viewModel.startNfcReader(activity)
            })
            Log.i("Alex1", "New receiver:$receiver")

            // Create an observer that triggers our remembered callbacks
            // for sending analytics events
            val observer = LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_RESUME) {
                    Log.i("Alex1", "startNfcReader")
                    receiver.register(activity)
                    viewModel.startNfcReader(activity)
                } else if (event == Lifecycle.Event.ON_PAUSE) {
                    Log.i("Alex1", "stopNfcReader")
                    viewModel.stopNfcReader(activity)
                    receiver.unregister(activity)
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
        if (nfcStatus != NfcStatus.NFC_OK) {
            LaunchedEffect(nfcStatus) {
                when (nfcStatus) {
                    NfcStatus.NFC_UNSUPPORTED -> {
                        snackbarHostState.showSnackbar("NFC is unsupported")
                    }

                    NfcStatus.NFC_DISABLED -> {
                        Log.i("Alex", "NFC is disabled")
                        snackbarHostState.showSnackbar("NFC is disabled")
                    }

                    else -> {}
                }
            }
        }
    }

    Log.i("Alex", "tap screen refresh once")

    LaunchedEffect(intent) {
        if (intent?.extras?.containsKey("NFC") == true) {
            Log.i("Alex", "Parse card data....")
            nfcIntent = Intent()
            viewModel.saveNfcPayload(intent = intent)
        }
    }

    LaunchedEffect(dataStatus) {
        if (dataStatus) {
            Log.i("Alex", "data available")
            viewModel.dataConsumed()
            onCardDataAvailable()
        }
    }
}

