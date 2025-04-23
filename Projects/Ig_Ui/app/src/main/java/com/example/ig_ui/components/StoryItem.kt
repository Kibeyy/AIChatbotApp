package com.example.ig_ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ig_ui.R


@Preview
@Composable
fun StoryItem(){
    Box (
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(100))
            .background(brush = Brush.linearGradient(
                colors = listOf(
                    Color.Red,
                    Color.Blue
                ),

            ))
            .width(80.dp).height(80.dp)
    ){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(100))
                .background(color = Color.Black)
                .width(75.dp).height(75.dp)
        ) {
            Box (
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(100))
                    .background(color = Color.White)
                    .width(70.dp).height(70.dp)

            ) {
                Image(painter = painterResource(R.drawable.img),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                    )

            }
        }
    }
}

@Preview
@Composable
fun Storyitem2() {
    Box(
        modifier = Modifier
            .width(80.dp)
            .height(80.dp)
    ) {
        // Story circle with image
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(100))
                .background(color = Color.White)
                .fillMaxSize()
                .border(
                    2.dp,
                    Brush.linearGradient(colors = listOf(Color.Red, Color.Blue)),
                    CircleShape
                )
        ) {
            Image(
                painter = painterResource(R.drawable.img),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                    .border(4.dp, Color.Black, CircleShape)
            )
        }

        // Plus icon at bottom right
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(10.dp)
                .align(Alignment.BottomEnd)
                .offset(x = 4.dp, y = 2.dp) // Optional, to push it a bit outward
                .clip(CircleShape)
                .background(color = Color.White)
        ) {
            Icon(
                Icons.Default.Add,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}
