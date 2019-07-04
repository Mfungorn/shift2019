package com.example.app.features.profile

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app.App
import com.example.app.R
import com.example.app.core.PreferencesApi
import com.example.app.core.model.User
import com.example.app.features.BaseFragment
import com.example.app.features.MainFlowFragment
import com.example.app.features.events.EventAdapter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_profile.view.*
import javax.inject.Inject

class ProfileFragment : BaseFragment() {

    @Inject
    lateinit var prefs: SharedPreferences

    lateinit var user: User
    lateinit var nameTextView: TextView
    lateinit var nicknameView: TextView
    lateinit var banquetsView: ConstraintLayout
    lateinit var friendsAddView: ConstraintLayout
    lateinit var logoutButton: Button
    lateinit var friendsRecyclerView: RecyclerView
    lateinit var adapter: FriendAdapter

    init {
        App.INSTANCE.getAppComponent().inject(this)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(getLayoutID(), container, false)

        nameTextView = view.name_text_view
        nicknameView = view.nickname_text_view
        banquetsView = view.button_banquets
        friendsAddView = view.button_add_friends
        logoutButton = view.button_logout
        friendsRecyclerView = view.list_user_friends
        friendsRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = FriendAdapter(requireContext(), object : FriendAdapter.SelectFriendListener {
            override fun onFriendSelect(user: User) {

            }
        })
        friendsRecyclerView.adapter = adapter


        logoutButton.setOnClickListener {
            run {
                PreferencesApi.delData(prefs)
                activity!!.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, MainFlowFragment.newInstance(), "MAIN_FLOW_FRAGMENT")
                    .commit()
            }
        }

        banquetsView.setOnClickListener {
            Toast.makeText(context, "banquets", Toast.LENGTH_SHORT).show()
        }

        friendsAddView.setOnClickListener {
            Toast.makeText(context, "add friend", Toast.LENGTH_SHORT).show()
        }

        view.button_edit.setOnClickListener {
            Toast.makeText(context, "Эта функция пока недоступна", Toast.LENGTH_LONG).show()
        }

        val bundle = this.arguments
        if (bundle != null) {
            val jsonString = bundle.getString("User")
            user = Gson().fromJson(jsonString, User::class.java)
            showUser()
        }
        return view
    }

    private fun showUser() {
        nameTextView.text = user.name
        nicknameView.text = user.login

        adapter.setFriends(user.friends)
    }

    override fun getLayoutID(): Int = R.layout.fragment_profile

    companion object {
        fun newInstance(user: User): ProfileFragment {
            val profileFragment = ProfileFragment()
            val bundle = Bundle()
            bundle.putString("User", Gson().toJson(user))
            profileFragment.arguments = bundle
            return profileFragment
        }
    }
}