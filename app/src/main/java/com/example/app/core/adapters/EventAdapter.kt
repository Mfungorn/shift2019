package com.example.app.core.adapters

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.core.model.Event
import com.example.app.core.model.Guest
import com.example.app.core.model.User
import kotlinx.android.synthetic.main.event_list_item.view.*
import java.text.SimpleDateFormat
import java.util.*

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

        private val adapter = IconAdapter(
            view.context,
            object : IconAdapter.SelectIconListener {
                override fun onIconSelect(user: User) {

                }
            })

        init {
            iconList.layoutManager = LinearLayoutManager(view.context,
                LinearLayoutManager.HORIZONTAL, false)
        }

        @TargetApi(Build.VERSION_CODES.N)
        fun bind(event: Event) {
            eventLocation.text = "${event.latitude} ${event.longitude}"
            eventDate.text = SimpleDateFormat("dd MMM, yyyy", Locale.ENGLISH).format(event.date)
            val total = event.expenses.stream().mapToDouble {expense -> expense.cost }.sum()
            eventTotalPrice.text = total.toString()
            eventPersonPrice.text = String.format("%.2f", (total / event.members.size))

            adapter.setIcons(event.members.map { guest -> guest.user })
            iconList.adapter = adapter

            itemView.setOnClickListener { selectEventListener.onEventSelect(event) }
        }

    }

    interface SelectEventListener {
        fun onEventSelect(event: Event)
    }

}