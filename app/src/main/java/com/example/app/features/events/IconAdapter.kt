package com.example.app.features.events

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.core.model.Event
import com.example.app.core.model.Guest
import com.example.app.core.model.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.icon_list_item.view.*

import java.util.ArrayList

class IconAdapter(context: Context, private val selectIconListener: SelectIconListener) :
    RecyclerView.Adapter<IconAdapter.IconHolder>() {

    private val icons = ArrayList<Guest>()
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getItemCount() = icons.size

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): IconHolder {
        val itemView = inflater.inflate(R.layout.icon_list_item, parent, false)
        return IconHolder(itemView, selectIconListener)
    }

    override fun onBindViewHolder(@NonNull holder: IconHolder, position: Int) {
        holder.bind(icons[position])
    }

    fun setIcons(list: List<Guest>) {
        icons.clear()
        icons.addAll(list)
        notifyDataSetChanged()
    }

    inner class IconHolder(view: View, private val selectIconListener: SelectIconListener) :
        RecyclerView.ViewHolder(view) {

        private val icon: ImageView = view.user_icon

        fun bind(guest: Guest) {
            //if (!user.profileIcon.isEmpty)
            //    Picasso.get().load(user.profileIcon).into(icon)

            itemView.setOnClickListener { selectIconListener.onIconSelect(guest) }
        }

    }

    interface SelectIconListener {
        fun onIconSelect(guest: Guest)
    }

}