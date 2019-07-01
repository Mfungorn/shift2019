package com.example.app.features.signin

import com.arellomobile.mvp.MvpView

interface SignInView: MvpView {
    fun showMessage(s: String)
    fun onUserAuthenticate()
}