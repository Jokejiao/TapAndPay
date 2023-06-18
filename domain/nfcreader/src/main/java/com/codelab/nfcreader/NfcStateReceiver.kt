package com.codelab.nfcreader

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NfcAdapter
import android.util.Log

class NfcStateReceiver(
    val onNfcEnabled: () -> Unit
) : BroadcastReceiver() {
    private var registered = false

    override fun onReceive(context: Context, intent: Intent) {

        if (intent.action.equals(NfcAdapter.ACTION_ADAPTER_STATE_CHANGED)) {
            val state = intent.getIntExtra(NfcAdapter.EXTRA_ADAPTER_STATE, NfcAdapter.STATE_OFF)

            if (state == NfcAdapter.STATE_ON) {
                onNfcEnabled()
            }
        }
    }

    fun register(context: Context) {
        if (!registered) {
            val filter = IntentFilter()
            filter.addAction(NfcAdapter.ACTION_ADAPTER_STATE_CHANGED)
            Log.i("Alex", "Register receiver:$this")
            context.registerReceiver(this, filter)
            registered = true
        }
    }

    fun unregister(context: Context) {
        if (registered) {
            Log.i("Alex", "Unregister receiver:$this")
            context.unregisterReceiver(this)
            registered = false
        }
    }
}
