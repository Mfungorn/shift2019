package com.example.app.features.event

import com.arellomobile.mvp.MvpView
import com.example.app.core.model.Event

interface EventView : MvpView {
    fun onEventLoadStart(id: Long)
    fun onEventLoaded(event: Event)
    fun showMessage(s: String)
}