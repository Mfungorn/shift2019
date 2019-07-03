package com.example.app.features.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.app.R
import com.example.app.core.model.Event
import com.example.app.features.BaseFragment
import com.example.app.features.events.EventAdapter.SelectEventListener

class EventsFragment : BaseFragment(), EventsView {

    @InjectPresenter
    lateinit var presenter: EventsPresenter

    lateinit var recyclerView: RecyclerView
    private lateinit var adapter: EventAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout. , container, false)
        recyclerView = view.findViewById(R.id.)
        recyclerView.layoutManager = LinearLayoutManager(context)

        adapter = EventAdapter(requireContext(), object : SelectEventListener {
            override fun onEventSelect(event: Event) {

            }
        })

        adapter.setEvents( )
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        return view
    }

    override fun showMessage(s: String) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
    }

    override fun getLayoutID() = R.layout...

    companion object {
        fun newInstance() = EventsFragment()
    }
}