package com.example.aichatbotapp.presentation.screens.chatscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aichatbotapp.domain.model.MessageModel
import com.example.aichatbotapp.presentation.viewmodels.ChatViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Chat_screen( viewModel: ChatViewModel){
    val prompt = remember {
        mutableStateOf("")
    }
    fun onPromptSend(){
        //send message to ai here
        viewModel.sendPrompt(prompt.value)
        //clear text field
        prompt.value = ""
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "AI",
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
                    ) }
            )
        },
        bottomBar = {}
    ) { paddingValues ->
        Column(

            modifier = Modifier
                .padding(paddingValues)


        ) {
            //list of messages
            LazyColumn(

                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(8.dp)

            ) {
                items(viewModel.messageList){message ->
                    MessageItem(message)

                }

            }
            //input field for user
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = prompt.value,
                    onValueChange = {prompt.value = it},
                    placeholder = {Text(text = "Prompt...")},
                    modifier = Modifier
                        .weight(1f)
                        .padding(5.dp)
                )
                Button(
                    onClick = {
                        onPromptSend()
                    },
                    shape = RoundedCornerShape(5.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = "Send_button",
                        modifier = Modifier
                            .height(42.dp)
                    )
                }


            }
            Spacer(modifier = Modifier.height(10.dp))


        }

    }

}
@Composable
fun MessageItem(message: MessageModel) {
    val isUser = message.role == "user"

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start
    ) {
        Card(
            modifier = Modifier
                .widthIn(max = 280.dp)
                .padding(4.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (isUser)
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.surfaceVariant
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = message.message,
                modifier = Modifier.padding(12.dp),
                color = if (isUser)
                    MaterialTheme.colorScheme.onPrimary
                else
                    MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
