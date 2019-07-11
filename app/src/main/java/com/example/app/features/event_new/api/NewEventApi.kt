package com.example.app.features.event_new.api

import com.example.app.core.model.Event
import com.example.app.core.model.EventPostPayload
import com.example.app.core.model.Wrapper
import kotlinx.coroutines.Deferred
import retrofit2.http.POST

interface NewEventApi {
    @POST("/events")
    fun createEventAsync(event: EventPostPayload) : Deferred<Wrapper<Event>>
}