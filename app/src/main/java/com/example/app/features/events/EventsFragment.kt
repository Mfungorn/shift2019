package com.example.app.features.events

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.app.R
import com.example.app.core.PreferencesApi
import com.example.app.core.adapters.EventAdapter
import com.example.app.core.model.Event
import com.example.app.features.BaseFragment
import com.example.app.core.adapters.EventAdapter.SelectEventListener
import com.example.app.features.NavigationManagerChildFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_events.view.*
import javax.inject.Inject

class EventsFragment : BaseFragment(), EventsView, NavigationManagerChildFragment {

    @Inject
    lateinit var prefs: SharedPreferences

    @InjectPresenter
    lateinit var presenter: EventsPresenter

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: EventAdapter
    private lateinit var addEventFab: FloatingActionButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(getLayoutID(), container, false)
        addEventFab = view.fab_add_event
        addEventFab.setOnClickListener {
            Toast.makeText(context, "Add Event", Toast.LENGTH_LONG).show()
            parent.showNewEventFragment()
        }

        view.button_profile.setOnClickListener {
            val user = PreferencesApi.getUser(prefs)
            if (user != null)
                parent.showUserProfileFragment(user)
        }

        recyclerView = view.list_events
        recyclerView.layoutManager = LinearLayoutManager(context)

        adapter = EventAdapter(requireContext(), object : SelectEventListener {
            override fun onEventSelect(event: Event) {
                parent.showEventFragment(event.id)
            }
        })
        recyclerView.adapter = adapter
        presenter.getEvents()

        return view
    }

    override fun onEventsLoaded(list: List<Event>) {
        adapter.setEvents(list)
    }

    override fun showMessage(s: String) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
    }

    override fun getLayoutID() = R.layout.fragment_events

    companion object {
        fun newInstance() = EventsFragment()
    }
}