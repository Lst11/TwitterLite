package com.lst11.twitterlite

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.lst11.twitterlite.model.User
import javax.inject.Inject

class UserRepository @Inject constructor(private var database: FirebaseDatabase) {

    private var reference: DatabaseReference = database.getReference("user")

    fun addUser(userName: String) {
        val user = User()
        user.name = userName
        user.followers = listOf(
            User(),
            User()
        )
        user.following = listOf(
            User(),
            User()
        )

        //TODO: save unique key
        reference.push().setValue(user)
    }
}
