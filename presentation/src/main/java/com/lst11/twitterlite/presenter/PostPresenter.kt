package com.lst11.twitterlite.presenter

import com.lst11.twitterlite.UserService
import com.lst11.twitterlite.user.model.Post
import io.reactivex.Observable
import javax.inject.Inject

class PostPresenter @Inject constructor(private var userService: UserService) {

    fun uploadPosts(): Observable<MutableList<Post>> {
        return userService.getPosts()
    }
    fun savePost(post: Post) {
        userService.savePost(post)
    }
}
