package com.example.app.core

import android.content.SharedPreferences
import android.security.keystore.UserNotAuthenticatedException
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class BasicAuthInterceptor : Interceptor {
    @Inject
    lateinit var prefs: SharedPreferences

    private var token = PreferencesApi.getJwt(prefs)

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return if (request.method() == "POST" && (
                    request.url().encodedPathSegments().contains("auth")
                            or
                            request.url().encodedPathSegments().contains("signup"))
        )
            chain.proceed(request)
        else
            if (token != null) {
                val authenticatedRequest = request.newBuilder()
                    .header("Authorization", token!!).build()
                chain.proceed(authenticatedRequest)
            } else throw UserNotAuthenticatedException()
    }
}