package com.example.composewellnessapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composewellnessapp.model.Datasource.quoteList
import com.example.composewellnessapp.model.Quote
import com.example.composewellnessapp.ui.theme.ComposeWellnessAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeWellnessAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TopBarApp("Quote Ali Bin Abi Thalib")
                }
            }
        }
    }
}

@Composable
fun TopBarApp(name: String) {
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Blue)
                    .size(56.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Hello $name!", style = MaterialTheme.typography.h1)
            }
        }
    ) {
        ListQuote(quoteList = quoteList)
    }
}

@Composable
private fun ListQuote(quoteList: List<Quote>) {
    var days = 1
    LazyColumn {
        items(quoteList) {
            ItemQuoteCard(quote = it, days)
            days+=1
        }
    }
}

@Composable
private fun ItemQuoteCard(quote: Quote, days: Int) {
    Card(
        modifier = Modifier
            .padding(16.dp,)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(14.dp)
        ) {
            Text(
                text = "Days $days",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.h1
            )
            Image(
                painter = painterResource(id = quote.imageResource),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(4.dp))
                    .padding(bottom = 8.dp),
                contentScale = ContentScale.Crop,
            )
            Text(
                text = "\"${stringResource(id = quote.quoteResource)}\"",
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeWellnessAppTheme {
        TopBarApp(name = "Days Of Wellness")
    }
}