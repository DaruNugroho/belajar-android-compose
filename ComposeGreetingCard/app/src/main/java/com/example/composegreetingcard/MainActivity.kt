package com.example.composegreetingcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composegreetingcard.ui.theme.ComposeGreetingCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeGreetingCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    //  modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    EidMubarakWithImage(
                        message = getString(R.string.selamat_idul_fitri_text),
                        from = getString(R.string.signature_text)
                    )
                }
            }
        }
    }
}

@Composable
fun EidMubarakGreetingWithText(message: String, from: String) {
    Column { // mengatur elemen secara vertikal
        Text( // menampilkan text
            text = message,
            fontSize = 36.sp,
            modifier = Modifier.fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally) // agar text ditengah
                .padding(start = 16.dp, top = 16.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = "- from $from",
            fontSize = 24.sp,
            modifier = Modifier.fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(top = 16.dp, end = 16.dp)
        )
    }
}

@Composable
fun EidMubarakWithImage(message: String, from: String) {
    val image = painterResource(id = R.drawable.androidparty)
    Box { // ibarat stack kalau di flutter
        // elemen layer 1
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxHeight() // ditengah pada panjangnya layar
                .fillMaxWidth(), //  center pada lebarnya layar
            contentScale = ContentScale.Crop // gambar di skale agar pas dengan panjang dan lebar
        ) // menampilkan gambar
        // elemen layer 2
        EidMubarakGreetingWithText(message = message, from = from)
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeGreetingCardTheme {
        //  EidMubarakGreetingWithText(message = "Selamat Hari Raya Idul Fitri", "- Daru")
        EidMubarakWithImage(message = "Selamat Hari Raya Idul Fitri", "Daru")
    }
}