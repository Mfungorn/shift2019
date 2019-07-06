package com.example.app.features.friends_select

import com.arellomobile.mvp.MvpPresenter
import com.example.app.core.model.User

class FriendsSelectPresenter : MvpPresenter<FriendsSelectView>() {
    var selectedFriendsList: ArrayList<User> = ArrayList()

    fun addSelectedFriend(friend: User) {
        selectedFriendsList.add(friend)
    }

    fun removeSelectedFriend(friend: User) {
        selectedFriendsList.remove(friend)
    }
}