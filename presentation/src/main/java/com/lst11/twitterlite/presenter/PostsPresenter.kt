package com.lst11.twitterlite.presenter

import com.lst11.twitterlite.UserService
import javax.inject.Inject

class PostsPresenter @Inject constructor(private var userService: UserService) {

    fun uploadPosts(): MutableList<String> {
        userService.addUser("Test one")

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

