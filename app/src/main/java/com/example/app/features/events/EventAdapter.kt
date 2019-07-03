package com.example.app.features.events

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.core.model.Event
import com.example.app.core.model.User
import kotlinx.android.synthetic.main.event_list_item.view.*

import java.util.ArrayList

class EventAdapter(context: Context, private val selectEventListener: SelectEventListener) :
    RecyclerView.Adapter<EventAdapter.EventHolder>() {

    private val events = ArrayList<Event>()
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getItemCount() = events.size

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): EventHolder {
        val itemView = inflater.inflate(R.layout.event_list_item, parent, false)
        return EventHolder(itemView, selectEventListener)
    }

    override fun onBindViewHolder(@NonNull holder: EventHolder, position: Int) {
        holder.bind(events[position])
    }

    fun setEvents(list: List<Event>) {
        events.clear()
        events.addAll(list)
        notifyDataSetChanged()
    }

    inner class EventHolder(view: View, private val selectEventListener: SelectEventListener) :
        RecyclerView.ViewHolder(view) {

        private val eventLocation: TextView = view.text_event_location
        private val eventDate: TextView = view.text_event_date
        private val eventTotalPrice: TextView = view.text_total_price
        private val eventPersonPrice: TextView = view.text_per_person_price
        private val iconList : RecyclerView = view.list_icons

        private val adapter = IconAdapter(view.context, object : IconAdapter.SelectIconListener {
            override fun onIconSelect(user: User) {

            }
        })

        init {
            iconList.layoutManager = LinearLayoutManager(view.context,
                LinearLayoutManager.HORIZONTAL, false)
        }

        fun bind(event: Event) {
            eventLocation.text = event.location
            eventDate.text = event.date
            eventTotalPrice.text = event.price
            eventPersonPrice.text = event.price / event.members.size

            adapter.setIcons(event.members)
            iconList.adapter = adapter

            itemView.setOnClickListener { selectEventListener.onEventSelect(event) }
        }

    }

    interface SelectEventListener {
        fun onEventSelect(event: Event)
    }

}