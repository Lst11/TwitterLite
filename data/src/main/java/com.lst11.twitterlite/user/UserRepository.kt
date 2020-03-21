package com.lst11.twitterlite.user

import android.content.ContentValues
import android.util.Log
import com.google.firebase.database.*
import com.lst11.twitterlite.user.model.User
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.util.logging.Logger
import javax.inject.Inject


class UserRepository @Inject constructor(private var database: FirebaseDatabase) {

    private var log: Logger? = Logger.getLogger("User Repository")

    private var reference: DatabaseReference = database.getReference("user")

//    private var reference: DatabaseReference = database.reference

    fun addUser(userName: String) {
        val user = User()
        user.name = userName
        user.followers = listOf(
            User("Follower #100"),
            User("Follower #101")
        )
        user.following = listOf(
            User("Following #200"),
            User("Following #200")
        )

//        //TODO: save unique key
        reference.push().setValue(user)
    }

    fun getPosts(userId: String) {

        val bool = "true"
    }

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

    fun getUser(userId: String): Observable<User> {
        val userObservable: PublishSubject<User> = PublishSubject.create()
        reference.child(userId).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val user: User = snapshot.getValue(User::class.java)!!
                userObservable.onNext(user)
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })

        return userObservable
    }
}
