package com.example.app.features.friend_profile

import com.arellomobile.mvp.MvpView
import com.example.app.core.model.Event

interface FriendProfileView : MvpView {
    fun showMessage(s: String)
    fun onEventsLoaded(events: ArrayList<Event>)
}