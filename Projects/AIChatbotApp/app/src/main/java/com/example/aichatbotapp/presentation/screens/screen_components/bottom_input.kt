package com.example.aichatbotapp.presentation.screens.screen_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.ModifierLocal
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Bottom_Input(prompt:String,onPromptChange:(String)->Unit,onSend:()->Unit){

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .background(Color.DarkGray)
            .fillMaxWidth()
            .imePadding()

    ) {
        TextField(
            value = prompt,
            onValueChange = { onPromptChange(it) },
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = { Text(text = "Prompt...") },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,

            )



        )
        Spacer(Modifier.height(5.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
               // .padding(10.dp)
                .fillMaxWidth()
        ) {
            IconButton(
                onClick = {
                    //upload image button
                    
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Panorama,
                    contentDescription = "upload_image_button",
                    modifier = Modifier
                        .height(25.dp)
                        //.background(Color.LightGray)
                )
            }

            Row {
                //mic button for recording
                IconButton(
                    onClick = {
                        //record_audio_button

                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Mic,
                        contentDescription = "upload_audio_button",
                        modifier = Modifier
                            .height(25.dp)
                        //.background(Color.LightGray)
                    )
                }

                //send button
               if(prompt.isNotEmpty()){
                   IconButton(
                       onClick = {
                           //send_button
                           onSend()

                       }
                   ) {
                       Icon(
                           imageVector = Icons.Default.Send,
                           contentDescription = "send_button",
                           modifier = Modifier
                               .height(25.dp)
                           //.background(Color.LightGray)
                       )
                   }
               }


            }
        }


    }

}