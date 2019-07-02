package com.example.app.features.map

import com.arellomobile.mvp.MvpView
import com.google.android.gms.maps.model.LatLng
import com.google.maps.model.DirectionsResult

interface MapFragmentView: MvpView {
    fun showMessage(s: String)
}
