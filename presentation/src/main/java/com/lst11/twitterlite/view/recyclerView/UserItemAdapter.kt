package com.lst11.twitterlite.view.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.lst11.twitterlite.R

class UserItemAdapter(private var itemList: MutableList<String> = mutableListOf()) :
    RecyclerView.Adapter<UserItemAdapter.MyViewHolder>() {

    class MyViewHolder(postItem: ConstraintLayout) : RecyclerView.ViewHolder(postItem)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val postItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_item, parent, false) as ConstraintLayout
        // TODO: set the view's size, margins, paddings and layout parameters

        return MyViewHolder(postItem)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val textView = holder.itemView.findViewById<TextView>(R.id.profile_name_text)
        textView.text = itemList[position]
    }

    override fun getItemCount() = itemList.size


    fun addItems(items: List<String>) {
        val startPos = itemList.size
        itemList.addAll(items)
        notifyItemRangeChanged(startPos, items.size)
    }

    fun resetItems(items: List<String>) {
        cleanItems()
        val startPos = itemList.size
        itemList.addAll(items)
        notifyItemRangeChanged(startPos, items.size)
    }

    private fun cleanItems() {
        itemList.clear()
        notifyDataSetChanged()
    }
}
