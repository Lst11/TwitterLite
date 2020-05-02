package com.lst11.twitterlite

import android.net.Uri
import com.lst11.twitterlite.executor.PostExecutorThread
import com.lst11.twitterlite.user.UserRepository
import com.lst11.twitterlite.user.model.Post
import com.lst11.twitterlite.user.model.User
import io.reactivex.Observable
import javax.inject.Inject

class UserService @Inject constructor(
    private var repository: UserRepository,
    postExecutorThread: PostExecutorThread
) : BaseService(postExecutorThread) {

    var userId = "-M2wiAj6RvDYWYGVR_Ng"
    var userImage =
        "\"https://img0.liveinternet.ru/images/attach/c/7/95/100/95100574_4278666_1283476.jpg\""

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

    fun getPosts(): Observable<MutableList<Post>> {
        return repository.getUserPosts(userId)
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }

    fun savePost(post: Post) {
        repository.savePost(userId, post)
    }

    fun uploadFileFromByteArray(bytes: ByteArray, imageId: String): Observable<Uri> {
        var imagePath = userId + imageId

        return repository.saveToStorage(bytes, imagePath)
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }

    fun getImageUrl(imageId: String): Observable<String> {
        var imagePath = userId + imageId

        return repository.getFileUrl(imagePath)
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }

    fun getUserName(): Observable<String> {
        return repository.getUserName(userId)
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }

    fun getUserImageLink(): Observable<String> {
        return repository.getUserImage(userId)
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }

    fun getFollowersNumber(): Observable<String> {
        return repository.getFollowersNumber(userId)
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }

    fun getFollowingNumber(): Observable<String> {
        return repository.getFollowingNumber(userId)
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }
}
