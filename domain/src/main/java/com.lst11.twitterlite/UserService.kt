package com.lst11.twitterlite

import javax.inject.Inject

class UserService @Inject constructor(private var repository: UserRepository) {

    fun addUser(userName: String) {
        repository.addUser(userName)
    }
}
