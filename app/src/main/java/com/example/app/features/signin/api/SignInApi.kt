package com.example.app.features.signin.api

import com.example.app.core.model.User
import com.example.app.core.model.UserSignInPayload
import com.example.app.core.model.Wrapper
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignInApi {

    @POST("users/auth")
    fun authUser(@Body user: UserSignInPayload): Call<Wrapper<User>>
}