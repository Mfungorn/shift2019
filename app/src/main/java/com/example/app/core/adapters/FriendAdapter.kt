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
import kotlinx.android.synthetic.main.friends_list_item.view.*

import java.util.ArrayList

class FriendAdapter(context: Context, private val listener: SelectFriendListener) :
    RecyclerView.Adapter<FriendAdapter.FriendHolder>() {

    private val friends = ArrayList<User>()
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getItemCount() = friends.size

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): FriendHolder {
        val itemView = inflater.inflate(R.layout.friends_list_item, parent, false)
        return FriendHolder(itemView, listener)
    }

    override fun onBindViewHolder(@NonNull holder: FriendHolder, position: Int) {
        holder.bind(friends[position])
    }

    fun setFriends(list: List<User>) {
        friends.clear()
        friends.addAll(list)
        notifyDataSetChanged()
    }

    inner class FriendHolder(view: View, private val selectFriendListener: SelectFriendListener) :
        RecyclerView.ViewHolder(view) {

        private val icon: ImageView = view.friend_icon
        private val name: TextView = view.text_friend_name
        private val phonenumber: TextView = view.text_friend_phone

        fun bind(user: User) {
            //if (!user.profileIcon.isEmpty)
            //    Picasso.get().load(user.profileIcon).into(icon)
            name.text = user.name
            phonenumber.text = user.phone

            itemView.setOnClickListener { selectFriendListener.onFriendSelect(user) }
        }

    }

    interface SelectFriendListener {
        fun onFriendSelect(user : User)
    }

}