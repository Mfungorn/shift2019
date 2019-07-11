package com.example.app.features.event.api

import com.example.app.core.model.Event
import com.example.app.core.model.Wrapper
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface EventApi {
    @GET("/events/{id}")
    fun getEventAsync(@Path("id") id: Long) : Deferred<Wrapper<Event>>
}