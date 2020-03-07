package com.lst11.twitterlite.view.recyclerView;

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.lst11.twitterlite.R

class PostItemAdapter(private val myDataset: List<String>) :
    RecyclerView.Adapter<PostItemAdapter.MyViewHolder>() {

    class MyViewHolder(postItem: ConstraintLayout) : RecyclerView.ViewHolder(postItem)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val postItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_item, parent, false) as ConstraintLayout
        // TODO: set the view's size, margins, paddings and layout parameters

        return MyViewHolder(postItem)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val textView = holder.itemView.findViewById<TextView>(R.id.profile_name_text)
        textView.text = myDataset[position]
    }

    override fun getItemCount() = myDataset.size
}
