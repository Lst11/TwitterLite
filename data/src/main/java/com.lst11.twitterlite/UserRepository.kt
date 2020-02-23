package com.lst11.twitterlite

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UserRepository(database: FirebaseDatabase) {

    private lateinit var database: FirebaseDatabase
    private var reference: DatabaseReference = database.getReference("message")

    fun addUser(message: String) {
        reference.push().setValue(message)
//        val user = User("Test user")
//        reference.push().setValue(user)
    }
}
