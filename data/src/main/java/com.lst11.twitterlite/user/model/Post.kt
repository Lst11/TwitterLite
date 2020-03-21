package com.lst11.twitterlite.user.model

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
class Post(private val authorId: Long) : Serializable {
    private lateinit var description: String
}