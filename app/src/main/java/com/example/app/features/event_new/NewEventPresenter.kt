package com.example.app.features.event_new

import android.content.SharedPreferences
import com.arellomobile.mvp.MvpPresenter
import com.example.app.App
import com.example.app.core.PreferencesApi
import com.example.app.core.model.*
import com.example.app.features.event_new.api.NewEventApi
import kotlinx.coroutines.*
import retrofit2.Retrofit
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList
import kotlin.coroutines.CoroutineContext

class NewEventPresenter : MvpPresenter<NewEventView>(), CoroutineScope {
    override val coroutineContext: CoroutineContext =
        Dispatchers.Main + SupervisorJob()

    @Inject
    lateinit var retrofit: Retrofit

    private val api: NewEventApi

    @Inject
    lateinit var prefs: SharedPreferences

    var currentTime = Calendar.getInstance()!!

    var event = EventPostPayload(
        PreferencesApi.getUser(prefs)!!,
        "",
        "",
        .0,
        .0,
        currentTime.time,
        ArrayList<User>(),
        ArrayList<Expense>())

    init {
        App.INSTANCE.getAppComponent().inject(this)
        api = retrofit.create(NewEventApi::class.java)
    }

    fun createEvent() {
        this.launch {
            try {
                withContext(Dispatchers.IO) {
                    api.createEvent(event)
                }
            } catch (t: Throwable) {
                viewState.showMessage("Не удалось создать мероприятие")
            }
        }
    }
}

