package com.example.app.features.signin

import android.content.SharedPreferences
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.app.App
import com.example.app.core.PreferencesApi
import com.example.app.core.model.UserSignInPayload
import com.example.app.core.model.Wrapper
import com.example.app.features.signin.api.SignInApi
import kotlinx.coroutines.*
import retrofit2.Retrofit
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@InjectViewState
class SignInPresenter: MvpPresenter<SignInView>(), CoroutineScope {

    var login = ""
    var password = ""

    override val coroutineContext: CoroutineContext =
        Dispatchers.Main + SupervisorJob()

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
            this.launch {
                try {
                    val response = withContext(Dispatchers.IO) {
                        api.authUserAsync(Wrapper(
                            "OK",
                            UserSignInPayload(login, password))
                        ).await().data
                    }

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

    override fun onDestroy() {
        super.onDestroy()
        coroutineContext[Job]!!.cancel()
    }
}