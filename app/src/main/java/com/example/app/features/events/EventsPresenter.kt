package com.example.app.features.events

import android.content.SharedPreferences
import com.arellomobile.mvp.MvpPresenter
import com.example.app.App
import com.example.app.features.events.api.EventsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import retrofit2.Retrofit
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class EventsPresenter : MvpPresenter<EventsView>(), CoroutineScope {
    override val coroutineContext: CoroutineContext =
        Dispatchers.Main + SupervisorJob()

    @Inject
    lateinit var retrofit: Retrofit

    @Inject
    lateinit var prefs: SharedPreferences

    private val api: EventsApi

    init {
        App.INSTANCE.getAppComponent().inject(this)
        api = retrofit.create(EventsApi::class.java)
    }


}