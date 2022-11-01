package com.example.composelemonadeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composelemonadeapp.ui.theme.ComposeLemonadeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLemonadeAppTheme {
              LemonadeApp()
            }
        }
    }
}


@Composable
fun LemonadeApp() {
    var countStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when(countStep) {
            1 -> {
                LemonadeAppWithImage(
                    descText = changeDesc(countStep),
                    imageResourceId = R.drawable.lemon_tree,
                    onImageClick = {
                        countStep = 2
                        squeezeCount = (2..4).random()
                    }
                )
            }
            2 -> {
                LemonadeAppWithImage(
                    descText = changeDesc(countStep),
                    imageResourceId = R.drawable.lemon_squeeze,
                    onImageClick = {
                        squeezeCount--
                        if (squeezeCount == 0) {
                            countStep = 3
                        }
                    }
                )
            }
            3 -> {
                LemonadeAppWithImage(
                    descText = changeDesc(countStep),
                    imageResourceId = R.drawable.lemon_drink,
                    onImageClick = {
                        countStep = 4
                    }
                )
            }
            4 -> {
                LemonadeAppWithImage(
                    descText = changeDesc(countStep),
                    imageResourceId = R.drawable.lemon_restart,
                    onImageClick = {
                        countStep = 1
                    }
                )
            }
        }
    }
}

@Composable
fun LemonadeAppWithImage(
    modifier: Modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center),
    descText: Int,
    imageResourceId: Int,
    onImageClick : () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = stringResource(id = descText), fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = imageResourceId),
            contentDescription = stringResource(id = descText),
            modifier = Modifier
                .border(
                    BorderStroke(2.dp, Color(105, 205, 216)),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(16.dp)
                .clickable(onClick = onImageClick)
        )
    }
}

fun changeImage(result: Int) : Int {
    return when(result) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
}

fun changeDesc(result: Int) : Int {
    return when(result) {
        1 -> R.string.lemon_tree
        2 -> R.string.lemon
        3 -> R.string.glass_of_lemonade
        else -> R.string.empty_glass
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeLemonadeAppTheme {
        LemonadeApp()
    }
}