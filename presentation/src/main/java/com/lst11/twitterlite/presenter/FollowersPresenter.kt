package com.lst11.twitterlite.presenter

import com.lst11.twitterlite.UserService
import com.lst11.twitterlite.user.model.User
import io.reactivex.Observable
import javax.inject.Inject

class FollowersPresenter @Inject constructor(private var userService: UserService) {

    fun uploadFollowers(): Observable<MutableList<User>> {
        return userService.getFollowers()
    }
}

