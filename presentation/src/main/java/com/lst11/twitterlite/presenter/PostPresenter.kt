package com.lst11.twitterlite.presenter

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import com.lst11.twitterlite.UserService
import com.lst11.twitterlite.user.model.Post
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class PostPresenter @Inject constructor(private var userService: UserService) {

    fun downloadPosts(): Observable<MutableList<Post>> {
        return userService.getPosts()
    }

    fun savePost(post: Post, bitmapImage: Bitmap?) {
        uploadImage(bitmapImage).subscribeBy(
            onNext = {
                Log.e("aaa", " Success! Url is: $it")

                val imageUrl = getImageUrl(bitmapImage)
                imageUrl.subscribeBy(
                    onNext = { imageLink ->
                        post.setImageUrl(imageLink)
                        userService.savePost(post)
                    })
            }
        )
    }

    private fun uploadImage(bitmapImage: Bitmap?): Observable<Uri> {
        Log.e("aaa", " Converting file ")
        val bao = ByteArrayOutputStream()
        bitmapImage?.compress(Bitmap.CompressFormat.PNG, 100, bao)

        val byteArray = bao.toByteArray()

        return userService.uploadFileFromByteArray(byteArray, bitmapImage.toString())
    }

    fun getImageUrl(bitmapImage: Bitmap?): Observable<String> {
        return userService.getImageUrl(bitmapImage.toString())
    }
}
