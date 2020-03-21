package com.lst11.data

import android.content.Context
import com.lst11.twitterlite.user.UserRepository
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import javax.inject.Inject


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Inject
    lateinit var repository: UserRepository

    lateinit var instrumentationContext: Context

    @Before
    fun setup() {
//        val component = DaggerTestAppComponent.builder()
//            .applicationModule(TestApplicationModule(GDelegate()))
//            .build()
//        component.into(this)
    }

    @Test
    fun someTest() {
        TODO()
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun addUserTest() {
        val url = "https://twitterlite-11b04.firebaseio.com/"


        repository.addUser("Test one")
        repository.addUser("Test two")
        repository.addUser("Test three")
    }
}
