package com.example.app.features.friend_profile.api

import retrofit2.http.POST
import retrofit2.http.Path

interface FriendProfileApi {
    @POST("/user/friends/{id}")
    fun addFriend(@Path("id") id: Long)
}