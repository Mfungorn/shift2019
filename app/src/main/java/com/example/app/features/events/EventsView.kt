package com.example.app.features.events

import com.arellomobile.mvp.MvpView
import com.example.app.core.model.Event

interface EventsView : MvpView {
    fun showMessage(s: String)
    fun onEventsLoaded(list : List<Event>)
}