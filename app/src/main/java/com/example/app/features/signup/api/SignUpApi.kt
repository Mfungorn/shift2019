package com.example.app.features.signup.api

import com.example.app.core.model.User
import com.example.app.core.model.UserSignUpPayload
import com.example.app.core.model.Wrapper
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpApi {

    @POST("user/signup")
    fun createUserAsync(@Body user: Wrapper<UserSignUpPayload>): Deferred<Wrapper<User>>
}