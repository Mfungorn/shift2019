package com.example.app.features.friends

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.app.App
import com.example.app.features.friends.api.FriendsApi
import kotlinx.coroutines.*
import retrofit2.Retrofit
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@InjectViewState
class FriendsPresenter : MvpPresenter<FriendsView>(), CoroutineScope {
    override val coroutineContext: CoroutineContext =
        Dispatchers.Main + SupervisorJob()

    @Inject
    lateinit var retrofit: Retrofit

    private val api: FriendsApi

    init {
        App.INSTANCE.getAppComponent().inject(this)
        api = retrofit.create(FriendsApi::class.java)
    }

    fun searchFriends(query: String) {
        this.launch {
            try {
                val results = withContext(Dispatchers.IO) {
                    api.findFriendsAsync(query).await().data
                }
                viewState.onSearchResultsLoaded(results)
            } catch (t: Throwable) {
                viewState.showMessage("Не удалось выполнить поиск")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineContext[Job]!!.cancel()
    }
}