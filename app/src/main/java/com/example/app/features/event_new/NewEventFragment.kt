package com.example.app.features.event_new

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.app.R
import com.example.app.core.DefaultTextWatcher
import com.example.app.core.model.*
import com.example.app.features.BaseFragment
import com.example.app.core.adapters.IconAdapter
import com.example.app.features.friend_profile.FriendProfileFragment
import com.example.app.features.friends_select.FriendsSelectFragment
import kotlinx.android.synthetic.main.fragment_new_event.view.*
import java.util.*
import android.text.format.DateUtils
import com.example.app.features.map.MapFragment


class NewEventFragment : BaseFragment(), NewEventView {

    @InjectPresenter
    lateinit var presenter: NewEventPresenter

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: IconAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(getLayoutID(), container, false)

        recyclerView = view.list_new_event_friends
        recyclerView.layoutManager = LinearLayoutManager(context)

        adapter = IconAdapter(
            view.context,
            object : IconAdapter.SelectIconListener {
                override fun onIconSelect(user: User) {
                    parentFragment!!.childFragmentManager.beginTransaction()
                        .add(R.id.flow_container, FriendProfileFragment.newInstance(user), "FRIEND_PROFILE")
                        .addToBackStack("FRIEND_PROFILE")
                        .commit()
                }
            })
        recyclerView.adapter = adapter

        view.button_new_event_create.setOnClickListener {
            presenter.createEvent()
            parentFragment!!.childFragmentManager
                .popBackStack()
        }

        view.button_new_event_location.setOnClickListener {
            childFragmentManager.beginTransaction()
                .add(getContainerID(),
                    MapFragment.newInstance(presenter.event.latitude, presenter.event.longitude),
                    "MAP")
                .commit()
        }

        view.button_new_event_date.setOnClickListener {
            DatePickerDialog(view.context,
                DatePickerDialog.OnDateSetListener(function = { _, y, m, d ->
                    presenter.currentTime.set(y, m, d)
                    view.text_new_event_date.text = DateUtils.formatDateTime(view.context,
                        presenter.currentTime.timeInMillis,
                        DateUtils.FORMAT_SHOW_DATE or
                                DateUtils.FORMAT_SHOW_YEAR or
                                DateUtils.FORMAT_SHOW_TIME)
                    TimePickerDialog(view.context,
                        TimePickerDialog.OnTimeSetListener(function = { _, hour, min ->
                            presenter.currentTime.set(Calendar.HOUR_OF_DAY, hour)
                            presenter.currentTime.set(Calendar.MINUTE, min)
                            view.text_new_event_date.text = DateUtils.formatDateTime(view.context,
                                presenter.currentTime.timeInMillis,
                                DateUtils.FORMAT_SHOW_DATE or
                                        DateUtils.FORMAT_SHOW_YEAR or
                                        DateUtils.FORMAT_SHOW_TIME)
                        }),
                        presenter.currentTime.get(Calendar.HOUR_OF_DAY),
                        presenter.currentTime.get(Calendar.MINUTE),
                        true)
                        .show()
                }),
                presenter.currentTime.get(Calendar.YEAR),
                presenter.currentTime.get(Calendar.MONTH),
                presenter.currentTime.get(Calendar.DAY_OF_MONTH))
                .show()
        }

        view.fab_new_event_add_friend.setOnClickListener {
            parentFragment!!.childFragmentManager.beginTransaction()
                .add(R.id.flow_container, FriendsSelectFragment.newInstance(presenter.event.members), "SELECT_FRIENDS")
                .addToBackStack("SELECT_FRIENDS")
                .commit()
        }

        view.fab_add_carry.setOnClickListener {
            showBottomFragment()
        }

        view.edit_new_event_title.addTextChangedListener(object : DefaultTextWatcher() {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.event.title = s!!.toString()
            }

        })

        return view
    }

    override fun showMessage(s: String) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
    }

    override fun getLayoutID() = R.layout.fragment_new_event

    private fun getContainerID() = R.id.container_map

    private fun showBottomFragment() {
        val bottomNavDrawerFragment = BottomNavigationDrawerFragment.newInstance(presenter.event.expenses)
        bottomNavDrawerFragment.show(childFragmentManager, bottomNavDrawerFragment.tag)
    }

    companion object {
        fun newInstance() = NewEventFragment()
    }
}