package com.lst11.twitterlite.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.lst11.twitterlite.R
import com.lst11.twitterlite.app.App
import com.lst11.twitterlite.presenter.PostPresenter
import com.lst11.twitterlite.user.model.Post
import com.lst11.twitterlite.view.activity.MainActivity
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

    init {
        App.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val createPostButton = getView()?.findViewById<FloatingActionButton>(R.id.save_button)

        createPostButton?.setOnClickListener {

            val newPost = getPostFromFields()
            presenter.savePost(newPost)

            val activity = (activity as MainActivity)
            activity.backToMain()
        }
    }

    private fun getPostFromFields(): Post {
        val titleTextView = view?.findViewById<TextView>(R.id.post_title_text)
        val title = titleTextView?.text.toString()

        val descriptionTextView = view?.findViewById<TextView>(R.id.post_description_text)
        val description = descriptionTextView?.text.toString()

        val imageUrl = ""

        return Post(title, description, imageUrl)
    }

}