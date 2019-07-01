package com.example.app

import android.app.Application
import android.content.Context
import com.example.app.core.AppComponent
import com.example.app.core.DaggerAppComponent
import com.example.app.core.modules.SharedPreferencesModule

class App: Application() {

    private lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        component = DaggerAppComponent.builder()
            .sharedPreferencesModule(SharedPreferencesModule(this))
            .build()

    }

    fun getAppComponent(): AppComponent {
        return component
    }

    companion object {

        private fun getApp(context: Context): App {
            return context.applicationContext as App
        }

        lateinit var INSTANCE: App


    }
}