package com.example.app.features.events.api

import com.example.app.core.model.Event
import com.example.app.core.model.Wrapper
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface EventsApi {
    @GET("/events/")
    fun getEventsAsync() : Deferred<Wrapper<ArrayList<Event>>>
}