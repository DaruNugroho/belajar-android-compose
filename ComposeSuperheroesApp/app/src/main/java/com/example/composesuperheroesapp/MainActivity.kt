package com.example.composesuperheroesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composesuperheroesapp.model.Hero
import com.example.composesuperheroesapp.model.HeroesRepository
import com.example.composesuperheroesapp.ui.theme.ComposeSuperheroesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeSuperheroesAppTheme {
                ComposeSuperheroApp()
            }
        }
    }
}

@Composable
private fun ComposeSuperheroApp() {
    val listSuperhero = HeroesRepository.heroes

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier.fillMaxWidth()
                    .size(56.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.h1,
                )
            }
        }
    ) {
        ListSuperhero(heroList = listSuperhero)
    }
}

@Composable
private fun ListSuperhero(heroList: List<Hero>) {
    LazyColumn(
        modifier = Modifier.background(MaterialTheme.colors.background) // ganti background latar belakang
    ){
        items(heroList) {
            HeroItem(hero = it)
        }
    }
}

@Composable
private fun HeroItem(modifier: Modifier = Modifier, hero: Hero) {
    Card(
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
        elevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .sizeIn(minHeight = 72.dp), // atur tinggi card
        ) {
            Column(
               modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = stringResource(id = hero.nameRes),
                    style = MaterialTheme.typography.h3,
                )
                Text(
                    text = stringResource(id = hero.descriptionRes),
                    style = MaterialTheme.typography.h3,
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box(
                modifier = Modifier
                    .size(74.dp)
                    .clip(RoundedCornerShape(8.dp))
            ) {
                Image(
                    painter = painterResource(id = hero.imageRes),
                    contentDescription = stringResource(id = R.string.description4),
                    alignment = Alignment.TopCenter,
                    contentScale = ContentScale.FillHeight
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeSuperheroesAppTheme(darkTheme = true) {
        ComposeSuperheroApp()
    }
}