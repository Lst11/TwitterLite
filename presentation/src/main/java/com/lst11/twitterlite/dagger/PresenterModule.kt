package com.lst11.twitterlite.dagger

import android.content.Context
import com.example.lena.finalapp.executor.UIThread
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.lst11.twitterlite.UserService
import com.lst11.twitterlite.executor.PostExecutorThread
import com.lst11.twitterlite.presenter.FollowersPresenter
import com.lst11.twitterlite.presenter.FollowingPresenter
import com.lst11.twitterlite.presenter.PostPresenter
import com.lst11.twitterlite.presenter.ProfilePresenter
import com.lst11.twitterlite.user.UserRepository
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
    fun provideStorage(): FirebaseStorage = FirebaseStorage.getInstance()

    @Provides
    @Singleton
    fun providePostExecutorThread(): PostExecutorThread = UIThread()

    @Provides
    @Singleton
    fun provideUserRepository(): UserRepository =
        UserRepository(provideDatabase(), provideStorage())

    @Provides
    @Singleton
    fun provideUserService(): UserService =
        UserService(provideUserRepository(), providePostExecutorThread())

    @Provides
    @Singleton
    fun providePostsPresenter(): PostPresenter = PostPresenter(provideUserService())

    @Provides
    @Singleton
    fun provideFollowingPresenter(): FollowingPresenter = FollowingPresenter(provideUserService())

    @Provides
    @Singleton
    fun provideFollowersPresenter(): FollowersPresenter = FollowersPresenter(provideUserService())

    @Provides
    @Singleton
    fun provideProfilePresenter(): ProfilePresenter =
        ProfilePresenter(provideContext(), provideUserService())
}