package com.example.app.core

import android.content.SharedPreferences
import okhttp3.Interceptor
import okhttp3.Response

class BasicAuthInterceptor(val prefs: SharedPreferences) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return if (request.method() == "POST" && (
                    request.url().encodedPath().contains("login")
                            or
                            request.url().encodedPath().contains("signup"))
        ) {
            val response = chain.proceed(request)
            val header =  response.header("Authorization")
            PreferencesApi.setJwt(prefs, header ?: "")
            return response
        }
        else {
            val token = PreferencesApi.getJwt(prefs) ?: ""
            if (token.isNotEmpty()) {
                val authenticatedRequest = request.newBuilder()
                    .header("Authorization", token).build()
                chain.proceed(authenticatedRequest)
            } else throw com.example.app.core.exceptions.UserNotAuthenticatedException()
        }
    }
}