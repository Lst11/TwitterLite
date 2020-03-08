package com.lst11.twitterlite.dagger

import android.content.Context
import com.google.firebase.database.FirebaseDatabase
import com.lst11.twitterlite.UserRepository
import com.lst11.twitterlite.UserService
import com.lst11.twitterlite.presenter.PostsPresenter
import com.lst11.twitterlite.presenter.ProfilePresenter
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
    fun provideDatabase(): FirebaseDatabase = FirebaseDatabase.getInstance()

    @Provides
    @Singleton
    fun provideUserRepository(): UserRepository = UserRepository(provideDatabase())

    @Provides
    @Singleton
    fun provideUserService(): UserService = UserService(provideUserRepository())

    @Provides
    @Singleton
    fun providePostsPresenter(): PostsPresenter = PostsPresenter(provideUserService())

    @Provides
    @Singleton
    fun provideProfilePresenter(): ProfilePresenter =
        ProfilePresenter(provideContext(), provideUserService())
}