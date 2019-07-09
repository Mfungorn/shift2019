package com.example.app.features.map

import com.arellomobile.mvp.MvpView

interface MapFragmentView: MvpView {
    fun showMessage(s: String)
}
