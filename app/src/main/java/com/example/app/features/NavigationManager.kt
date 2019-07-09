package com.example.app.features

import com.example.app.core.model.Guest
import com.example.app.core.model.User

interface NavigationManager {
    fun showNewEventFragment()
    fun showEventsFragment()
    fun showEventFragment(id: Long)
    fun showFriendProfileFragment(friend: User)
    fun showUserFriendsFragment(user: User)
    fun showFriendsSelectFragment(selectedFriendsList: List<User>)
    fun showUserProfileFragment(user: User)
    fun showGuestsFragment(guests: ArrayList<Guest>)
}