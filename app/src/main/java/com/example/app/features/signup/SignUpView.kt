package com.example.app.features.signup

import com.arellomobile.mvp.MvpView

interface SignUpView: MvpView {
    fun showMessage(s: String)
    fun onUserSignUp()
}
