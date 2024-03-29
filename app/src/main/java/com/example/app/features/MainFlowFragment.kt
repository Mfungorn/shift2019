package com.example.app.features

import android.os.Bundle
import android.view.*
import com.example.app.App
import com.example.app.R
import com.example.app.core.model.Guest
import com.example.app.core.model.User
import com.example.app.features.event.EventFragment
import com.example.app.features.event_new.NewEventFragment
import com.example.app.features.events.EventsFragment
import com.example.app.features.friend_profile.FriendProfileFragment
import com.example.app.features.friends.FriendsFragment
import com.example.app.features.friends_select.FriendsSelectFragment
import com.example.app.features.guests.GuestsFragment
import com.example.app.features.profile.ProfileFragment

class MainFlowFragment : BaseFragment(), NavigationManager {

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

    override fun showEventFragment(id: Long) {
        childFragmentManager.beginTransaction()
            .add(getContainerID(), EventFragment.newInstance(id), "EVENT")
            .addToBackStack("EVENT")
            .commit()
    }

    override fun showNewEventFragment() {
        childFragmentManager.beginTransaction()
            .add(getContainerID(), NewEventFragment.newInstance(), "NEW_EVENT")
            .addToBackStack("NEW_EVENT")
            .commit()
    }

    override fun showEventsFragment() {
        childFragmentManager.beginTransaction()
            .add(getContainerID(), EventsFragment.newInstance(), "EVENTS")
            .addToBackStack("EVENTS")
            .commit()
    }

    override fun showFriendProfileFragment(friend: User) {
        childFragmentManager.beginTransaction()
            .add(getContainerID(), FriendProfileFragment.newInstance(friend), "FRIEND_PROFILE")
            .addToBackStack("FRIEND_PROFILE")
            .commit()
    }

    override fun showUserFriendsFragment(user: User) {
        childFragmentManager.beginTransaction()
            .add(getContainerID(), FriendsFragment.newInstance(user), "FRIENDS")
            .addToBackStack("FRIENDS")
            .commit()
    }

    override fun showFriendsSelectFragment(selectedFriendsList: List<User>) {
        childFragmentManager.beginTransaction()
            .add(getContainerID(), FriendsSelectFragment.newInstance(selectedFriendsList), "FRIENDS_SELECT")
            .addToBackStack("FRIENDS_SELECT")
            .commit()
    }

    override fun showGuestsFragment(guests: ArrayList<Guest>) {
        childFragmentManager.beginTransaction()
            .add(getContainerID(), GuestsFragment.newInstance(guests), "GUESTS")
            .addToBackStack("GUESTS")
            .commit()
    }


    override fun showUserProfileFragment(user: User) {
        childFragmentManager.beginTransaction()
            .add(getContainerID(), ProfileFragment.newInstance(user), "PROFILE")
            .addToBackStack("PROFILE")
            .commit()
    }

    companion object {
        var instance : MainFlowFragment? = null
        fun newInstance() : MainFlowFragment {
            if (instance == null) instance = MainFlowFragment()
            return instance as MainFlowFragment
        }
    }
}