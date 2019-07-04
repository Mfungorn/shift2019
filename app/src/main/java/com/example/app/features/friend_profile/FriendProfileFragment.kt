package com.example.app.features.friend_profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.app.R
import com.example.app.features.BaseFragment

class FriendProfileFragment : BaseFragment(), FriendProfileView {

    @InjectPresenter
    lateinit var presenter: FriendProfilePresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(getLayoutID(), container, false)

        return view
    }

    override fun getLayoutID() = R.layout

    override fun showMessage(s: String) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance(): FriendProfileFragment {
            return FriendProfileFragment()
        }
    }
}