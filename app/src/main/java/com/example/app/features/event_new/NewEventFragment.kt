package com.example.app.features.event_new

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.app.R
import com.example.app.core.model.*
import com.example.app.features.BaseFragment
import com.example.app.features.events.IconAdapter
import kotlinx.android.synthetic.main.fragment_new_event.view.*

class NewEventFragment : BaseFragment(), NewEventView {

    @InjectPresenter
    lateinit var presenter: NewEventPresenter

    var expenses: ArrayList<Expense> = ArrayList()

    lateinit var createButton: ImageButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: IconAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(getLayoutID(), container, false)

        recyclerView = view.list_new_event_friends
        recyclerView.layoutManager = LinearLayoutManager(context)

        adapter = IconAdapter(view.context, object : IconAdapter.SelectIconListener {
            override fun onIconSelect(guest: Guest) {

            }
        })
        recyclerView.adapter = adapter

        createButton = view.button_new_event_create
        createButton.setOnClickListener {
            presenter.createEvent(EventPostPayload())
        }

        view.button_new_event_location.setOnClickListener {

        }

        view.button_new_event_date.setOnClickListener {

        }

        view.fab_new_event_add_friend.setOnClickListener {

        }

        view.fab_add_carry.setOnClickListener {
            showBottomFragment()
        }

        return view
    }

    override fun showMessage(s: String) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
    }

    override fun getLayoutID() = R.layout.fragment_new_event

    private fun showBottomFragment() {
        val bottomNavDrawerFragment = BottomNavigationDrawerFragment.newInstance(expenses)
        bottomNavDrawerFragment.show(childFragmentManager, bottomNavDrawerFragment.tag)
    }

    companion object {
        fun newInstance() = NewEventFragment()
    }
}