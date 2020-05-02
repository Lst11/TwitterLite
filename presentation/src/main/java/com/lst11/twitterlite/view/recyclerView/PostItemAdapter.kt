package com.lst11.twitterlite.view.recyclerView

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.lst11.twitterlite.R
import com.lst11.twitterlite.user.model.Post

class PostItemAdapter(
    private var itemList: MutableList<Post> = mutableListOf(),
    private var context: Context
) :
    RecyclerView.Adapter<PostItemAdapter.MyViewHolder>() {

    class MyViewHolder(postItem: ConstraintLayout) : RecyclerView.ViewHolder(postItem)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val postItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_item, parent, false) as ConstraintLayout

        return MyViewHolder(postItem)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val titleTextView = holder.itemView.findViewById<TextView>(R.id.post_title_text)
        titleTextView.text = itemList[position].title

        val descriptionTextView = holder.itemView.findViewById<TextView>(R.id.post_description_text)
        descriptionTextView.text = itemList[position].description

        uploadPostImage(position, holder)

        uploadAuthorImage(position, holder)
    }

    private fun uploadAuthorImage(position: Int, holder: MyViewHolder) {
        val imageAuthorUrl: String = itemList[position].authorImage

        if (imageAuthorUrl.isNotEmpty()) {
            val postImageView = holder.itemView.findViewById<ImageView>(R.id.profile_image)
            val progressBar = holder.itemView.findViewById<ProgressBar>(R.id.progress)
            uploadImage(imageAuthorUrl, postImageView, progressBar)
                .apply(RequestOptions.circleCropTransform())
                .into(postImageView)
        }
    }

    private fun uploadPostImage(
        position: Int,
        holder: MyViewHolder
    ) {
        val imageUrl: String = itemList[position].getImageUrl()

        if (imageUrl.isNotEmpty()) {
            val postLayout = holder.itemView.findViewById<RelativeLayout>(R.id.image_layout)
            postLayout.visibility = View.VISIBLE
            val postImageView = holder.itemView.findViewById<ImageView>(R.id.post_image)
            val progressBar = holder.itemView.findViewById<ProgressBar>(R.id.progress)
            uploadImage(imageUrl, postImageView, progressBar)
                .fitCenter()
                .into(postImageView)
        }
    }

    private fun uploadImage(
        imageUrl: String,
        postImageView: ImageView,
        progressBar: ProgressBar
    ): RequestBuilder<Drawable> {
        return Glide
            .with(context)
            .load(imageUrl)
            .centerCrop()
            .listener(object : RequestListener<Drawable> {
                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: com.bumptech.glide.load.DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    postImageView.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                    return false
                }

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressBar.visibility = View.GONE
                    return false
                }
            })
    }

    override fun getItemCount() = itemList.size

    fun addItems(items: List<Post>) {
        val startPos = itemList.size
        itemList.addAll(items)
        notifyItemRangeChanged(startPos, items.size)
    }

    fun resetItems(items: List<Post>) {
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
