package com.lst11.twitterlite.view.fragment

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.lst11.twitterlite.R
import com.lst11.twitterlite.app.App
import com.lst11.twitterlite.presenter.PostPresenter
import com.lst11.twitterlite.user.model.Post
import com.lst11.twitterlite.view.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_create_post.*
import javax.inject.Inject


class FragmentCreatePost : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_post, container, false)
    }

    @Inject
    lateinit var presenter: PostPresenter

    private var bitmapImage: Bitmap? = null

    private val PICK_IMAGE = 100

    init {
        App.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setClickListener(save_button)

        upload_file_button?.setOnClickListener {
            getFileFromGallery()
        }
    }

    private fun getFileFromGallery() {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(gallery, PICK_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val returnUri: Uri? = data?.data
        bitmapImage = MediaStore.Images.Media.getBitmap(activity!!.contentResolver, returnUri)

        post_image.setImageBitmap(bitmapImage)
    }

    private fun setClickListener(createPostButton: FloatingActionButton?) {
        createPostButton?.setOnClickListener {

            val newPost = getPostFromFields()
            if (newPost.title.isNotBlank() && newPost.description.isNotBlank()) {
                presenter.savePost(newPost, bitmapImage)
                val activity = (activity as MainActivity)
                activity.backToMain()
            } else {
                val toast =
                    Toast.makeText(context, "The fields should not be empty!", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }

    private fun getPostFromFields(): Post {
        val titleTextView = view?.findViewById<TextView>(R.id.post_title_text)
        val title = titleTextView?.text.toString()

        val descriptionTextView = view?.findViewById<TextView>(R.id.post_description_text)
        val description = descriptionTextView?.text.toString()

        return Post(title, description)
    }
}