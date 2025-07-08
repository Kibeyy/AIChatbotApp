package com.example.splashscreen.presentation.onboading.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.splashscreen.presentation.onboading.Page
import com.example.splashscreen.R
import com.example.splashscreen.presentation.onboading.pages
import com.example.splashscreen.ui.theme.SplashScreenTheme

@Composable
fun OnboardingPage(
    modifier: Modifier,
    page: Page
){
    Column(

    ) {
        Image(
            painter = painterResource(page.image),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Spacer(Modifier.height(10.dp))
        Text(text = page.title)
        Text(text = page.description)

    }

}

@Preview
@Composable
fun Prev(){
    SplashScreenTheme {
        OnboardingPage(
            modifier = Modifier,
            page = pages[0]
        )
    }
}