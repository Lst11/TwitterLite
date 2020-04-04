package com.lst11.twitterlite.presenter

import com.lst11.twitterlite.UserService
import com.lst11.twitterlite.user.model.Post
import javax.inject.Inject

class PostPresenter @Inject constructor(private var userService: UserService) {

    fun savePost(post: Post) {
        userService.savePost(post)
    }

    fun uploadPosts(): MutableList<String> {
//        userService.getUser("Test one")

        return listOf(
            "First Item",
            "Second Item",
            "Third Item",
            "Forth Item",
            "Fifth Item",
            "The end"
        ).toMutableList()
    }
}

