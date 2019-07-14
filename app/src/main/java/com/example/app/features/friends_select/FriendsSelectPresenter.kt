package com.example.app.features.friends_select

import android.content.SharedPreferences
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.app.App
import com.example.app.core.model.User
import javax.inject.Inject

@InjectViewState
class FriendsSelectPresenter : MvpPresenter<FriendsSelectView>() {
    @Inject
    lateinit var prefs: SharedPreferences

    init {
        App.INSTANCE.getAppComponent().inject(this)
    }

    var selectedFriendsList: ArrayList<User> = ArrayList()

    fun addSelectedFriend(friend: User) {
        selectedFriendsList.add(friend)
    }

    fun removeSelectedFriend(friend: User) {
        selectedFriendsList.remove(friend)
    }
}