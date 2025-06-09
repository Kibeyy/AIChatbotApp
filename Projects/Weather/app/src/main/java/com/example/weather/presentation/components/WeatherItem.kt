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
import coil.compose.AsyncImage


@Composable
fun WeatherItem(
                iconURl:String,
                description: String
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
            AsyncImage(
                model = "https:${iconURl}",
                contentDescription = "Current weather icon",
                modifier = Modifier.size(80.dp)
            )
            Spacer(Modifier.height(10.dp))
            Text(text = description, fontWeight = FontWeight.Bold)


        }
    }

}