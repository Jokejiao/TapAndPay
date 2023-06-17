package com.codelab.tapandpay

import android.app.PendingIntent
import android.content.Intent
import android.nfc.NfcAdapter
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.codelab.tapandpay.ui.theme.TapAndPayTheme

//@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private var tapInit: TapInit? = null
    private var nfcAdapter: NfcAdapter? = null
    private var pendingIntent: PendingIntent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("Alex", "a=$this")
        tapInit = TapInit(this)
        tapInit?.initTap()
        setContent {
            TapAndPayTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TapNavGraph()
//                    TapScreen()
                }
            }
        }
//        nfcAdapter = NfcAdapter.getDefaultAdapter(this)
//        if (nfcAdapter == null) {
//            Toast.makeText(this, "No NFC", Toast.LENGTH_SHORT).show()
//            finish()
//            return
//        }
//        pendingIntent = PendingIntent.getActivity(
//            this, 0,
//            Intent(this, javaClass)
//                .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), PendingIntent.FLAG_MUTABLE
//        )
    }

    override fun onResume() {
        super.onResume()
//        if (currentRoute == TapDestinations.TAP_ROUTE) {
            tapInit?.startTap()
//        }
//        if (nfcAdapter != null) {
//            if (!nfcAdapter!!.isEnabled) showWirelessSettings()
//            nfcAdapter?.enableForegroundDispatch(this, pendingIntent, null, null)
//        }
    }
    private fun showWirelessSettings() {
        Toast.makeText(this, "You need to enable NFC", Toast.LENGTH_SHORT).show()
        val intent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
        startActivity(intent)
    }
    override fun onPause() {
        super.onPause()
//        if (currentRoute == TapDestinations.TAP_ROUTE) {
            tapInit?.stopTap()
//        }
//        nfcAdapter?.disableForegroundDispatch(this)
    }

    override fun onNewIntent(intent: Intent) {
        Log.i("Alex", "Card Detected")
        super.onNewIntent(intent)
        setIntent(intent)
    }
}
