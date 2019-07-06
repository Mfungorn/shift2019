package com.example.app.features.friend_profile.api

import com.example.app.core.model.Event
import com.example.app.core.model.Wrapper
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FriendProfileApi {
    @POST("/user/friends/{login}")
    fun addFriend(@Path("login") login: String)

    @GET("/user/friends/{login}/events")
    fun getProfileEvents(@Path("login") login: String) : Wrapper<ArrayList<Event>>
}