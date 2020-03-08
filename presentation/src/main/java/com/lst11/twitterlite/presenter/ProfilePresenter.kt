package com.lst11.twitterlite.presenter

import android.content.Context
import com.lst11.twitterlite.R
import com.lst11.twitterlite.UserService
import javax.inject.Inject

class ProfilePresenter @Inject constructor(
    private val context: Context,
    private var userService: UserService
) {

    fun buttonClicked(buttonName: String): List<String> {
        when (buttonName) {
            context.resources.getString(R.string.user_twits_button) -> {
                return uploadUserPosts()
            }

            context.resources.getString(R.string.following_twits_button) -> {
                return uploadFollowingPosts()
            }
            context.resources.getString(R.string.followers_twits_button) -> {
                return uploadFollowersPosts()
            }
            else -> {
                return emptyList()
            }
        }
    }

    fun uploadUserPosts(): MutableList<String> {
        return listOf(
            "User #1",
            "User #2",
            "User #3",
            "User #4",
            "User #5",
            "User #6",
            "User #7"
        ).toMutableList()
    }

    private fun uploadFollowersPosts(): MutableList<String> {
        return listOf(
            "Follower #1",
            "Follower #2",
            "Follower #3",
            "Follower #4"
        ).toMutableList()
    }

    private fun uploadFollowingPosts(): MutableList<String> {
        return listOf(
            "Following #1",
            "Following #2",
            "Following #3",
            "Following #4"
        ).toMutableList()
    }
}

