package com.example.shebetar.Classes.Post

import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import java.util.Date

data class Post(
    var id: Long,
    var authorId: Long,
    var text: String,
    var dateOfPublication: Date,
    var likesQuantity: Long,
    var usersLiked: List<Long>,
    var repostsQuantity: Long,
    var usersReposted: List<Long>,
    var commentsQuantity: Long,
    var comments: List<Long>,
    var viewsQuantity: Long
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
            dateOfPublication = documentSnapshot.getTimestamp("dateOfPublication")?.toDate()!!
            likesQuantity = data["likesQuantity"] as Long
            usersLiked = data["usersLiked"] as List<Long>
            repostsQuantity = data["repostsQuantity"] as Long
            usersReposted = data["usersReposted"] as List<Long>
            commentsQuantity = data["commentsQuantity"] as Long
            comments = data["comments"] as List<Long>
            viewsQuantity = data["viewsQuantity"] as Long
            Log.d("toPostFromDocumentSnapshot", "Success")
        } else {
            Log.d("toPostFromDocumentSnapshot", "Failure")
        }
    }
}