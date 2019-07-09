package com.example.app.features.event.api

import com.example.app.core.model.Event
import com.example.app.core.model.Wrapper
import retrofit2.http.GET
import retrofit2.http.Path

interface EventApi {
    @GET("/events/{id}")
    fun getEvent(@Path("id") id: Long) : Wrapper<Event>
}