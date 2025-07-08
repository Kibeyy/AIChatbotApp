package com.example.splashscreen.presentation.onboading.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.splashscreen.presentation.onboading.pages

@Composable
fun PageIndicator(
    pageSize:Int,
    selectedPage:Int,
    selectedColor:Color = MaterialTheme.colorScheme.primary,
    unselectedColor: Color = Color.DarkGray

){
    Row (
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        repeat(times = pageSize) { page ->
            Box(modifier = Modifier
                .clip(RoundedCornerShape(50))
                .size(14.dp)
                .background(color = if (page == selectedPage) selectedColor else unselectedColor)
            )
        }

    }

}