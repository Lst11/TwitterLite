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
import com.lst11.twitterlite.presenter.FollowersPresenter
import com.lst11.twitterlite.view.recyclerView.UserItemAdapter
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject


class FragmentFollowers : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    @Inject
    lateinit var presenter: FollowersPresenter

    init {
        App.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_followers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewManager = LinearLayoutManager(view.context)
        viewAdapter = UserItemAdapter(mutableListOf())

        recyclerView = getView()!!.findViewById<RecyclerView>(R.id.posts).apply {
            setHasFixedSize(true)

            layoutManager = viewManager
            adapter = viewAdapter
        }

        subscribeOnFollowers()
    }

    private fun subscribeOnFollowers() {
        var disposable = presenter.uploadFollowers().subscribeBy(
            onNext = {
                Log.e("aaa", "Followers - onNext: $it")

                val result = mutableListOf<String>()
                for (item in it) {
                    result.add(item.name)
                }
                (viewAdapter as UserItemAdapter).resetItems(result)
                viewAdapter.notifyDataSetChanged()
            },
            onError = {
                Log.e("aaa", "Error")
            })
    }

}