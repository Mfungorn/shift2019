package com.example.app.features.event_new

import com.arellomobile.mvp.MvpView

interface NewEventView : MvpView {
    fun showMessage(s: String)
}