package com.example.composecourseapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composecourseapp.data.Datasource.topics
import com.example.composecourseapp.model.Topic
import com.example.composecourseapp.ui.theme.ComposeCourseAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCourseAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.padding(16.dp).fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                   // ComposeCourseApp()
                    TopicGrid()
                }
            }
        }
    }
}

@Composable
private fun ComposeCourseApp() {
    // ItemTopic(topic = Topic(R.string.architecture, 10, R.drawable.architecture))
    TopicCourseList(topicList = topics)
}

@Composable
private fun TopicCourseList(topicList: List<Topic>) {
    LazyColumn {
        items(topicList) { topic -> 
            TopicCard(topic = topic)
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TopicGrid(modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(8.dp)
    ) {
        items(topics) { topic ->
            TopicCard(topic)
        }
    }
}

@Composable
private fun TopicCard(topic: Topic, modifier: Modifier = Modifier) {

    val imageResourceId = topic.imageResourceId
    val stringResourceId = topic.stringResourceId

    Card(
        elevation = 4.dp,
        backgroundColor = Color.Cyan
    ) {
        Row {
            Image(
                painter = painterResource(id = imageResourceId),
                contentDescription = stringResource(id = stringResourceId),
                modifier = Modifier
                    .size(height = 68.dp, width = 68.dp)
                    .aspectRatio(1f), // mengatur rasio gambar
                contentScale = ContentScale.Crop
            )
            Column {
                Text(
                    text = stringResource(id = stringResourceId),
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 16.dp,
                        end = 16.dp,
                        bottom = 8.dp
                    )
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_grain),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 8.dp)
                            .size(12.dp)
                    )
                    Text(
                        text = topic.Course.toString(),
                        style = MaterialTheme.typography.caption,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeCourseAppTheme {
        val topic = Topic(R.string.photography, 321, R.drawable.photography)
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopicCard(topic = topic)
        }
    }
}