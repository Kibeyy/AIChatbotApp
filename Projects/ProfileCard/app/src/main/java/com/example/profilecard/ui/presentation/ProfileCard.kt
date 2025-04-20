import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.profilecard.R

@Preview
@Composable
fun ProfileCard(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(top = 150.dp, bottom = 200.dp, start = 50.dp, end = 50.dp)
    ) {
        Card(
            elevation = CardDefaults.cardElevation(50.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(color = Color.Gray)
                    .fillMaxSize()

            ) {
                Image(
                    painter = painterResource(R.drawable.me),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(RoundedCornerShape(20))
                        .size(120.dp)
                )
                Spacer(modifier = Modifier.height(40.dp))
                Divider(modifier = Modifier.padding(all = 50.dp))

                Text(
                    text = "NAME: Collins Kibe",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp,
                    fontStyle = FontStyle.Italic
                )
                Text(
                    text = "JOB: Mobile Developer",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp,
                    fontStyle = FontStyle.Italic
                )
                Spacer(modifier = Modifier.height(30.dp))
                Divider(modifier = Modifier.padding(all = 30.dp))
                Text(
                    text = "Reach me out at:\ncollinskibe6477@gmail.com",
                    fontStyle = FontStyle.Italic,
                    color = Color.White
                )

            }
        }
    }
}
