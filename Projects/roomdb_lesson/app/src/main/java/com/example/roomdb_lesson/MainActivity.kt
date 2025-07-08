package com.example.roomdb_lesson

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.roomdb_lesson.data.local.userEntity.userEntity
import com.example.roomdb_lesson.presentation.viewmodels.userViewModel.UserViewModel
import com.example.roomdb_lesson.ui.theme.Roomdb_lessonTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.hilt.navigation.compose.hiltViewModel




@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val vm: UserViewModel = hiltViewModel() // Use hiltViewModel to get the ViewModel
            MainScreen(viewModel = vm)


        }
    }
}


@Composable
fun MainScreen(viewModel: UserViewModel){
    val input = remember { mutableStateOf("") }
    val userList by viewModel.allUsers.observeAsState(emptyList())

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            OutlinedTextField(
                value = input.value,
                onValueChange = { input.value = it },
                label = { Text("Name") },
                modifier = Modifier.width(200.dp)
            )
            Button(
                onClick = {
                    val name = input.value.trim()
                    if (name.isNotEmpty()) {
                        viewModel.addUser(userEntity(firstName = name, isChecked = false))
                        input.value = ""
                    }
                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .padding(top = 5.dp)
                    .fillMaxHeight()
            ) {
                Text("ADD NAME")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(userList) { user ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text(text = user.firstName)
                }
            }
        }
    }
}


