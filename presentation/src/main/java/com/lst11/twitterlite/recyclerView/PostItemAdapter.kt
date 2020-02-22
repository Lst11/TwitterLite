package com.lst11.twitterlite.recyclerView;

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lst11.twitterlite.R

class PostItemAdapter(private val myDataset: List<String>) :
    RecyclerView.Adapter<PostItemAdapter.MyViewHolder>() {

    class MyViewHolder(linearLayout: LinearLayout) : RecyclerView.ViewHolder(linearLayout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_item, parent, false) as LinearLayout
        // TODO: set the view's size, margins, paddings and layout parameters

        return MyViewHolder(textView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val textView = holder.itemView.findViewById<TextView>(R.id.item_name)
        textView.text = myDataset[position]
    }

    override fun getItemCount() = myDataset.size
}
