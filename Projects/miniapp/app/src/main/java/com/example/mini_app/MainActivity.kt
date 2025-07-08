package com.example.mini_app

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mini_app.ui.theme.MiniappTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

                var isDarkTheme by remember { mutableStateOf(false) }
                MiniappTheme(darkTheme = isDarkTheme) {
                    Home(
                        isDarkTheme = isDarkTheme,
                        onToggleTheme = { isDarkTheme = !isDarkTheme }
                    )
                }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Home(
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val isDarkModeOn = remember { mutableStateOf(false) }

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.width(250.dp)
            ) {

                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.Gray)
                        .height(200.dp)
                ){
                    Text(
                        text = "Collins Kibe", fontWeight = FontWeight.Bold, letterSpacing = 2.sp,
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.titleLarge
                    )
                }
                NavigationDrawerItem(

                    label = { Text("Logout") },
                    selected = false,
                    onClick = {
                        // TODO: Handle logout
                    },
                    icon = {
                        Icon(Icons.Default.Logout, contentDescription = "Logout")
                    },
                    badge = {
                        // Simple red dot
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .background(Color.Red, shape = CircleShape)
                        )
                    }
                )
            }
        },
        modifier = Modifier,
        drawerState = drawerState,
        gesturesEnabled = true,
        scrimColor = Color.Transparent
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Gray),
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawerState.open()
                                }


                            }
                        ) {
                            Icon(Icons.Default.Menu, contentDescription = null)
                        }

                    },
                    title = {
                        Text(text = "HOME_PAGE", fontWeight = FontWeight.Bold)
                    },
                    actions = {
                        IconButton(onClick = onToggleTheme) {
                            Icon(if(isDarkTheme) Icons.Default.LightMode else Icons.Default.DarkMode, contentDescription = "Toggle Theme")
                        }
                    }
                )
            }
        ) {
            // Add screen content here
        }
    }
}
