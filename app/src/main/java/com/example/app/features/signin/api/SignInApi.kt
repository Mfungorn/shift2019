package com.example.app.features.signin.api

import com.example.app.core.model.User
import com.example.app.core.model.UserSignInPayload
import com.example.app.core.model.Wrapper
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.POST

interface SignInApi {

    @POST("login")
    fun authUserAsync(@Body user: Wrapper<UserSignInPayload>): Deferred<Wrapper<User>>
}