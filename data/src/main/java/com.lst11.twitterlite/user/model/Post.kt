package com.lst11.twitterlite.user.model

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
class Post(
    var title: String = "",
    var description: String = "",
    var authorImage: String = ""
) : Serializable {

    private var imageUrl: String = ""

    private lateinit var authorId: String

    fun setImageUrl(imageUrl: String) {
        this.imageUrl = imageUrl
    }

    fun setAuthorId(imageUrl: String) {
        this.authorId = authorId
    }

    fun getImageUrl(): String {
        return imageUrl
    }
}