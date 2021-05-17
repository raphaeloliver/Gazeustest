package com.example.gazeuslibrary.usecase

import org.junit.Test

class UseReposUseCaseTest {

    private val UserName = "Gazeus"

    @Test
    fun `get repository name success`() {

        withRobot {
            andWhenGetRepositoryReturnsSuccess()
        } launch {
            getRepositoryNameUse(UserName)
        }check {
            thatGetRepositoryUserNameSuccess()
        }
    }

    @Test
    fun `get repository name error`() {

        withRobot {
            andWhenGetRepositoryRetunsError()
        } launch {
            getRepositoryNameUse(UserName)
        }check {
            thatGetRepositoryUserNameError()
        }
    }
}