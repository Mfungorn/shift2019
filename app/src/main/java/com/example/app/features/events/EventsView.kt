package com.example.app.features.events

import com.arellomobile.mvp.MvpView

interface EventsView : MvpView {
    fun showMessage(s: String)
}