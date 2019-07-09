package com.example.app.features.guests

import com.arellomobile.mvp.MvpPresenter
import com.example.app.core.model.Guest

class GuestsPresenter : MvpPresenter<GuestsView>() {
    var guestsList: ArrayList<Guest> = ArrayList()
}