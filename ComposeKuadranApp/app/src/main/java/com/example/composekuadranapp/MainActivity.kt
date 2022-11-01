package com.example.composekuadranapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composekuadranapp.ui.theme.ComposeKuadranAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeKuadranAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                 //   modifier = Modifier.fillMaxSize(), // supaya tidak penuh satu layar
                    color = MaterialTheme.colors.background
                ) {
                    ComposeQuadrantApp()
                }
            }
        }
    }
}

@Composable
fun ComposeQuadrantApp() {
   Column(Modifier.fillMaxWidth()) {
       Row(Modifier.weight(2f)) {
           ComposableInfoCard( title = stringResource(R.string.title_text_compose),
               description = stringResource(id = R.string.desc_text_compose),
               backgroundColor = Color.Green,
               modifier = Modifier.weight(1f) // hanya bisa di gunakan jika didalam column/row
           )
           ComposableInfoCard(
               title = stringResource(R.string.title_image_compose),
               description = stringResource(R.string.desc_image_compose),
               backgroundColor = Color.Yellow,
               modifier = Modifier.weight(1f)
           )
       }
       Row(Modifier.weight(2f)) {
           ComposableInfoCard(
               title = stringResource(id = R.string.title_row_compose),
               description = stringResource(R.string.desc_row_compose),
               backgroundColor = Color.Cyan,
               modifier = Modifier.weight(1f)
           )
           ComposableInfoCard(
               title = stringResource(id = R.string.title_column_compose),
               description = stringResource(R.string.desc_column_compose),
               backgroundColor = Color.Gray,
               modifier = Modifier.weight(1f)
           )
       }

   }
}

@Composable
private fun ComposableInfoCard(
    title: String,
    description: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize() // agar dapat menggunakan size layar penuh
            .background(backgroundColor)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = description,
            textAlign = TextAlign.Justify
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeKuadranAppTheme {
        ComposeQuadrantApp()
    }
}