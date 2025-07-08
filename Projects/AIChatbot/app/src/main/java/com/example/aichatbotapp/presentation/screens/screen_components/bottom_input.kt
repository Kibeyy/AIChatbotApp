package com.example.aichatbotapp.presentation.screens.screen_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Panorama
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Bottom_Input(
    prompt: String,
    onPromptChange: (String) -> Unit,
    onSend: () -> Unit
) {
    val scrollState = rememberScrollState()

    // Auto-scroll to bottom when text changes
    LaunchedEffect(prompt) {
        scrollState.animateScrollTo(scrollState.maxValue)
    }

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .background(Color.DarkGray)
            .fillMaxWidth()
            .imePadding()
            .padding(horizontal = 12.dp, vertical = 12.dp)
    ) {
        TextField(
            value = prompt,
            onValueChange = onPromptChange,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp, max = 150.dp)
                .verticalScroll(scrollState), // Natural scroll direction
            placeholder = { Text(text = "Prompt...") },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
            ),
            maxLines = Int.MAX_VALUE,
            singleLine = false
        )

        Spacer(Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { /* upload image */ }) {
                Icon(
                    imageVector = Icons.Default.Panorama,
                    contentDescription = "upload_image_button",
                    tint = Color.White,
                    modifier = Modifier.height(24.dp)
                )
            }

            Row {
                IconButton(onClick = { /* record audio */ }) {
                    Icon(
                        imageVector = Icons.Default.Mic,
                        contentDescription = "upload_audio_button",
                        tint = Color.White,
                        modifier = Modifier.height(24.dp)
                    )
                }

                if (prompt.isNotEmpty()) {
                    IconButton(onClick = onSend) {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = "send_button",
                            tint = Color.White,
                            modifier = Modifier.height(24.dp)
                        )
                    }
                }
            }
        }
    }
}