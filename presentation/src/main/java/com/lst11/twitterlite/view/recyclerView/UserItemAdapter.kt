package com.lst11.twitterlite.view.recyclerView

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.lst11.twitterlite.R
import com.lst11.twitterlite.user.model.User

class UserItemAdapter(
    private var itemList: MutableList<User> = mutableListOf(),
    private var context: Context
) :
    RecyclerView.Adapter<UserItemAdapter.MyViewHolder>() {

    class MyViewHolder(postItem: ConstraintLayout) : RecyclerView.ViewHolder(postItem)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val postItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_item, parent, false) as ConstraintLayout

        return MyViewHolder(postItem)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.e("aaa", "Adapter position: $position")

        val nameTextView = holder.itemView.findViewById<TextView>(R.id.profile_name_text)
        nameTextView.text = itemList[position].name

        val statusTextView = holder.itemView.findViewById<TextView>(R.id.profile_status_text)
        statusTextView.text = itemList[position].status

        val imageView = holder.itemView.findViewById<ImageView>(R.id.profile_image)
        val progressBar = holder.itemView.findViewById<ProgressBar>(R.id.progress)

        Glide
            .with(context)
            .load(itemList[position].imageLink)
            .listener(object : RequestListener<Drawable> {
                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: com.bumptech.glide.load.DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressBar.visibility = View.GONE
                    return false
                }

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressBar.visibility = View.GONE
                    return false
                }
            })
            .apply(RequestOptions.circleCropTransform())
            .into(imageView)

    }

    override fun getItemCount() = itemList.size


    fun addItems(items: List<User>) {
        val startPos = itemList.size
        itemList.addAll(items)
        notifyItemRangeChanged(startPos, items.size)
    }

    fun resetItems(items: MutableList<User>) {
        itemList = items
        notifyItemRangeChanged(itemList.size, items.size)
    }

    private fun cleanItems() {
        itemList.clear()
        notifyDataSetChanged()
    }
}
