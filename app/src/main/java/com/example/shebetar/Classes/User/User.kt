package com.example.shebetar.Classes.User

import android.graphics.Picture
import android.util.Log
import com.example.shebetar.Classes.Comment.Comment
import com.example.shebetar.Classes.Post.Post
import com.google.firebase.firestore.DocumentSnapshot
import java.util.Date
import java.util.HashMap

// User class
data class User(
    var id: Long,
    var firstName: String,
    var lastName: String,
    var nickname: String,
    var email: String,
    var phone: String,
    var password: String,
    var dateOfBirth: Date?,
    var dateOfJoin: Date,
    var profilePicture: Picture,
    var backgroundPicture: Picture,
    var followersQuantity: Long,
    var followers: List<User>,
    var followingQuantity: Long,
    var following: List<User>,
    var posts: List<Post>,
    var likedPosts: List<Post>,
    var repostedPosts: List<Post>,
    var comments: List<Comment>
){
    constructor() : this(
        0,
        "",
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
    constructor(firstName: String, lastName: String, nickname: String, email: String, phone: String, password: String, dateOfBirth: Date?): this(
        0,
        firstName,
        lastName,
        nickname,
        email,
        phone,
        password,
        dateOfBirth,
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
        map["password"] = password
        map["dateOfBirth"] = dateOfBirth ?: ""
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

    fun toUserFromQuery(query: DocumentSnapshot?){
        if (query?.exists() == true) {
            val data = query.data
            id = data?.get("id") as Long
            firstName = data["firstName"] as String
            lastName = data["lastName"] as String
            nickname = data["nickname"] as String
            email = data["email"] as String
            phone = data["phone"] as String
            password = data["password"] as String
            dateOfBirth = query.getTimestamp("dateOfBirth")?.toDate()
            dateOfJoin = query.getTimestamp("dateOfJoin")?.toDate()!!
            profilePicture = Picture()
            backgroundPicture = Picture()
            followersQuantity = data["followersQuantity"] as Long
            followers = data["followers"] as List<User>
            followingQuantity = data["followingQuantity"] as Long
            following = data["following"] as List<User>
            posts = data["posts"] as List<Post>
            likedPosts = data["likedPosts"] as List<Post>
            repostedPosts = data["repostedPosts"] as List<Post>
            comments = data["comments"] as List<Comment>
            Log.d("DeviceUserGetMethod", "found")
        } else {
            Log.d("DeviceUserGetMethod", "not found")
        }
    }
}
