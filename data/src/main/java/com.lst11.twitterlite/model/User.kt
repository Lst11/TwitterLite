package com.lst11.twitterlite.model

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
class User : Serializable {
    var name: String = ""
    var id: String = ""
    var imageLink: String = ""
    // var posts: List<Post> = emptyList()
    var followers: List<User> = emptyList()
    var following: List<User> = emptyList()
}