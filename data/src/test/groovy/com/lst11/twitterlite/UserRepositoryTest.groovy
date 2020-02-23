package com.lst11.twitterlite


import spock.lang.Specification

class UserRepositoryTest extends Specification {

    private UserService repository = new UserService()

    def "AddUserTest"() {
        given:
        def success = true

        when:
        repository.addUser("Test one")
        repository.addUser("Test two")
        repository.addUser("Test three")

        then:
        assert success = true
    }
}
