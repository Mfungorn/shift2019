package com.example.app.features.signup.api

import com.example.app.core.model.User
import com.example.app.core.model.UserSignUpPayload
import com.example.app.core.model.Wrapper
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpApi {

    @POST("users/signup")
    fun createUser(@Body user: Wrapper<UserSignUpPayload>): Wrapper<User>
}