package com.lst11.twitterlite.dagger

import android.content.Context
import com.lst11.twitterlite.presenter.PostsPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresenterModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context = context

    @Provides
    @Singleton
    fun providePostsPresenter(): PostsPresenter = PostsPresenter()
}