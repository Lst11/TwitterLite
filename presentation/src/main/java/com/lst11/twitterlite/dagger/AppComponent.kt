package com.lst11.twitterlite.dagger

import com.lst11.twitterlite.view.fragment.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [PresenterModule::class])
interface AppComponent {

    fun inject(fragmentPosts: FragmentPosts)
    fun inject(fragmentPosts: FragmentProfile)
    fun inject(fragmentFollowing: FragmentFollowing)
    fun inject(fragmentFollowing: FragmentFollowers)
    fun inject(fragmentFollowing: FragmentCreatePost)

}