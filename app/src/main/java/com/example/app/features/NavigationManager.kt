package com.example.app.features

import com.example.app.core.model.User

interface NavigationManager {
    fun showNewEventFragment()
    fun showEventsFragment()
    fun showFriendProfileFragment(friend: User)
    fun showUserFriendsFragment(user: User)
    fun showFriendsSelectFragment(selectedFriendsList: ArrayList<User>)
    fun showUserProfileFragment(user: User)
}