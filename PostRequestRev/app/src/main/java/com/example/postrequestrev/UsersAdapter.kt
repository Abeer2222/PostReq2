package com.example.postrequestrev

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.user_item_row.view.*

class UsersAdapter(private val myUsers: List<User.UserDetails>) :
    RecyclerView.Adapter<UsersAdapter.ItemViewHolder>() {
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            return ItemViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.user_item_row,
                    parent,
                    false
                )
            )
        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            val list1 = myUsers[position]

            holder.itemView.apply {
                tvname.text = list1.name
                tvlocation.text = list1.location
                tvID.text = list1.id.toString()


            }
        }

        override fun getItemCount() = myUsers.size
    }