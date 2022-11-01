package com.example.composeartspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeartspaceapp.ui.theme.ComposeArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeArtSpaceAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colors.background,
                    elevation = 32.dp,
                ) {
                    ComposeArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ComposeArtSpaceApp() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(
            id = R.drawable.android_logo),
            contentDescription = null,
            modifier = Modifier
                .height(256.dp)
                .border(2.dp, Color.Blue),
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            shape = RoundedCornerShape(4.dp),
            elevation = 10.dp,
            modifier = Modifier.width(300.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = "Beginner Android Developer",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(text = "\"Lakukan apa yang kamu sukai. Seandainya kau menemukan masalah kau akan menganggap nya sebagai ??? \"", textAlign = TextAlign.Center)
                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = Color.Red)
                        ) {
                            append("Daru Nugroho ")
                        }
                        append("(2022)")
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,

        ) {
            Button(
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp),
                onClick = { /*TODO*/ }
            ) {
                Text(text = "Previous")
            }

            Button(
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp),
                onClick = { /*TODO*/ }
            ) {
                Text(text = "Next")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeArtSpaceAppTheme {
       ComposeArtSpaceApp()
    }
}