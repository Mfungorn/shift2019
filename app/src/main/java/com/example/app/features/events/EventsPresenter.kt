package com.example.app.features.events

import android.content.SharedPreferences
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.app.App
import com.example.app.features.events.api.EventsApi
import kotlinx.coroutines.*
import retrofit2.Retrofit
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@InjectViewState
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

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getEvents()
    }

    private fun getEvents() {
        this.launch {
            try {
                val events = withContext(Dispatchers.IO) {
                    api.getEventsAsync().await().data
                }
                viewState.onEventsLoaded(events)
            } catch (t : Throwable) {
                viewState.showMessage("Не удалось загрузить мероприятия")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineContext[Job]!!.cancel()
    }
}