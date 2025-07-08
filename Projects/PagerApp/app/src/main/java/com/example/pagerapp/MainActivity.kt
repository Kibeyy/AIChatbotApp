package com.example.pagerapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pagerapp.ui.theme.PagerAppTheme
import com.google.accompanist.pager.HorizontalPagerIndicator


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PagerAppTheme {
                Home()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Home(){
    val pages = listOf(
        R.drawable.slideone,
        R.drawable.slidetwo,
        R.drawable.slidethree
    )
    val pagerState = rememberPagerState(initialPage = 0, pageCount = {pages.size})
    Scaffold(
        topBar = {
            TopAppBar(

                title = {Text(text = "Pager App", fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic, letterSpacing = 2.sp)},

                )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                HorizontalPager(pagerState, modifier = Modifier.fillMaxSize()) { index ->
                    Image(
                        painter = painterResource(pages[index]),
                        modifier = Modifier.fillMaxSize(),
                        contentDescription = null, contentScale = ContentScale.Crop
                    )


                }

            }
            //page indicator
            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .padding(16.dp),
                activeColor = Color.Blue,
                inactiveColor = Color.LightGray
            )

        }

    }
}