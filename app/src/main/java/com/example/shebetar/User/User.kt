package com.example.shebetar.User

import android.graphics.Picture
import com.example.shebetar.Comment.Comment
import com.example.shebetar.Post.Post
import java.util.Date
import java.util.HashMap

// User class
data class User(
    var id: Int,
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
    fun toMap(): HashMap<String, Any> {
        val map = HashMap<String, Any>()
        map["id"] = id
        map["firstName"] = firstName
        map["lastName"] = lastName
        map["nickname"] = nickname
        map["email"] = email
        map["phone"] = phone
        map["dateOfBirth"] = dateOfBirth
        map["dateOfJoin"] = dateOfJoin
        map["profilePicture"] = profilePicture
        map["backgroundPicture"] = backgroundPicture
        map["followersQuantity"] = followersQuantity
        map["followers"] = followers
        map["followingQuantity"] = followingQuantity
        map["following"] = following
        map["posts"] = posts
        map["likedPosts"] = likedPosts
        map["repostedPosts"] = repostedPosts
        map["comments"] = comments
        return map
    }
}
