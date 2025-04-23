package com.example.ig_ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AddBox
import androidx.compose.material.icons.outlined.Facebook
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.VideoLibrary
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ig_ui.components.StoryItem
import com.example.ig_ui.ui.theme.Ig_UiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Homepage()

        }
    }
}

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Homepage(){
    val Users = listOf("Collins","Kibe","Joyce","Muthoni","Lucy","Kibe")
    val userImage = painterResource(R.drawable.img)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text(text = "Instagram",
                    fontWeight = FontWeight.Bold
                    )},
                actions = {
                    IconButton(onClick = {}) {

                        Icon(Icons.Filled.Favorite,
                            contentDescription = null,
                            tint = Color.Red
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(Icons.Outlined.Facebook,
                            contentDescription = null)


                    }
                }


            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = "Home",
                            tint = Color.Black // Change this to any color you want
                        )
                    },
                    modifier = Modifier
                )

                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = { Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = "Search",
                        tint = Color.Black
                    ) },
                    modifier = Modifier,
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.AddBox,
                            contentDescription = "Add",
                            tint = Color.Black // Change this to any color you want
                        )
                    },
                    modifier = Modifier
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.VideoLibrary,
                            contentDescription = "Reels",
                            tint = Color.Black // Change this to any color you want
                        )
                    },
                    modifier = Modifier
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = {
                        Image(
                            painter = userImage,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.clip(CircleShape).size(24.dp)


                        )
                    },

                )



            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = paddingValues.calculateStartPadding(LocalLayoutDirection.current),
                    top = paddingValues.calculateTopPadding(),
                    end = paddingValues.calculateEndPadding(LocalLayoutDirection.current)
                )
        ) {
            LazyRow(

            ){
                items(Users.size){user ->
                    StoryItem()
                    Spacer(modifier = Modifier.width(10.dp))

                }

            }

            Spacer(Modifier.height(10.dp))

            Column (
                modifier = Modifier
                    .fillMaxSize()

            ){
                Row (modifier = Modifier.padding(start = 10.dp)){
                    Image(
                        painter = painterResource(R.drawable.img),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(CircleShape).size(50.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Column(modifier = Modifier.padding(top = 5.dp)) {
                        Text(text = "Collins Kibe", fontWeight = FontWeight.Bold)
                        Text(text = "Hold me while you wait.")
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = {}) { Icon(imageVector = Icons.Default.MoreVert,
                        contentDescription = null) }
                }
                Spacer(Modifier.height(8.dp))
                Image(
                    painter = painterResource(R.drawable.img),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    modifier = Modifier.clip(RoundedCornerShape(6.dp)).fillMaxSize()
                )

            }


        }
    }
}
