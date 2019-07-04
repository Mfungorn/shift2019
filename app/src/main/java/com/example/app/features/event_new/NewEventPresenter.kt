package com.example.app.features.event_new

import com.arellomobile.mvp.MvpPresenter
import com.example.app.App
import com.example.app.core.model.Event
import com.example.app.core.model.EventPostPayload
import com.example.app.features.event_new.api.NewEventApi
import kotlinx.coroutines.*
import retrofit2.Retrofit
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class NewEventPresenter : MvpPresenter<NewEventView>(), CoroutineScope {
    override val coroutineContext: CoroutineContext =
        Dispatchers.Main + SupervisorJob()

    @Inject
    lateinit var retrofit: Retrofit

    private val api: NewEventApi

    init {
        App.INSTANCE.getAppComponent().inject(this)
        api = retrofit.create(NewEventApi::class.java)
    }

    fun createEvent(event: EventPostPayload) {
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

