package com.example.app.core.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.core.model.Guest
import kotlinx.android.synthetic.main.guests_list_item.view.*

import java.util.ArrayList

class GuestAdapter(context: Context, private val listenerGuest: GuestClickListener) :
    RecyclerView.Adapter<GuestAdapter.GuestHolder>() {

    private val guests = ArrayList<Guest>()
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getItemCount() = guests.size

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): GuestHolder {
        val itemView = inflater.inflate(R.layout.guests_list_item, parent, false)
        return GuestHolder(itemView, listenerGuest)
    }

    override fun onBindViewHolder(@NonNull holder: GuestHolder, position: Int) {
        holder.bind(guests[position])
    }

    fun setGuests(list: List<Guest>) {
        guests.clear()
        guests.addAll(list)
        notifyDataSetChanged()
    }

    inner class GuestHolder(view: View, private val guestClickListener: GuestClickListener) :
        RecyclerView.ViewHolder(view) {

        private val icon: ImageView = view.guest_icon
        private val name: TextView = view.text_guest_name
        private val phonenumber: TextView = view.text_guest_phone
        private val paid: TextView = view.text_guest_paid
        private val total: TextView = view.text_guest_total
        private val status: TextView = view.text_guest_status

        fun bind(guest: Guest) {
            //if (!user.profileIcon.isEmpty)
            //    Picasso.get().load(user.profileIcon).into(icon)
            name.text = guest.user.username
            phonenumber.text = guest.user.phone
            paid.text = guest.paid.toString()
            total.text = guest.total.toString()
            status.text = guest.status

            itemView.setOnClickListener { guestClickListener.onGuestClick(guest) }
        }

    }

    interface GuestClickListener {
        fun onGuestClick(guest: Guest)
    }

}