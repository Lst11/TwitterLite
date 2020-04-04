package com.lst11.twitterlite.user.model

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
class Post(
    private var title: String = "",
    private var description: String = "",
    private var imageUrl: String = ""
) : Serializable {

    private lateinit var authorId: String

    fun getTitle(): String {
        return title
    }

    fun getDescription(): String {
        return description
    }

    fun getImageUrl(): String {
        return imageUrl
    }
}