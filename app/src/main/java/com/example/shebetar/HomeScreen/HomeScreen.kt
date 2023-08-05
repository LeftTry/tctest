package com.example.shebetar.HomeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.IconButton
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

data class Post(val id: Int, val content: String, var likes: Int, val reposts: Int, val comments: Int, val views: Int)
var posts = listOf(
    Post(1, "Content of Post 1", 0, 0, 0, 0)
)

@Composable
fun HomeScreen(navController: NavHostController, scope: CoroutineScope, scaffoldState: ScaffoldState) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(color = MaterialTheme.colorScheme.primary)
        ) {
            IconButton(
                onClick = { scope.launch{ scaffoldState.drawerState.open() } }) {
                Icon(imageVector = Icons.Default.List, contentDescription = "LeftSideMenu",
                    tint = MaterialTheme.colorScheme.surface)
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(10.dp)
        ) {
            items(posts) { post ->
                PostItem(post)
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        FloatingActionButton(
            modifier = Modifier
                .align(alignment = Alignment.BottomEnd)
                .padding(all = 16.dp),
            onClick = {
                navController.navigate("postCreation")
            }) {
            Icon(
                Icons.Filled.Add,
                contentDescription = "Create post",
                tint = MaterialTheme.colorScheme.surface
            )
        }
    }
}

@Composable
fun PostItem(post: Post) {
    var like by remember { mutableStateOf(Icons.Default.FavoriteBorder) }
    var likes by remember { mutableStateOf(post.likes) }
    var liked = false
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = post.content,
                fontSize = 18.sp,
                style = TextStyle(fontSize = 14.sp)
            )
            Row (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp)
            ){
                IconButton(
                    modifier = Modifier.align(alignment = Alignment.CenterVertically),
                    onClick = {
                        if (!liked) {
                            like = Icons.Default.Favorite
                            liked = true
                            likes += 1
                        } else {
                            like = Icons.Default.FavoriteBorder
                            liked = false
                            likes -= 1
                        }
                    }) {
                    Icon(imageVector = like, contentDescription = "Likes")
                }
                Text(modifier = Modifier.align(alignment = Alignment.CenterVertically),
                    text = likes.toString())
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "Reposts")
                }
                Text(modifier = Modifier.align(alignment = Alignment.CenterVertically),
                    text = post.reposts.toString())
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Face, contentDescription = "Comments")
                }
                Text(modifier = Modifier.align(alignment = Alignment.CenterVertically),
                    text = post.comments.toString())
                Icon(modifier = Modifier
                    .align(alignment = Alignment.CenterVertically)
                    .padding(10.dp),
                    imageVector = Icons.Default.Info, contentDescription = "Views")
                Text(modifier = Modifier.align(alignment = Alignment.CenterVertically),
                    text = post.views.toString())
            }
        }
    }
}

fun createPost(text: String){
    val post = Post(posts.last().id, text, 0, 0, 0, 0)
    posts += post
}