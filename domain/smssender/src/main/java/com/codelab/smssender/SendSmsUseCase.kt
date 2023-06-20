package com.codelab.smssender

import android.content.Context
import android.content.Intent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class SendSmsUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    operator fun invoke(phoneNo: String, content: String) {

        // Sending the SMS directly doesn't work on my Huawei Note10, so delegate it to the system SMS app
/*        val smsManager: SmsManager? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            context.getSystemService(SmsManager::class.java)
        } else {
            SmsManager.getDefault();
        }

        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.SEND_SMS
            ) != PackageManager.PERMISSION_GRANTED
        ) return
        smsManager?.sendTextMessage(phoneNo, null, content, null, null)*/

        val sendIntent = Intent(Intent.ACTION_VIEW).apply {
            putExtra("sms_body", content)
            putExtra("address", phoneNo)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            type = "vnd.android-dir/mms-sms"
        }

        context.startActivity(sendIntent)
    }
}