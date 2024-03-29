package com.example.app.features.guests

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.app.core.model.Guest

@InjectViewState
class GuestsPresenter : MvpPresenter<GuestsView>() {
    var guestsList: ArrayList<Guest> = ArrayList()
}