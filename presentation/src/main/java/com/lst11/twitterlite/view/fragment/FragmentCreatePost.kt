package com.lst11.twitterlite.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.lst11.twitterlite.R
import com.lst11.twitterlite.view.activity.MainActivity


class FragmentCreatePost : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val createPostButton = getView()?.findViewById<FloatingActionButton>(R.id.save_button)

        createPostButton?.setOnClickListener {
            val activity = (activity as MainActivity)
            activity.backToMain()
        }
    }

}