package com.lst11.twitterlite.user.model

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
class User(var name: String = "") : Serializable {
    var id: String = ""
    var imageLink: String = ""
    var status: String = ""
    var posts: List<Post> = emptyList()
    var followers: List<User> = emptyList()
    var following: List<User> = emptyList()

    override fun toString(): String {
        return "User(name='$name', id='$id', imageLink='$imageLink', posts=$posts, followers=$followers, following=$following)"
    }
}