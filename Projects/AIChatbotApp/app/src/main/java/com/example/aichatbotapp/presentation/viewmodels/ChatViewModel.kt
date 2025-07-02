package com.example.aichatbotapp.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aichatbotapp.constants.Constants
import com.example.aichatbotapp.domain.model.MessageModel
import com.google.ai.client.generativeai.GenerativeModel
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

    fun sendPrompt(prompt: String) {
        viewModelScope.launch {
            try {
                Log.i("Testing", "Sending prompt: $prompt")

                // Add user message first
                messageList.add(MessageModel(prompt, "user"))

                val chat = generativeModel.startChat()
                val response = chat.sendMessage(prompt)

                // Safe null checking
                val responseText = response.text
                if (responseText != null) {
                    Log.i("Testing", "Response: $responseText")
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