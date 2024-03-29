package com.example.app.features.friend_profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.app.R
import com.example.app.core.adapters.EventAdapter
import com.example.app.core.model.Event
import com.example.app.core.model.User
import com.example.app.features.BaseFragment
import com.example.app.features.NavigationManagerChildFragment
import kotlinx.android.synthetic.main.fragment_friend_profile.view.*

class FriendProfileFragment(private val friend : User) : BaseFragment(), FriendProfileView, NavigationManagerChildFragment {

    @InjectPresenter
    lateinit var presenter: FriendProfilePresenter

    private lateinit var eventsRecyclerView: RecyclerView
    lateinit var adapter: EventAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(getLayoutID(), container, false)

        view.button_friend_friends.setOnClickListener {
            parent.showUserFriendsFragment(friend)
        }

        view.button_add_to_friends.setOnClickListener {
            presenter.addFriend(friend)
        }

        eventsRecyclerView = view.list_friend_profie_events
        eventsRecyclerView.layoutManager = LinearLayoutManager(context)

        adapter = EventAdapter(view.context, object : EventAdapter.SelectEventListener {
            override fun onEventSelect(event: Event) {

            }
        })
        eventsRecyclerView.adapter = adapter
        adapter.setEvents(friend.events)
        //presenter.getProfileEvents(friend)

        return view
    }

    override fun getLayoutID() = R.layout.fragment_friend_profile

    override fun onEventsLoaded(events: ArrayList<Event>) {
        adapter.setEvents(events)
    }

    override fun showMessage(s: String) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance(friend: User): FriendProfileFragment {
            return FriendProfileFragment(friend)
        }
    }
}