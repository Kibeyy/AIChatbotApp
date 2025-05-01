package com.example.drawerapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.automirrored.filled.Notes
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notes
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.drawerapp.ui.theme.DrawerAppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DrawerAppTheme {
                Home()

                }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Home(){
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    ModalNavigationDrawer(
        modifier = Modifier.fillMaxWidth(),
        drawerContent = {ModalDrawerSheet(modifier = Modifier.width(230.dp)) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .background(color = Color.Blue.copy(alpha = .5f))
            ) {
                Text(text = "Collins Kibe", fontSize = 30.sp, fontWeight = FontWeight.Bold, letterSpacing = 5.sp)

            }
            NavigationDrawerItem(
                label = { Text(text = "Logout") },
                selected = false,
                onClick = { Toast.makeText(context,"User logged out!",Toast.LENGTH_SHORT).show()},
                modifier = Modifier,
                icon = { Icon( Icons.AutoMirrored.Default.Logout, contentDescription = null)},
                shape = RectangleShape,
            )

            NavigationDrawerItem(
                label = { Text(text = "Licenses") },
                selected = false,
                onClick = { },
                modifier = Modifier,
                icon = { Icon( Icons.AutoMirrored.Default.Notes, contentDescription = null)},
                shape = RectangleShape,
            )


        }},
        drawerState = drawerState,
        gesturesEnabled = true,
        scrimColor = Color.Transparent
    ) {

    Scaffold (
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Blue.copy(alpha = .5f)),
                navigationIcon = {
                   IconButton(
                       onClick = {
                           scope.launch {
                               drawerState.open()
                           }
                       }
                   ) { Icon(Icons.Default.Menu, contentDescription = null) }
                },
                title = { Text(text = "DRAWER_APP", fontWeight = FontWeight.Bold, letterSpacing = 2.sp) },
                actions = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(Icons.Default.DarkMode, contentDescription = null)
                    }
                }
            )
        }

    ){ paddingValues ->

    }

}}