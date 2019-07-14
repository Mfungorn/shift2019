package com.example.app.features

import android.content.SharedPreferences
import android.os.Bundle
import com.example.app.App
import com.example.app.R
import com.example.app.core.PreferencesApi
import com.example.app.features.sign.SignInFragment
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var prefs: SharedPreferences

    init {
        App.INSTANCE.getAppComponent().inject(this)
    }

    private var mainFlowFragment: MainFlowFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        val token = PreferencesApi.getJwt(prefs)
        if (token.isNullOrEmpty()) {
            supportFragmentManager.beginTransaction()
                .add(getContainerId(), SignInFragment.newInstance(), "SIGNIN")
                .commit()
        } else {
            mainFlowFragment = MainFlowFragment.newInstance()
            supportFragmentManager.beginTransaction()
                .replace(getContainerId(), mainFlowFragment!!, "MAIN_FRAGMENT_FLOW")
                .commit()
        }

    }

    override fun getLayoutId() = R.layout.activity_main

    override fun getContainerId() = R.id.main_container

    override fun onBackPressed() {
        //super.onBackPressed()
        if ((mainFlowFragment != null) and (mainFlowFragment!!.host != null)) {
            mainFlowFragment!!.childFragmentManager.popBackStack()
        }
    }
}
