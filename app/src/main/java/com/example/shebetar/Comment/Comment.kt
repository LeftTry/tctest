package com.example.shebetar.Comment

import android.view.ViewParent
import com.example.shebetar.User.User

data class Comment(
    val author: User,
    val text: String,
    val likesQuantity: Int,
    val usersLiked: List<User>,
    val parent: Comment?,
    val childs: List<Comment>
)
