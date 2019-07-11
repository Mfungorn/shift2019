package com.example.app.features

import android.os.Bundle
import com.example.app.R
import com.example.app.features.sign.SignInFragment

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        supportFragmentManager.beginTransaction()
            .add(getContainerId(), SignInFragment.newInstance(), "SIGNIN")
            .commit()
    }

    override fun getLayoutId() = R.layout.activity_main

    override fun getContainerId() = R.id.main_container
}
