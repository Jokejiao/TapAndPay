package com.codelab.tapandpay

import android.app.PendingIntent
import android.content.Intent
import android.nfc.NfcAdapter
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity

class TapInit (val activity: ComponentActivity) {
    private var nfcAdapter: NfcAdapter? = null
    private var pendingIntent: PendingIntent? = null

    public fun initTap() {
        Log.i("Alex", "b=$activity")
        nfcAdapter = NfcAdapter.getDefaultAdapter(activity)
        if (nfcAdapter == null) {
            Toast.makeText(activity, "No NFC", Toast.LENGTH_SHORT).show()
            activity.finish()
            return
        }
        pendingIntent = PendingIntent.getActivity(
            activity, 0,
            Intent(activity, activity.javaClass)
                .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), PendingIntent.FLAG_MUTABLE
        )
    }

    public fun startTap() {
        if (nfcAdapter != null) {
            if (!nfcAdapter!!.isEnabled) showWirelessSettings()
            nfcAdapter?.enableForegroundDispatch(activity, pendingIntent, null, null)
        }
    }

    private fun showWirelessSettings() {
        Toast.makeText(activity, "You need to enable NFC", Toast.LENGTH_SHORT).show()
        val intent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
        activity.startActivity(intent)
    }

    public fun stopTap() {
        nfcAdapter?.disableForegroundDispatch(activity)
        Log.i("Alex","Stop TAp")
    }
}