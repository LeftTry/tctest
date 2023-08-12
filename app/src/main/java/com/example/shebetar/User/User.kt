package com.example.shebetar.User

import android.graphics.Picture
import com.example.shebetar.Comment.Comment
import com.example.shebetar.Post.Post
import java.util.Date

// User class
data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val nickname: String,
    val email: String,
    val phone: String,
    val dateOfBirth: Date,
    val dateOfJoin: Date,
    val profilePicture: Picture,
    val backgroundPicture: Picture,
    val followersQuantity: Int,
    val followers: List<User>,
    val followingQuantity: Int,
    val following: List<User>,
    val posts: List<Post>,
    val likedPosts: List<Post>,
    val repostedPosts: List<Post>,
    val comments: List<Comment>
){
    constructor() : this(
        0,
        "",
        "",
        "",
        "",
        "",
        Date(),
        Date(),
        Picture(),
        Picture(),
        0,
        emptyList(),
        0,
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList()
    )
}
