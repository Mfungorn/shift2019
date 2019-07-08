package com.example.app.features

interface NavigationManagerChildFragment {
    val parent: NavigationManager
    get() = MainFlowFragment.newInstance()
}