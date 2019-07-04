package com.example.app.features.friends.api

import com.example.app.core.model.User
import com.example.app.core.model.Wrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface FriendsApi {
    @GET("/user/friends")
    fun findFriends(@Query("q") query: String) : Wrapper<ArrayList<User>>
}