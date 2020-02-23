package com.lst11.twitterlite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase
import com.lst11.twitterlite.recyclerView.PostItemAdapter


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    //TODO: move to dagger dependency
    private var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var userService: UserService

    init {
        userService = UserService(database)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        viewManager = LinearLayoutManager(this)
        viewAdapter = PostItemAdapter(myDataset)

        recyclerView = findViewById<RecyclerView>(R.id.my_recycler_view).apply {
            setHasFixedSize(true)

            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
}
