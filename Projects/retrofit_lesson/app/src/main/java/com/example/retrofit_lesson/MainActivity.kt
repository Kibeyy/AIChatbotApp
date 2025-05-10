package com.example.retrofit_lesson

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.retrofit_lesson.ui.theme.Retrofit_lessonTheme
import com.example.retrofit_lesson.utils.RetrofitInstance
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Retrofit_lessonTheme {
                HomeScreen()
            }
        }
    }
}

// Fetches fact and returns it via callback
@OptIn(DelicateCoroutinesApi::class)
fun getFact(context: Context, onResult: (String) -> Unit) {
    GlobalScope.launch(Dispatchers.IO) {
        try {
            val response = RetrofitInstance.api.getFact()
            if (response.isSuccessful && response.body() != null) {
                val fact = response.body()!!.fact
                withContext(Dispatchers.Main) {
                    onResult(fact)
                }
            } else {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Unexpected error: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (e: HttpException) {
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "HTTP ERROR: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        } catch (e: IOException) {
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "IO ERROR: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun HomeScreen() {
    val context = LocalContext.current
    val fact = remember { mutableStateOf("Tap to get a cat fact!") }

    Scaffold()  {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    getFact(context) { newFact ->
                        fact.value = newFact
                    }
                }
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.catimage),
                contentDescription = null
            )
            Text(
                text = "CAT FACT",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 3.sp,
                color = Color.Blue
            )
            Spacer(Modifier.height(15.dp))
            Text(
                text = fact.value,
                fontSize = 20.sp,
                letterSpacing = 1.sp
            )
        }
    }
}
