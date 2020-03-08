package com.lst11.twitterlite.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lst11.twitterlite.R
import com.lst11.twitterlite.app.App
import com.lst11.twitterlite.presenter.ProfilePresenter
import com.lst11.twitterlite.view.recyclerView.PostItemAdapter
import javax.inject.Inject


class FragmentProfile : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    @Inject
    lateinit var presenter: ProfilePresenter

    init {
        App.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val posts = presenter.uploadUserPosts()

        viewManager = LinearLayoutManager(view.context)
        viewAdapter = PostItemAdapter(posts)

        recyclerView = getView()!!.findViewById<RecyclerView>(R.id.posts).apply {
            setHasFixedSize(true)

            layoutManager = viewManager
            adapter = viewAdapter
        }

        val radioGroup = getView()?.findViewById<RadioGroup>(R.id.bottom_group)

        radioGroup?.setOnCheckedChangeListener { _, checkedId ->
            val radio = getView()?.findViewById<RadioButton>(checkedId)
            val posts = presenter.buttonClicked(radio?.text as String)
            (viewAdapter as PostItemAdapter).resetItems(posts)
            viewAdapter.notifyDataSetChanged()
        }
    }
}
