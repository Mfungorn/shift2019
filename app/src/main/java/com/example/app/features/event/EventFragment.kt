package com.example.app.features.event

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.app.R
import com.example.app.core.model.*
import com.example.app.features.BaseFragment
import com.example.app.core.adapters.IconAdapter
import java.util.*
import android.widget.TextView
import com.example.app.features.NavigationManagerChildFragment
import com.example.app.features.event_new.BottomNavigationDrawerFragment
import com.example.app.features.map.MapFragment
import kotlinx.android.synthetic.main.fragment_event.view.*
import java.text.SimpleDateFormat

class EventFragment(private val eventId: Long) : BaseFragment(), EventView, NavigationManagerChildFragment {

    @InjectPresenter
    lateinit var presenter: EventPresenter

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: IconAdapter
    private lateinit var eventTitleTextView: TextView
    private lateinit var eventLocationTextView: TextView
    private lateinit var eventDateTextView: TextView
    private lateinit var eventTotalTextView: TextView

    private lateinit var map : MapFragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(getLayoutID(), container, false)

        map = MapFragment.newInstance(presenter.event.latitude, presenter.event.longitude)

        recyclerView = view.list_event_friends
        recyclerView.layoutManager = LinearLayoutManager(context)

        adapter = IconAdapter(
            view.context,
            object : IconAdapter.SelectIconListener {
                override fun onIconSelect(user: User) {
                    parent.showGuestsFragment(presenter.event.members)
                }
            })
        recyclerView.adapter = adapter

        eventTitleTextView = view.text_event_title
        eventLocationTextView = view.text_event_location
        eventDateTextView = view.text_event_date
        eventTotalTextView = view.text_event_total

        view.button_event_done.setOnClickListener {
            parentFragment!!.childFragmentManager
                .popBackStack()
        }

        view.button_event_location.setOnClickListener {
            childFragmentManager.beginTransaction()
                .add(getContainerID(),
                    map,"MAP")
                .commit()
        }

        view.button_event_date.setOnClickListener {
            showMessage(SimpleDateFormat("dd MMM, yyyy", Locale.ENGLISH).format(presenter.event.date))
        }

        view.fab_carry.setOnClickListener {
            showBottomFragment()
        }

        onEventLoadStart(eventId)

        return view
    }

    override fun onEventLoadStart(id: Long) {
        presenter.getEvent(id)
    }

    @TargetApi(Build.VERSION_CODES.N)
    override fun onEventLoaded(event: Event) {
        eventTitleTextView.text = event.title
        eventTotalTextView.text = String.format("%.2f ${getString(R.string.currency)}", event.expenses.stream()
            .mapToDouble { e -> e.cost}
            .sum())
        eventDateTextView.text = SimpleDateFormat("dd MMM, yyyy", Locale.ENGLISH).format(event.date)
        eventLocationTextView.text = map.getCity()
        adapter.setIcons(event.members.map { guest -> guest.user })
    }

    override fun showMessage(s: String) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
    }

    override fun getLayoutID() = R.layout.fragment_event

    private fun getContainerID() = R.id.container_map_event

    private fun showBottomFragment() {
        val bottomNavDrawerFragment = BottomNavigationDrawerFragment.newInstance(presenter.event.expenses)
        bottomNavDrawerFragment.show(childFragmentManager, bottomNavDrawerFragment.tag)
    }

    companion object {
        fun newInstance(eventId: Long) = EventFragment(eventId)
    }
}