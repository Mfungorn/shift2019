package com.example.app.features.friend_profile

import com.arellomobile.mvp.MvpPresenter
import com.example.app.App
import com.example.app.core.model.User
import com.example.app.features.friend_profile.api.FriendProfileApi
import kotlinx.coroutines.*
import retrofit2.Retrofit
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class FriendProfilePresenter : MvpPresenter<FriendProfileView>(), CoroutineScope {
    override val coroutineContext: CoroutineContext =
        Dispatchers.Main + SupervisorJob()

    @Inject
    lateinit var retrofit: Retrofit

    private val api: FriendProfileApi

    init {
        App.INSTANCE.getAppComponent().inject(this)
        api = retrofit.create(FriendProfileApi::class.java)
    }

    fun addFriend(user: User) {
        this.launch {
            try {
                withContext(Dispatchers.IO) {
                    api.addFriend(user.id)
                }
            } catch (t: Throwable) {
                viewState.showMessage("Не удалось добавить в друзья")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineContext[Job]!!.cancel()
    }
}