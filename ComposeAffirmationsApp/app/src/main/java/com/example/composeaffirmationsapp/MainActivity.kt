package com.example.composeaffirmationsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeaffirmationsapp.data.Datasource
import com.example.composeaffirmationsapp.model.Affirmation
import com.example.composeaffirmationsapp.ui.theme.ComposeAffirmationsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAffirmationsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    ComposeAffirmationsApp()
                }
            }
        }
    }
}

@Composable
private fun ComposeAffirmationsApp() {
    val context = LocalContext.current
    ComposeAffirmationsAppTheme {
        Scaffold(content = {
            AffirmationList(affimationList = Datasource().loadAffirmations())
        })
    }
}

@Composable
private fun AffirmationCard(
    affirmation: Affirmation,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = Modifier.padding(8.dp),
        elevation = 4.dp,
    ){
        val imageResourceId = affirmation.imageResourcerId
        val stringResourceId = affirmation.stringResourceId
        Column {

            Image(
                painter = painterResource(id = imageResourceId),
                contentDescription = stringResource(id = stringResourceId),
                Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop
            )

            Text(
                text = stringResource(id = stringResourceId),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.h6
            )
        }
    }
}

@Composable
private fun AffirmationList(affimationList: List<Affirmation>) {
    /**
     *  LazyColumn digunakan untuk daftar panjang,
     *  terutama jika panjang daftar tidak diketahui
     */
    LazyColumn{
        items(affimationList){ affimation -> // item yang ada didalam list
            // setial item, tampilkan dengan AffirmationCard
            AffirmationCard(affirmation = affimation)
        }
    }
}   

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeAffirmationsAppTheme {
        ComposeAffirmationsApp()
    }
}