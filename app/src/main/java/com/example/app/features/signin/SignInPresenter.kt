package com.example.app.features.signin

import android.content.SharedPreferences
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.app.App
import com.example.app.core.PreferencesApi
import com.example.app.core.model.UserSignInPayload
import com.example.app.features.signin.api.SignInApi
import kotlinx.coroutines.*
import retrofit2.Retrofit
import javax.inject.Inject

@InjectViewState
class SignInPresenter: MvpPresenter<SignInView>(){

    var login = ""
    var password = ""

    private val uiScope = CoroutineScope(Dispatchers.Main)
    private val ioDispatcher : CoroutineDispatcher = Dispatchers.IO

    @Inject
    lateinit var retrofit: Retrofit

    @Inject
    lateinit var prefs:SharedPreferences

    private val api: SignInApi

    init {
        App.INSTANCE.getAppComponent().inject(this)
        api = retrofit.create(SignInApi::class.java)
    }

    fun signInUser() {
        if (login.isNotBlank() && password.isNotBlank())
            uiScope.launch {
                try {
                    val response = withContext(ioDispatcher) {
                        api.authUser(UserSignInPayload(login, password))
                    }.data
                    PreferencesApi.setUser(prefs, response)
                    viewState.onUserAuthenticate()
                } catch (t: Throwable) {
                    viewState.showMessage("Неверные данные")
                }
            }
            /*
           api.authUser(UserSignInPayload(login, password)).enqueue(object : Callback<Wrapper<User>>{
                override fun onFailure(call: Call<Wrapper<User>>, t: Throwable) {
                    viewState.showMessage("Неверные данные")
                }

                override fun onResponse(call: Call<Wrapper<User>>, response: Response<Wrapper<User>>) {
                    PreferencesApi.setUser(prefs, response.body()!!.data)
                    viewState.onUserAuthenticate()
                }
            })
            */

    }
}