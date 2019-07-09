package com.example.app.features.guests

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.app.R
import com.example.app.core.adapters.GuestAdapter
import com.example.app.core.model.Guest
import com.example.app.features.BaseFragment
import kotlinx.android.synthetic.main.fragment_guests.view.*

class GuestsFragment(private var guestsList: ArrayList<Guest>) : BaseFragment(), GuestsView {

    @InjectPresenter
    lateinit var selectPresenter: GuestsPresenter

    private lateinit var guestsRecyclerView: RecyclerView
    lateinit var adapter: GuestAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(getLayoutID(), container, false)

        guestsRecyclerView = view.list_guests
        guestsRecyclerView.layoutManager = LinearLayoutManager(context)

        adapter = GuestAdapter(view.context, object : GuestAdapter.GuestClickListener {
            override fun onGuestClick(guest: Guest) {

            }
        })
        adapter.setGuests(guestsList)
        guestsRecyclerView.adapter = adapter

        return view
    }

    override fun getLayoutID() = R.layout.fragment_guests

    companion object {
        fun newInstance(guestsList: ArrayList<Guest>): GuestsFragment {
            return GuestsFragment(guestsList)
        }
    }
}