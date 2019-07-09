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
import com.example.app.core.model.User
import kotlinx.android.synthetic.main.selectable_friends_list_item.view.*
import java.util.*

class SelectableFriendAdapter(context: Context, private val listener: SelectFriendListener) :
    RecyclerView.Adapter<SelectableFriendAdapter.SelectedFriendHolder>() {

    private val friends = ArrayList<User>()
    private val selectedFriends = ArrayList<User>()
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getItemCount() = friends.size

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): SelectedFriendHolder {
        val itemView = inflater.inflate(R.layout.selectable_friends_list_item, parent, false)
        return SelectedFriendHolder(itemView, listener)
    }

    override fun onBindViewHolder(@NonNull holderSelected: SelectedFriendHolder, position: Int) {
        holderSelected.bind(friends[position])
    }

    fun setFriends(list: List<User>) {
        friends.clear()
        friends.addAll(list)
        notifyDataSetChanged()
    }

    fun setSelectedFriends(list: List<User>) {
        selectedFriends.clear()
        selectedFriends.addAll(list)
        notifyDataSetChanged()
    }

    inner class SelectedFriendHolder(view: View, private val selectFriendListener: SelectFriendListener) :
        RecyclerView.ViewHolder(view) {

        private val icon: ImageView = view.selectable_friend_icon
        private val name: TextView = view.text_selectable_friend_name
        private val phonenumber: TextView = view.text_selectable_friend_phone

        fun bind(user: User) {
            //if (!user.profileIcon.isEmpty)
            //    Picasso.get().load(user.profileIcon).into(icon)
            name.text = user.username
            phonenumber.text = user.phone
            if (selectedFriends.contains(user))
                itemView.setBackgroundColor(0x32D81B60)
            else
                itemView.setBackgroundColor(0xFFFFFF)

            itemView.setOnClickListener { selectFriendListener.onFriendSelect(user) }
        }

    }

    interface SelectFriendListener {
        fun onFriendSelect(user: User)
    }

}