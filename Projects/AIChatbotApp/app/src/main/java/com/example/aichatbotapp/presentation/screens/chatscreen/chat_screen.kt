package com.example.aichatbotapp.presentation.screens.chatscreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Panorama
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aichatbotapp.domain.model.MessageModel
import com.example.aichatbotapp.presentation.viewmodels.ChatViewModel
import com.example.aichatbotapp.R
import com.example.aichatbotapp.presentation.screens.screen_components.Bottom_Input

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Chat_screen( viewModel: ChatViewModel){
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val prompt = remember {
        mutableStateOf("")
    }
    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    fun onPromptSend(){
        //send message to ai here
        viewModel.sendPrompt(prompt.value)
        //clear text field
        prompt.value = ""
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = {
                            //history_button

                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "drawer_button",
                            modifier = Modifier
                                .height(25.dp)
                            //.background(Color.LightGray)
                        )
                    }
                },
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
            if(viewModel.messageList.isNotEmpty()){
                //list of messages
                LazyColumn(
                    reverseLayout = true,

                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(8.dp)

                ) {
                    items(viewModel.messageList.reversed()){message ->
                        MessageItem(message)

                    }

                }
            }else{
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_question_answer_24),
                        contentDescription = null,
                        modifier = Modifier
                            .size(100.dp)

                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Ask me anything",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier

                    )
                }
                
            }
            //input field for user here

//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier
//                    .padding(horizontal = 10.dp)
//                    .fillMaxWidth()
//            ) {
//                OutlinedTextField(
//                    value = prompt.value,
//                    onValueChange = {prompt.value = it},
//                    placeholder = {Text(text = "Prompt...")},
//                    modifier = Modifier
//                        .weight(1f)
//                        .padding(5.dp)
//                )
//                Button(
//                    onClick = {
//                        if (prompt.value.isNotEmpty()){
//                            onPromptSend()
//                        }
//
//                    },
//                    shape = RoundedCornerShape(5.dp)
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.Send,
//                        contentDescription = "Send_button",
//                        modifier = Modifier
//                            .height(42.dp)
//                    )
//                }
//
//
//            }
            Bottom_Input(prompt = prompt.value, onPromptChange = {prompt.value = it}, onSend = {onPromptSend()})
            //space bottom of input field
            Spacer(Modifier.height(10.dp))



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
            SelectionContainer {
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
}
