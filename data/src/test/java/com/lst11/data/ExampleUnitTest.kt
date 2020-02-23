package com.lst11.data

import com.lst11.twitterlite.UserService
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private val repository = UserService()

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test

    fun addUserTest() {
        repository.addUser("Test one")
        repository.addUser("Test two")
        repository.addUser("Test three")
    }
}
