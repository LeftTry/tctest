package com.example.shebetar.Classes.Post

import android.graphics.Picture
import android.util.Log
import com.example.shebetar.Classes.Comment.Comment
import com.example.shebetar.Classes.User.User
import com.google.firebase.firestore.DocumentSnapshot
import java.util.Date
import java.util.HashMap

data class Post(
    var id: Long,
    var authorId: Long,
    var text: String,
    var dateOfPublication: Date,
    var likesQuantity: Int,
    var usersLiked: List<Int>,
    var repostsQuantity: Int,
    var usersReposted: List<Int>,
    var commentsQuantity: Int,
    var comments: List<Int>,
    var viewsQuantity: Int
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

    fun toPostFromDocumentSnapshot(documentSnapshot: DocumentSnapshot?){
        if (documentSnapshot?.exists() == true) {
            val data = documentSnapshot.data
            id = data?.get("id") as Long
            authorId = data["authorId"] as Long
            text = data["text"] as String
            dateOfPublication = data["dateOfPublication"] as Date
            likesQuantity = data["likesQuantity"] as Int
            usersLiked = data["usersLiked"] as List<Int>
            repostsQuantity = data["repostsQuantity"] as Int
            usersReposted = data["usersReposted"] as List<Int>
            commentsQuantity = data["commentsQuantity"] as Int
            comments = data["comments"] as List<Int>
            viewsQuantity = data["viewsQuantity"] as Int
            Log.d("toPostFromDocumentSnapshot", "Success")
        } else {
            Log.d("toPostFromDocumentSnapshot", "Failure")
        }
    }
}