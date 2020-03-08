package com.lst11.twitterlite.dagger

import com.lst11.twitterlite.view.fragment.FragmentPosts
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [PresenterModule::class])
interface AppComponent {

    fun inject(fragmentPosts: FragmentPosts)
}