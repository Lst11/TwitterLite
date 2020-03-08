package com.lst11.twitterlite.presenter

import com.google.firebase.database.FirebaseDatabase
import com.lst11.twitterlite.UserService
import javax.inject.Inject

class PostsPresenter @Inject constructor() {

    //TODO: move to dagger dependency
    private var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var userService: UserService


    init {
        userService = UserService(database)
    }

    fun uploadPosts(): List<String> {
        userService.addUser("Test one")
        userService.addUser("Test two")
        userService.addUser("Test three")

        return listOf(
            "First Item",
            "Second Item",
            "Third Item",
            "Forth Item",
            "Fifth Item",
            "The end"
        )
    }
}

