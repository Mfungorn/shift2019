package com.example.app.features.signup

import android.content.SharedPreferences
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.app.App
import com.example.app.core.PreferencesApi
import com.example.app.core.model.User
import com.example.app.core.model.UserSignUpPayload
import com.example.app.core.model.Wrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import com.example.app.features.signup.api.SignUpApi
import javax.inject.Inject

@InjectViewState
class SignUpPresenter : MvpPresenter<SingUpView>() {


    @Inject
    lateinit var retrofit: Retrofit

    @Inject
    lateinit var prefs: SharedPreferences


    private val signUpApi: SignUpApi

    init {
        App.INSTANCE.getAppComponent().inject(this)
        signUpApi = retrofit.create(SignUpApi::class.java)
    }

    var name = ""
    var password = ""
    var login = ""

    fun createUser(){
        if (name.isNotBlank() && password.isNotBlank() && login.isNotBlank())
            signUpApi.createUser(UserSignUpPayload(login, name, password)).enqueue(object : Callback<Wrapper<User>>{
                override fun onFailure(call: Call<Wrapper<User>>, t: Throwable) {

                }

                override fun onResponse(call: Call<Wrapper<User>>, response: Response<Wrapper<User>>) {
                    PreferencesApi.setUser(prefs, response.body()!!.data)
                    viewState.onUserSignUp()
                }

            })
    }
}