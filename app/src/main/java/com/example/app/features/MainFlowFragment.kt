package com.example.app.features

import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import com.example.app.App
import com.example.app.R
import com.example.app.core.PreferencesApi
import com.example.app.features.events.EventsFragment
import com.example.app.features.map.MapFragment
import com.example.app.features.profile.ProfileFragment
import kotlinx.android.synthetic.main.fragment_mainflow.view.*
import com.example.app.features.signin.SignInFragment
import kotlinx.android.synthetic.main.activity_main.view.*
import javax.inject.Inject

class MainFlowFragment : BaseFragment() {

    init {
        App.INSTANCE.getAppComponent().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(getLayoutID(), container, false)
        setHasOptionsMenu(true)

//        childFragmentManager.beginTransaction()
//            .add(getContainerID(), EventsFragment.newInstance(), "EVENTS")
//            .commit()
        childFragmentManager.beginTransaction()
            .add(getContainerID(), EventsFragment.newInstance(), "EVENTS")
            .commit()
        return view
    }

    override fun getLayoutID(): Int = R.layout.fragment_mainflow

    private fun getContainerID(): Int = R.id.flow_container

    companion object {
        fun newInstance() = MainFlowFragment()
    }
}