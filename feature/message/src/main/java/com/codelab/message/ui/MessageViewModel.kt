package com.codelab.message.ui

import androidx.lifecycle.ViewModel
import com.codelab.smssender.SendSmsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MessageViewModel @Inject constructor(
    private val sendSmsUseCase: SendSmsUseCase
) : ViewModel() {

    fun sendSms(phoneNo: String, content: String) = sendSmsUseCase(phoneNo, content)
}