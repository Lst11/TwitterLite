package com.lst11.twitterlite

import com.google.firebase.database.FirebaseDatabase

class UserService(database: FirebaseDatabase) {

    private var repository: UserRepository = UserRepository(database)

    fun addUser(message: String) {
        repository.addUser(message)

//        val user = User("Test user")
//        reference.push().setValue(user)
    }
}
