package com.example.app.features

import android.os.Bundle
import com.example.app.R

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        supportFragmentManager.beginTransaction()
            .add(R.id.main_container, MainFlowFragment.newInstance(), "MAIN_FRAGMENT_FLOW")
            .commit()
    }

    override fun getLayoutId() = R.layout.activity_main

    override fun getContainerId() = R.id.main_container
}
