package com.example.app.features.friends

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.app.R
import com.example.app.core.adapters.FriendAdapter
import com.example.app.core.model.User
import com.example.app.features.BaseFragment
import kotlinx.android.synthetic.main.fragment_friends.view.*

class FriendsFragment(val user: User) : BaseFragment(), FriendsView {

    @InjectPresenter
    lateinit var presenter: FriendsPresenter

    private lateinit var userFriendsRecyclerView: RecyclerView
    private lateinit var resultsRecyclerView: RecyclerView

    private lateinit var userFriendsAdapter: FriendAdapter
    private lateinit var resultsAdapter: FriendAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(getLayoutID(), container, false)

        view.button_friends_search.setOnClickListener {
            onSearchStarted()
        }

        userFriendsRecyclerView = view.list_friends_user_results
        userFriendsRecyclerView.layoutManager = LinearLayoutManager(context)
        resultsRecyclerView = view.list_friends_results
        resultsRecyclerView.layoutManager = LinearLayoutManager(context)

        userFriendsAdapter = FriendAdapter(view.context, object : FriendAdapter.SelectFriendListener {
            override fun onFriendSelect(user: User) {

            }
        })

        resultsAdapter = FriendAdapter(view.context, object : FriendAdapter.SelectFriendListener {
            override fun onFriendSelect(user: User) {

            }
        })

        userFriendsRecyclerView.adapter = userFriendsAdapter
        resultsRecyclerView.adapter = resultsAdapter

        return view
    }

    override fun onSearchStarted(query: String) {
        userFriendsAdapter.setFriends(user.friends.filter {
            it.name.startsWith(query) || it.login.startsWith(query)
        })
        presenter.searchFriends(query)
    }

    override fun onSearchResultsLoaded(results: ArrayList<User>) {
        resultsAdapter.setFriends(results)
    }

    override fun getLayoutID() = R.layout.fragment_friends

    override fun showMessage(s: String) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance(user: User): FriendsFragment {
            return FriendsFragment(user)
        }
    }
}