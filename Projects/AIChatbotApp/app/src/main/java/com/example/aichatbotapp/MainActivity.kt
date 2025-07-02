package com.example.aichatbotapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.aichatbotapp.presentation.screens.chatscreen.Chat_screen
import com.example.aichatbotapp.presentation.theme.AIChatbotAppTheme
import com.example.aichatbotapp.presentation.viewmodels.ChatViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AIChatbotAppTheme {
                val viewModel: ChatViewModel = hiltViewModel()
                Chat_screen(viewModel = viewModel)

            }
        }
    }
}

