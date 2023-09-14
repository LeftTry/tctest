package com.example.shebetar.Classes.Comment

import android.view.ViewParent
import com.example.shebetar.Classes.User.User

data class Comment(
    val author: Int,
    val text: String,
    val likesQuantity: Int,
    val usersLiked: List<Int>,
    val parent: Comment?,
    val children: List<Int>
)
