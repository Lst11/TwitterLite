package com.lst11.twitterlite.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lst11.twitterlite.R
import com.lst11.twitterlite.view.recyclerView.UserItemAdapter


class FragmentFollowers : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_followers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val followers = listOf(
            "Follower #1",
            "Follower #2",
            "Follower #3",
            "Follower #4",
            "Follower #5"
        )

        viewManager = LinearLayoutManager(view.context)
        viewAdapter = UserItemAdapter(followers as MutableList<String>)

        recyclerView = getView()!!.findViewById<RecyclerView>(R.id.posts).apply {
            setHasFixedSize(true)

            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

}