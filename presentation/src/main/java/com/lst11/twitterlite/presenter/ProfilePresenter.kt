package com.lst11.twitterlite.presenter

import android.content.Context
import com.lst11.twitterlite.UserService
import io.reactivex.Observable
import javax.inject.Inject

class ProfilePresenter @Inject constructor(
    private val context: Context,
    private var userService: UserService
) {

    fun uploadUserName(): Observable<String> {
        return userService.getUserName()
    }

    fun uploadUserImageUrl(): Observable<String> {
        return userService.getUserImageLink()
    }

    fun uploadFollowersNumber(): Observable<String> {
        return userService.getFollowersNumber()
    }

    fun uploadFollowingNumber(): Observable<String> {
        return userService.getFollowingNumber()
    }
}
