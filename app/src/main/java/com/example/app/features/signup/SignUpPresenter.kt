package com.example.app.features.signup

import android.content.SharedPreferences
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.app.App
import com.example.app.core.PreferencesApi
import com.example.app.core.model.UserSignUpPayload
import com.example.app.features.signup.api.SignUpApi
import kotlinx.coroutines.*
import retrofit2.Retrofit
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@InjectViewState
class SignUpPresenter : MvpPresenter<SingUpView>(), CoroutineScope {

    override val coroutineContext: CoroutineContext =
        Dispatchers.Main + SupervisorJob()

    @Inject
    lateinit var retrofit: Retrofit

    @Inject
    lateinit var prefs: SharedPreferences


    private val api: SignUpApi

    init {
        App.INSTANCE.getAppComponent().inject(this)
        api = retrofit.create(SignUpApi::class.java)
    }

    var name = ""
    var password = ""
    var login = ""

    fun createUser() {
        if (name.isNotBlank() && password.isNotBlank() && login.isNotBlank())
            this.launch {
                try {
                    val response = withContext(Dispatchers.IO) {
                        api.createUser(UserSignUpPayload(login, name, password))
                    }.data
                    PreferencesApi.setUser(prefs, response)
                    viewState.onUserSignUp()
                } catch (t: Throwable) {
                    viewState.showMessage("Не удалось зарегистрировать пользователя")
                }
            }
            /*
            api.createUser(UserSignUpPayload(login, name, password)).enqueue(object : Callback<Wrapper<User>>{
                override fun onFailure(call: Call<Wrapper<User>>, t: Throwable) {

                }

                override fun onResponse(call: Call<Wrapper<User>>, response: Response<Wrapper<User>>) {
                    PreferencesApi.setUser(prefs, response.body()!!.data)
                    viewState.onUserSignUp()
                }

            })
            */
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineContext[Job]!!.cancel()
    }
}