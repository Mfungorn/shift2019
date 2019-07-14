package com.example.app.features.event

import android.content.SharedPreferences
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.app.App
import com.example.app.core.model.Event
import com.example.app.features.event.api.EventApi
import kotlinx.coroutines.*
import retrofit2.Retrofit
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@InjectViewState
class EventPresenter : MvpPresenter<EventView>(), CoroutineScope {
    override val coroutineContext: CoroutineContext =
        Dispatchers.Main + SupervisorJob()

    @Inject
    lateinit var retrofit: Retrofit

    private val api: EventApi

    @Inject
    lateinit var prefs: SharedPreferences

    lateinit var event: Event

    init {
        App.INSTANCE.getAppComponent().inject(this)
        api = retrofit.create(EventApi::class.java)
    }

    fun getEvent(id: Long) {
        this.launch {
            try {
                event = withContext(Dispatchers.IO) {
                    api.getEventAsync(id).await().data
                }
                viewState.onEventLoaded(event)
            } catch (t: Throwable) {
                viewState.showMessage("Не удалось получить мероприятие")
            }
        }
    }
}

