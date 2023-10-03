package com.example.shebetar.HomeScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shebetar.Classes.Post.Post
import com.example.shebetar.Classes.User.User
import java.util.Date

var posts = listOf(
    Post(0, User().id, "Content of Post 0", Date(), 0, emptyList(), 0, emptyList(), 0, emptyList(), 0)
)

@Composable
fun PostItem(post: Post) {
    var like by remember { mutableStateOf(Icons.Default.FavoriteBorder) }
    var likes by remember { mutableLongStateOf(post.likesQuantity) }
    var liked = false
    val user = User()
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "@ ${
                    user.nickname
                    }",
                fontSize = 18.sp,
                style = TextStyle(fontSize = 14.sp)
            )
            Text(
                text = post.text,
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
                    text = post.repostsQuantity.toString())
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
                    text = post.viewsQuantity.toString())
            }
        }
    }
}
