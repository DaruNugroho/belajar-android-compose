package com.example.composewoofapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composewoofapp.data.Dog
import com.example.composewoofapp.data.dogs
import com.example.composewoofapp.ui.theme.ComposeWoofAppTheme
import com.example.composewoofapp.ui.theme.Green50

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeWoofAppTheme {
              WoofApp()
            }
        }
    }
}

@Composable
fun WoofApp() {
    Scaffold(
        topBar = {
            WoofTopAppBar()
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
        ){
            items(dogs) { dog ->
                DogItem(dog = dog)
            }
        }
    }
}

@Composable
fun WoofTopAppBar() {
    Row(
        modifier = Modifier
            .background(color = MaterialTheme.colors.primary)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_woof_logo),
            contentDescription = null,
            modifier = Modifier
                .size(64.dp)
                .padding(8.dp)
        )
        Text(text = stringResource(id = R.string.app_name), style = MaterialTheme.typography.h1,)
    }
}


@Composable
fun DogItem(
    dog: Dog,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }
    val color by animateColorAsState(targetValue = if (expanded) Color.Yellow else MaterialTheme.colors.surface)

    Card(
        modifier = modifier.padding(8.dp),
        elevation = 4.dp // menambahkan bayangan agar aplikasi terlihat realistis
    ) {
        Column(
            modifier = Modifier
                .animateContentSize( // menambahkan animasi pada expanded
                    animationSpec = spring( // menambahkan animasi pegas/spring
                        dampingRatio = Spring.DampingRatioMediumBouncy, // menentukan seberapa mantulnya spring
                        stiffness = Spring.StiffnessLow // menentukan kecepatan daya kembali pegas
                    )
                )
                .background(color) // mengganti warna background saat item di expand
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),

                ) {
                DogIcon(dog.imageResourcesId)
                DogInformation(dog.name, dog.age)
                Spacer(modifier = Modifier.weight(1f)) // untuk sejajarkan tombol expanded
                DogItemButton(
                    expanded = expanded, 
                    onClick = { expanded = !expanded }
                )
            }
            if (expanded) {
                DogHobby(dogHobby = dog.hobbies)
            }
        }
    }
}

@Composable
fun DogIcon(
    @DrawableRes dogIcon: Int,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .size(64.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(50)), // membentuk lingkaran pada gambar
        contentScale = ContentScale.Crop, // untuk membuat semua foto agar pas dengan shape (lingkaran)
        painter = painterResource(dogIcon),
        contentDescription = null
    )
}

@Composable
fun DogInformation(@StringRes dogName: Int, dogAge: Int, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = stringResource(dogName),
            modifier = modifier.padding(top = 8.dp),
            style = MaterialTheme.typography.h2
        )
        Text(
            text = stringResource(R.string.years_old, dogAge),
            style = MaterialTheme.typography.body1
        )
    }
}

@Composable
private fun DogItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick
    ) {
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = stringResource(id = R.string.expand_button_content_description),
            tint = MaterialTheme.colors.secondary
        )
    }
}

@Composable
fun DogHobby(
    @StringRes dogHobby: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(
            start = 16.dp,
            top = 8.dp,
            bottom = 16.dp,
            end = 16.dp
        )
    ) {
        Text(
            text = stringResource(id = R.string.about),
            style = MaterialTheme.typography.h3
        )
        Text(
            text = stringResource(id = dogHobby),
            style = MaterialTheme.typography.body1
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeWoofAppTheme(darkTheme = false) {
        WoofApp()
    }
}

// untuk priview tema gelap
@Preview
@Composable
fun DarkThemePreview() {
    ComposeWoofAppTheme(darkTheme = true) {
        WoofApp()
    }
}