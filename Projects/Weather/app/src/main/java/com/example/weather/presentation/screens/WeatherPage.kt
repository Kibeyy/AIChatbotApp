package com.example.weather.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weather.data.remote.NetworkResponse
import com.example.weather.data.remote.WeatherModel
import com.example.weather.presentation.components.GridWeatherItems
import com.example.weather.presentation.components.NormalWeatherItem
import com.example.weather.presentation.components.WeatherItem
import com.example.weather.presentation.viewmodels.WeatherViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherPage(viewModel: WeatherViewModel = hiltViewModel()){
    val city = remember {
        mutableStateOf("")
    }
    val weatherResult = viewModel.weatherResult.observeAsState()
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text(text = "Weather", fontWeight = FontWeight.Bold,
                letterSpacing = 3.sp) })
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
                    textStyle = TextStyle(
                        fontSize = 18.sp, // make text larger and clearer
                        lineHeight = 22.sp // optional for spacing
                    ),
                    placeholder = {
                        Text(
                            text = "City",
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search), // Set the IME action to Search
                    keyboardActions = KeyboardActions(
                        onSearch = { // This lambda is executed when the "Search" action button on the keyboard is pressed
                            viewModel.getData(city.value) // Perform your search
                            keyboardController?.hide() // Hide the keyboard
                        }
                    )
                )

                Button(
                    onClick = {
                        // Handle search logic here
                        viewModel.getData(city.value)

                    },
//                    colors = ButtonDefaults.buttonColors(
//                        containerColor = MaterialTheme.colorScheme.primary
//                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Text(text = "Search")
                }
            }
            //main body here
            when(val result = weatherResult.value) {
                is NetworkResponse.Error -> ErrPage(result.message)
                NetworkResponse.Loading -> LoadingPage()
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
                Column {
                    Text(text = data.location.name+",", fontWeight = FontWeight.Bold)
                    Text(text = data.location.country, color = Gray)
                }

            }
        }
        Spacer(Modifier.height(20.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ){
            WeatherItem(iconURl = data.current.condition.icon,
                description = data.current.condition.text)
            NormalWeatherItem(key = "Temperature", value = data.current.temp_c+ " °C")

        }
        Spacer(Modifier.height(15.dp))
        Card(
            elevation = CardDefaults.cardElevation(20.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(20.dp)
                , verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    GridWeatherItems(key = "Windspeed", value = data.current.wind_kph+" Km/h")
                    GridWeatherItems(key = "UV", value = data.current.uv)
                }
                Spacer(Modifier.height(50.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    GridWeatherItems(key = "Humidity", value = data.current.humidity)
                    GridWeatherItems(key = "Wind Direction", value = data.current.wind_dir)

                }


            }

        }

    }

}

@Composable
fun ErrPage(message: String){ // Accept a message parameter
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // You can use an Icon here to visually represent an error
        Icon(
            imageVector = Icons.Default.LocationOn, // Or a more suitable error icon if you have one
            contentDescription = "Error icon",
            tint = MaterialTheme.colorScheme.error, // Use error color from your theme
            modifier = Modifier.size(64.dp)
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = "Oops! Something went wrong.",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.error
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = message, // Display the actual error message
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
        // You could also add a retry button here
        // Spacer(Modifier.height(16.dp))
        // Button(onClick = { /* Handle retry logic, e.g., viewModel.getData("DefaultCity") */ }) {
        //     Text("Try Again")
        // }
    }


}

@Composable
fun LoadingPage() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LinearProgressIndicator(

        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = "Loading weather data...",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }}
