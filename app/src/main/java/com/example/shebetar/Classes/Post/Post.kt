package com.example.shebetar.Classes.Post

import com.example.shebetar.Classes.Comment.Comment
import com.example.shebetar.Classes.User.User
import java.util.Date
import java.util.HashMap

data class Post(
    var id: Long,
    val authorId: Long,
    val text: String,
    val dateOfPublication: Date,
    val likesQuantity: Int,
    val usersLiked: List<Int>,
    val repostsQuantity: Int,
    val usersReposted: List<Int>,
    val commentsQuantity: Int,
    val comments: List<Int>,
    val viewsQuantity: Int
) {
    constructor() : this(
        0,
        0,
        "",
        Date(),
        0,
        emptyList(),
        0,
        emptyList(),
        0,
        emptyList(),
        0
    )
    fun toMap(): HashMap<String, Any> {
        val map = HashMap<String, Any>()
        map["id"] = id
        map["authorId"] = authorId
        map["text"] = text
        map["dateOfPublication"] = dateOfPublication
        map["likesQuantity"] = likesQuantity
        map["usersLiked"] = usersLiked
        map["repostsQuantity"] = repostsQuantity
        map["usersReposted"] = usersReposted
        map["commentsQuantity"] = commentsQuantity
        map["comments"] = comments
        map["viewsQuantity"] = viewsQuantity
        return map
    }
}