package com.example.shebetar

import android.graphics.Picture
import com.example.shebetar.HomeScreen.Post
import java.util.Date

class User(
    private var id:             Int,
    private var nickname:       String,
    private var name:           String,
    private var surname:        String,
    private var bio:            String,
    private var dateOfBirth:    Date,
    private var dateOfJoin:     Date,
    private var profilePicture: Picture,
    private var profileBanner:  Picture,
    private var posts:          List<Post>,
    private var followers:      List<User>,
    private var following:      List<User>
) {
    // Getters
    fun getId(): Int {
        return id
    }

    fun getNickname(): String {
        return nickname
    }

    fun getName(): String {
        return name
    }

    fun getSurname(): String {
        return surname
    }

    fun getBio(): String {
        return bio
    }

    fun getDateOfBirth(): Date {
        return dateOfBirth
    }

    fun getDateOfJoin(): Date {
        return dateOfJoin
    }

    fun getProfilePicture(): Picture {
        return profilePicture
    }

    fun getProfileBanner(): Picture {
        return profileBanner
    }

    fun getPosts(): List<Post> {
        return posts
    }

    fun getFollowers(): List<User> {
        return followers
    }

    fun getFollowing(): List<User> {
        return following
    }

    // Setters
    fun setId(id: Int) {
        this.id = id
    }

    fun setNickname(nickname: String) {
        this.nickname = nickname
    }

    fun setName(name: String) {
        this.name = name
    }

    fun setSurname(surname: String) {
        this.surname = surname
    }

    fun setBio(bio: String) {
        this.bio = bio
    }

    fun setDateOfBirth(dateOfBirth: Date) {
        this.dateOfBirth = dateOfBirth
    }

    fun setDateOfJoin(dateOfJoin: Date) {
        this.dateOfJoin = dateOfJoin
    }

    fun setProfilePicture(profilePicture: Picture) {
        this.profilePicture = profilePicture
    }

    fun setProfileBanner(profileBanner: Picture) {
        this.profileBanner = profileBanner
    }

    fun setPosts(posts: List<Post>) {
        this.posts = posts
    }

    fun setFollowers(followers: List<User>) {
        this.followers = followers
    }

    fun setFollowing(following: List<User>) {
        this.following = following
    }
}