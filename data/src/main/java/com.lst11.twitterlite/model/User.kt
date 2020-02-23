package com.lst11.twitterlite.model

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
class User(private var name: String) : Serializable {
    private var id: Long = 0
    private var imageLink: String = ""
    private var posts: List<Post> = emptyList()
    private var followers: List<User> = emptyList()
    private var following: List<User> = emptyList()

    constructor () : this("")
}