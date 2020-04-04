package com.lst11.twitterlite.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lst11.twitterlite.R
import com.lst11.twitterlite.app.App
import com.lst11.twitterlite.presenter.PostPresenter
import com.lst11.twitterlite.view.recyclerView.PostItemAdapter
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class FragmentPosts : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    @Inject
    lateinit var presenter: PostPresenter

    init {
        App.appComponent.inject(this)
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

        val posts = presenter.uploadPosts()

        viewManager = LinearLayoutManager(view.context)
        viewAdapter = PostItemAdapter(mutableListOf(), context!!)

        recyclerView = getView()!!.findViewById<RecyclerView>(R.id.posts).apply {
            setHasFixedSize(true)

            layoutManager = viewManager
            adapter = viewAdapter
        }

        subscribeOnPosts()
    }

    private fun subscribeOnPosts() {
        var disposable = presenter.uploadPosts().subscribeBy(
            onNext = {
                Log.e("aaa", "Posts - onNext: $it")

                (viewAdapter as PostItemAdapter).resetItems(it)
                viewAdapter.notifyDataSetChanged()
            },
            onError = {
                Log.e("aaa", "Error")
            })
    }
}