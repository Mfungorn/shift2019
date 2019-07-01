package com.example.app.features

import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import com.example.app.App
import com.example.app.R
import com.example.app.core.PreferencesApi
import com.example.app.features.profile.ProfileFragment
import kotlinx.android.synthetic.main.fragment_mainflow.view.*
import com.example.app.features.signin.SignInFragment
import kotlinx.android.synthetic.main.activity_main.view.*
import javax.inject.Inject

class MainFlowFragment : BaseFragment() {

    @Inject
    lateinit var prefs: SharedPreferences

    init {
        App.INSTANCE.getAppComponent().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(getLayoutID(), container, false)
        setHasOptionsMenu(true)

//        childFragmentManager.beginTransaction()
//            .add(getContainerID(), .newInstance(), "MAP")
//            .commit()
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.app_bar_profile -> {
                val user = PreferencesApi.getUser(prefs)
                if (user == null) {
                    activity!!.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.main_container, SignInFragment.newInstance(), "SIGNIN")
                        .addToBackStack(null)
                        .commit()
                } else {
                    activity!!.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.main_container, ProfileFragment.newInstance(user), "PROFILE")
                        .addToBackStack(null)
                        .commit()
                }
            }
            R.id.app_bar_search -> {

            }

            android.R.id.home -> {

            }
        }
        return true
    }

    override fun getLayoutID(): Int = R.layout.fragment_mainflow

    private fun getContainerID(): Int = R.id.flow_container

    companion object {
        fun newInstance() = MainFlowFragment()
    }
}