package com.example.gridimagesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gridimagesapp.ui.theme.GridImagesAppTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GridImagesAppTheme {
                HomePage()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(){
    val fruitsCount = listOf(1,2,3,4,5,6,7,8,9)
    val gridState = rememberLazyGridState()


    Column(){
        CenterAlignedTopAppBar(
            title = {Text(text = "GRID_APP", fontStyle = Italic, fontWeight = Bold)}
        )
//        LazyVerticalGrid(
//            columns = GridCells.Fixed(2),
//            modifier = Modifier.fillMaxSize(),
//            state = gridState,
//            contentPadding = PaddingValues(10.dp),
//           // reverseLayout = TODO(),
//            verticalArrangement = Arrangement.spacedBy(8.dp),
//            horizontalArrangement = Arrangement.spacedBy(8.dp),
//            userScrollEnabled = true
//        ) {items(fruitsCount.size) { fruit ->
//            FruitItem(fruit + 1)
//
//        }
//
//        }
        StaggeredGrid()

    }
}

@Composable
fun FruitItem(number: Int){
    val randomHeight = remember { Random.nextInt(100, 250).dp }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(randomHeight)
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ){
            Text(text = "Fruit no: ${number} ")

        }
    }
}

@Composable
fun StaggeredGrid(){
    val fruitsCount = listOf(1,2,3,4,5,6,7,8,9)
    val state = rememberLazyStaggeredGridState()
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        state = state,
        contentPadding = PaddingValues(10.dp),
        verticalItemSpacing = 6.dp,
        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp),
        userScrollEnabled = true
    ) {
        items (fruitsCount.size){fruit ->
            FruitItem(fruit + 1)

        }
    }
}