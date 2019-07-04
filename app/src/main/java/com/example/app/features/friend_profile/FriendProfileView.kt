package com.example.app.features.friend_profile

import com.arellomobile.mvp.MvpView

interface FriendProfileView : MvpView {
    fun showMessage(s: String)
}