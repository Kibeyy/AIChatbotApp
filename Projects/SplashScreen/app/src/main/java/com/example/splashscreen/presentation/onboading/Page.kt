package com.example.splashscreen.presentation.onboading


import com.example.splashscreen.R
import androidx.annotation.DrawableRes

data class Page(
    val title : String,
    val description : String,
    @DrawableRes val image: Int
)

val pages = listOf<Page>(
    Page(
        title = "Welcome aboard!",
        description = "This is onboarding 1",
        image = R.drawable.onboarding_two
    ),
    Page(
        title = "Welcome aboard again!",
        description = "This is onboarding 2",
        image = R.drawable.onboarding_two
    ),
    Page(
        title = "All done now!",
        description = "This is onboarding 3",
        image = R.drawable.onboarding_two
    )
)
