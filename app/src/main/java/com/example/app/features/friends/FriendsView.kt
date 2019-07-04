package com.example.app.features.friends

import com.arellomobile.mvp.MvpView
import com.example.app.core.model.User

interface FriendsView : MvpView {
    fun showMessage(s: String)
    fun onSearchStarted(query: String)
    fun onSearchResultsLoaded(results: ArrayList<User>)
}