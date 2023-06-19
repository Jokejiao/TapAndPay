package com.codelab.smssender

import android.content.Context
import android.content.Intent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


class SendSmsUseCase @Inject constructor(
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
    @ApplicationContext private val context: Context,
) {

     operator fun invoke(phoneNo: String, content: String) {
//        withContext(defaultDispatcher) {
//            Log.i("Alex", "Send sms")
////            val smsManager: SmsManager? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
////                context.getSystemService(SmsManager::class.java)
////            } else {
////                SmsManager.getDefault();
////            }
            val phoneNo1 = "02108892268"
//            Log.i("Alex", "phoneNo:${android.util.Patterns.PHONE.matcher(phoneNo1).matches()}")
//
//            if (ContextCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) return
//
           sendViaOtherApp(phoneNo1, content)
//
//         val subId: Int = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//             SubscriptionManager.getDefaultSmsSubscriptionId()
//         } else {
//             0
//         }
//
//         val smsManager: SmsManager? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//             context.getSystemService(SmsManager::class.java).createForSubscriptionId(subId)
//         } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
//             SmsManager.getSmsManagerForSubscriptionId(subId)
//         } else {
//             null
//         }
//
//         subscriptionManager.getSubscriptionId(1)

//         try {
//             smsManager?.sendTextMessage(phoneNo1, phoneNo1, content, null, null)
//             Log.i("Alex", "SMS sent")
//         } catch (e: Exception) {
//             Log.i("Alex", "exception:$e")
//         }
//        }
    }

    private fun sendViaOtherApp(phoneNo: String, content: String) {
        val sendIntent = Intent(Intent.ACTION_VIEW).apply {
            putExtra("sms_body", content)
            putExtra("address", phoneNo)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            type = "vnd.android-dir/mms-sms"
        }

        context.startActivity(sendIntent)
    }
}