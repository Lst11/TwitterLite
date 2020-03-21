package com.lst11.twitterlite

import com.lst11.twitterlite.executor.PostExecutorThread
import com.lst11.twitterlite.user.UserRepository
import com.lst11.twitterlite.user.model.User
import io.reactivex.Observable
import javax.inject.Inject

class UserService @Inject constructor(
    private var repository: UserRepository,
    postExecutorThread: PostExecutorThread
) : BaseService(postExecutorThread) {

    var userId = "-M2wiAj6RvDYWYGVR_Ng"

    fun getUser(userName: String): Observable<User> {
        return repository.getUser(userId)
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }

    fun getFollowing(): Observable<MutableList<User>> {
        return repository.getFollowing(userId)
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }

    fun getFollowers(): Observable<MutableList<User>> {
        return repository.getFollowers(userId)
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }
}
