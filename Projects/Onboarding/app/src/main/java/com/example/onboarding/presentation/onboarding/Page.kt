package com.example.onboarding.presentation.onboarding


import androidx.annotation.DrawableRes
import com.example.onboarding.R

data class Page(
    @DrawableRes val image:Int,
    val title:String,
    val description:String
)

val pages = listOf<Page>(
    Page(
        image = R.drawable.images,
        title = "Onboarding 1",
        description = "This is the description of page 1"
    ),
    Page(
        image = R.drawable.images,
        title = "Onboarding 2",
        description = "This is the description of page 2"
    ),
    Page(
        image = R.drawable.images,
        title = "Onboarding 3",
        description = "This is the description of page 3"
    )
)