package com.lst11.twitterlite.user.model

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
class Post(
    var title: String = "",
    var description: String = "",
    var imageUrl: String = ""
) : Serializable {

    private lateinit var authorId: String

}