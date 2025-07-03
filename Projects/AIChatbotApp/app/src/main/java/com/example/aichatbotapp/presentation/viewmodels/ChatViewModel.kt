package com.example.aichatbotapp.presentation.viewmodels

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aichatbotapp.constants.Constants
import com.example.aichatbotapp.domain.model.MessageModel
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor() : ViewModel() {

    // Fix: Properly initialize the messageList
    val messageList = mutableStateListOf<MessageModel>()

    val generativeModel: GenerativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = Constants.apiKey
    )

    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    fun sendPrompt(prompt: String) {
        viewModelScope.launch {
            try {
                Log.i("Testing", "Sending prompt: $prompt")

                // Add user message to list first
                messageList.add(MessageModel(prompt, "user"))
                //loading text before actual message is replied by model
                messageList.add(MessageModel("Typing...", "model"))

                val chat = generativeModel.startChat(
                    history = messageList.map {
                        content(it.role){ text(it.message) } }.toList(),
                )
                val response = chat.sendMessage(prompt)

                // Safe null checking
                val responseText = response.text

                if (responseText != null) {
                    Log.i("Testing", "Response: $responseText")
                    messageList.removeLast()
                    messageList.add(MessageModel(responseText, "model"))
                } else {
                    Log.w("Testing", "Response text is null")
                    messageList.add(MessageModel("Sorry, I couldn't generate a response.", "model"))
                }

            } catch (e: Exception) {
                Log.e("Errors", "Error sending prompt: ${e.message}", e)
                messageList.add(MessageModel("Error: ${e.message}", "model"))
            }
        }
    }
}