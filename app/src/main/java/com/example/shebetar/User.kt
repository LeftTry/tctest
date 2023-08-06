package com.example.shebetar

import android.graphics.Picture
import com.example.shebetar.HomeScreen.Post
import java.util.Date

class User(
    val id: Int,
    val nickname: String,
    val name: String,
    val surname: String,
    val bio: String,
    val dateOfBirth: Date,
    val dateOfJoin: Date,
    val profilePicture: Picture,
    val profileBanner: Picture,
    val posts: List<Post>,
    val followers: List<User>,
    val following: List<User>
    ) {

}