package com.example.weather.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weather.data.remote.NetworkResponse
import com.example.weather.data.remote.WeatherModel
import com.example.weather.presentation.components.WeatherItem
import com.example.weather.presentation.viewmodels.WeatherViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherPage(viewModel: WeatherViewModel = hiltViewModel()){
    val city = remember {
        mutableStateOf("")
    }
    val weatherResult = viewModel.weatherResult.observeAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Weather") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // ✅ Apply scaffold padding first
                .padding(horizontal = 15.dp, vertical = 10.dp) // ✅ Then your custom padding
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = city.value,
                    onValueChange = { city.value = it },
                    modifier = Modifier.weight(1f), // Takes remaining space
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search icon"
                        )
                    },
                    placeholder = {
                        Text(
                            text = "City",
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    },
                    singleLine = true
                )

                Button(
                    onClick = {
                        // Handle search logic here
                        viewModel.getData(city.value)

                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Text(text = "Search")
                }
            }
            //main body here
            when(val result = weatherResult.value) {
                is NetworkResponse.Error -> result.message
                NetworkResponse.Loading -> CircularProgressIndicator()
                is NetworkResponse.success -> WeatherDetails(data = result.data)
                null -> {}
            }
        }
    }
}

@Composable
fun WeatherDetails(data: WeatherModel){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding( vertical = 25.dp)
    ) {
        //main location tag
        Card(
            elevation = CardDefaults.cardElevation(10.dp),
            shape = RoundedCornerShape(20.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(20.dp)
                    .fillMaxWidth()
            ) {
                Icon(Icons.Default.LocationOn, contentDescription = "Location icon")
                Row {
                    Text(text = data.location.name+", ", fontWeight = FontWeight.Bold)
                    Text(text = data.location.country, color = Gray)
                }

            }
        }
        Spacer(Modifier.height(20.dp))
        Row {
            WeatherItem(iconURl = data.current.condition.icon,
                description = data.current.condition.text)

        }

    }

}
