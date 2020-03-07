package com.lst11.twitterlite.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase
import com.lst11.twitterlite.R
import com.lst11.twitterlite.UserService
import com.lst11.twitterlite.view.recyclerView.PostItemAdapter


class FragmentPosts : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    //TODO: move to dagger dependency
    private var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var userService: UserService

    init {
        userService = UserService(database)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userService.addUser("Test one")
        userService.addUser("Test two")
        userService.addUser("Test three")

        val myDataset = listOf(
            "First Item",
            "Second Item",
            "Third Item",
            "Forth Item",
            "Fifth Item",
            "The end"
        )

        viewManager = LinearLayoutManager(view.context)
        viewAdapter = PostItemAdapter(myDataset)

        recyclerView = getView()!!.findViewById<RecyclerView>(R.id.posts).apply {
            setHasFixedSize(true)

            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

}