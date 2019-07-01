package com.example.app.features

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.app.moxy.MvpAndroidxFragment

abstract class BaseFragment : MvpAndroidxFragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(getLayoutID(), container, false)
    }

   //protected abstract fun injectDependencies()

    protected abstract fun getLayoutID(): Int
}
