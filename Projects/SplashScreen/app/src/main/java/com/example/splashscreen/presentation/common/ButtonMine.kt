package com.example.splashscreen.presentation.common

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun ButtonMine(
    onClick:() -> Unit,
    text: String
){
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(text = text)

    }

}