package com.example.weather.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun NormalWeatherItem(
    key:String,
    value:String
){

    Card(
        modifier = Modifier,
        shape = RoundedCornerShape(20.dp)
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(20.dp)
        ){
            Text(text = value, fontSize = 30.sp, fontWeight = FontWeight.Bold )
            Spacer(Modifier.height(10.dp))
            Text(text = key, fontWeight = FontWeight.Bold)


        }
    }

}