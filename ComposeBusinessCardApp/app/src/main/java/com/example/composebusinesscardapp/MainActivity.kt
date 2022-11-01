package com.example.composebusinesscardapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composebusinesscardapp.ui.theme.ComposeBusinessCardAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBusinessCardAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ComposeBusinessCardApp()
                }
            }
        }
    }
}

@Composable
private fun ComposeBusinessCardApp() {
    Column(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val image = painterResource(R.drawable.android_logo)
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier.size(128.dp)
        )

        Text(
            text = "Daru Nugroho",
            fontSize = 48.sp,
            color = Color.White,
        )

        Text(
            text = "Beginner Android Developer",
            color = Color(0xFF3ddc84),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            fontFamily = FontFamily.Monospace
        )

        Row(
            modifier = Modifier
                .padding(top = 64.dp)
                .fillMaxWidth(), // match dengan lebar
            horizontalArrangement = Arrangement.SpaceEvenly, // posisi dalam horizontal
            verticalAlignment = Alignment.CenterVertically // atur posisi dalam vertikal
        ) {
            val icon = Icons.Rounded.Phone
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color(0xFF3ddc84)
            )
            Text(
                text = "+62 823 8215 7576",
                color = Color(0xFF3ddc84)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly, // posisi dalam horizontal
            verticalAlignment = Alignment.CenterVertically // atur posisi dalam vertikal
        ) {
            Icon(
                imageVector = Icons.Rounded.Share,
                contentDescription = null,
                tint = Color(0xFF3ddc84)
            )
            Text(
                text = "@Dr Nugroho",
                color = Color(0xFF3ddc84)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly, // posisi dalam horizontal
            verticalAlignment = Alignment.CenterVertically // atur posisi dalam vertikal
        ) {
            Icon(
                imageVector = Icons.Rounded.Email,
                contentDescription = null,
                tint = Color(0xFF3ddc84)
            )
            Text(
                text = "darunugroho00@gmail.com",
                color = Color(0xFF3ddc84),

                )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeBusinessCardAppTheme {
        ComposeBusinessCardApp()
    }
}