package com.example.weather.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GridWeatherItems(
    key: String,
    value:String
){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = value, fontSize = 30.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(15.dp))
        Text(text = key,fontWeight = FontWeight.Bold)
    }
}