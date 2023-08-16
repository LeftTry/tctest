package com.example.shebetar.Classes.Post

import com.example.shebetar.Classes.Comment.Comment
import com.example.shebetar.Classes.User.User
import java.util.Date

data class Post(
    val id: Int,
    val author: User,
    val text: String,
    val dateOfPublication: Date,
    val likesQuantity: Int,
    val usersLiked: List<User>,
    val repostsQuantity: Int,
    val usersReposted: List<User>,
    val commentsQuantity: Int,
    val comments: List<Comment>,
    val viewsQuantity: Int
)
