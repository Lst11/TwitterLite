package com.lst11.twitterlite

import com.google.firebase.database.FirebaseDatabase

class UserService(database: FirebaseDatabase) {

    private var repository: UserRepository = UserRepository(database)

    fun addUser(userName: String) {
        repository.addUser(userName)
    }
}
