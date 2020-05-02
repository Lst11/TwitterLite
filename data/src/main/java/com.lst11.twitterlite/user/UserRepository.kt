package com.lst11.twitterlite.user

import android.content.ContentValues
import android.net.Uri
import android.util.Log
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.lst11.twitterlite.user.model.Post
import com.lst11.twitterlite.user.model.User
import io.reactivex.subjects.PublishSubject
import java.util.logging.Logger
import javax.inject.Inject


class UserRepository @Inject constructor(
    database: FirebaseDatabase,
    storage: FirebaseStorage
) {

    private var log: Logger? = Logger.getLogger("User Repository")

    private var reference: DatabaseReference = database.getReference("user")

    private var mStorageRef = FirebaseStorage.getInstance().reference

    fun getFollowing(userId: String): PublishSubject<MutableList<User>> {
        val followingObservable: PublishSubject<MutableList<User>> = PublishSubject.create()

        reference.child(userId).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val followingList = snapshot.child("following").children
                var following = mutableListOf<User>()

                for (followingItem in followingList) {

                    val follower = followingItem.getValue(User::class.java)
                    if (follower != null) {
                        following.add(follower)
                        Log.d(ContentValues.TAG, "Following: uploading data")
                    }
                    followingObservable.onNext(following)

                }
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
        return followingObservable
    }

    fun getFollowers(userId: String): PublishSubject<MutableList<User>> {
        val followersObservable: PublishSubject<MutableList<User>> = PublishSubject.create()

        reference.child(userId).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val followingList = snapshot.child("followers").children
                var following = mutableListOf<User>()

                for (followingItem in followingList) {

                    val follower = followingItem.getValue(User::class.java)
                    if (follower != null) {
                        following.add(follower)
                        Log.d(ContentValues.TAG, "Followers: uploading data")
                    }
                    followersObservable.onNext(following)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
        return followersObservable
    }

    fun getUserPosts(userId: String): PublishSubject<MutableList<Post>> {
        val postsObservable: PublishSubject<MutableList<Post>> = PublishSubject.create()

        reference.child(userId).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val postsList = snapshot.child("posts").children
                var posts = mutableListOf<Post>()

                for (followingItem in postsList) {

                    val post = followingItem.getValue(Post::class.java)
                    if (post != null) {
                        posts.add(post)
                        Log.d(ContentValues.TAG, "Posts: uploading data")
                    }
                }

                postsObservable.onNext(posts.sortedBy { myObject -> myObject.initDate }.toMutableList())
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })

        return postsObservable
    }

    fun savePost(userId: String, post: Post) {
        reference.child(userId).child("posts").push().setValue(post)
    }

    fun saveToStorage(byteArray: ByteArray, imagePath: String): PublishSubject<Uri> {
        val imageObservable: PublishSubject<Uri> = PublishSubject.create()
        val riversRef = mStorageRef.child(imagePath)

        val uploadTask = riversRef.putBytes(byteArray)
        uploadTask.addOnFailureListener {
            Log.e("aaa", " Upload failed ")
        }.addOnSuccessListener { taskSnapshot ->
            Log.e("aaa", " Upload successfull ")
            taskSnapshot.uploadSessionUri?.let { imageObservable.onNext(it) }
        }

        return imageObservable
    }

    fun getFileUrl(imagePath: String): PublishSubject<String> {
        val imageUriObservable: PublishSubject<String> = PublishSubject.create()

        val riversRef = mStorageRef.child(imagePath)

        riversRef.downloadUrl.addOnSuccessListener {
            Log.e("aaa", " download successful $it")
            imageUriObservable.onNext(it.toString())
        }
            .addOnFailureListener {
                Log.e("aaa", " download failed ")

            }
        return imageUriObservable
    }

    fun getUserName(userId: String): PublishSubject<String> {
        val nameObservable: PublishSubject<String> = PublishSubject.create()

        reference.child(userId).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var userName = snapshot.child("name").getValue(String::class.java)

                nameObservable.onNext(userName.toString())
                Log.e("aaa", " username $userName ")

            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
        return nameObservable
    }

    fun getUserImage(userId: String): PublishSubject<String> {
        val urlObservable: PublishSubject<String> = PublishSubject.create()

        reference.child(userId).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var imageUrl = snapshot.child("imageLink").getValue(String::class.java)

                urlObservable.onNext(imageUrl.toString())
                Log.e("aaa", " imageLink $imageUrl ")

            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
        return urlObservable
    }

    fun getFollowersNumber(userId: String): PublishSubject<String> {
        val followersObservable: PublishSubject<String> = PublishSubject.create()

        reference.child(userId).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val followersSize = snapshot.child("followers").childrenCount
                followersObservable.onNext(followersSize.toString())
                Log.e("aaa", " followers size $followersSize")
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
        return followersObservable
    }

    fun getFollowingNumber(userId: String): PublishSubject<String> {
        val followingObservable: PublishSubject<String> = PublishSubject.create()

        reference.child(userId).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val followingsSize = snapshot.child("following").childrenCount
                followingObservable.onNext(followingsSize.toString())
                Log.e("aaa", " Following size $followingsSize")
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
        return followingObservable
    }
}
