package com.example.onboarding.presentation.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen() {
    val pagerState = rememberPagerState(initialPage = 0) { pages.size }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), // Optional padding
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Pager with weight to take ~85% of the height
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // Take up remaining vertical space, but not all
        ) { index ->
            Onboarding(page = pages[index])
        }

        Spacer(modifier = Modifier.height(7.dp))

        // Navigation Buttons
        
    }
}
