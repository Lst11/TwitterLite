package com.lst11.twitterlite.view.fragment

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.lst11.twitterlite.R
import com.lst11.twitterlite.app.App
import com.lst11.twitterlite.presenter.ProfilePresenter
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_profile.view.*
import javax.inject.Inject


class FragmentProfile : Fragment() {

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

        setUserName(view)
        setUserImage(view)
        setFollowersNumber(view)
        setFollowingNumber(view)
    }

    private fun setUserName(view: View) {
        var userName = presenter.uploadUserName()
        userName.subscribeBy(
            onNext = {
                view.profile_name_text.text = it
            }
        )
    }

    private fun setUserImage(view: View) {
        var imageLink = presenter.uploadUserImageUrl()
        imageLink.subscribeBy(
            onNext = {

                val imageView = view.findViewById<ImageView>(R.id.user_image)
                val progressBar = view.findViewById<ProgressBar>(R.id.progress)

                Glide
                    .with(view.context)
                    .load(it)
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            progressBar.visibility = View.GONE
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            progressBar.visibility = View.GONE
                            return false
                        }
                    })
                    .apply(RequestOptions.circleCropTransform())
                    .into(imageView)
            }
        )
    }

    private fun setFollowersNumber(view: View) {
        var userName = presenter.uploadFollowersNumber()
        userName.subscribeBy(
            onNext = {
                view.followers_number.text = it
            }
        )
    }

    private fun setFollowingNumber(view: View) {
        var userName = presenter.uploadFollowingNumber()
        userName.subscribeBy(
            onNext = {
                view.following_number.text = it
            }
        )
    }
}
