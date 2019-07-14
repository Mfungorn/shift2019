package com.example.app.features.friends_select

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.app.R
import com.example.app.core.PreferencesApi
import com.example.app.core.adapters.SelectableFriendAdapter
import com.example.app.core.model.User
import com.example.app.features.BaseFragment
import kotlinx.android.synthetic.main.fragment_friends_select.view.*

class FriendsSelectFragment(private var selectedFriendsList: List<User>) : BaseFragment(), FriendsSelectView {

    @InjectPresenter
    lateinit var selectPresenter: FriendsSelectPresenter

    private lateinit var friendsRecyclerView: RecyclerView
    lateinit var adapter: SelectableFriendAdapter

    var user: User = PreferencesApi.getUser(selectPresenter.prefs)!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(getLayoutID(), container, false)

        friendsRecyclerView = view.list_friends_select
        friendsRecyclerView.layoutManager = LinearLayoutManager(context)

        adapter = SelectableFriendAdapter(view.context, object : SelectableFriendAdapter.SelectFriendListener {
            override fun onFriendSelect(user: User) {
                if (selectPresenter.selectedFriendsList.contains(user))
                    selectPresenter.removeSelectedFriend(user)
                else
                    selectPresenter.addSelectedFriend(user)
            }

        })
        adapter.setFriends(user.friends)
        adapter.setSelectedFriends(selectPresenter.selectedFriendsList)
        friendsRecyclerView.adapter = adapter

        view.button_select_friends.setOnClickListener {
            selectedFriendsList = selectPresenter.selectedFriendsList
            parentFragment!!.childFragmentManager.popBackStack()
        }

        return view
    }

    override fun getLayoutID() = R.layout.fragment_friends_select

    companion object {
        fun newInstance(selectedFriendsList: List<User>): FriendsSelectFragment {
            return FriendsSelectFragment(selectedFriendsList)
        }
    }
}