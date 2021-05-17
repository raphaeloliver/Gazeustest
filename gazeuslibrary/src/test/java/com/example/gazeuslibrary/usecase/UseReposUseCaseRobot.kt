package com.example.gazeuslibrary.usecase

import com.example.gazeuslibrary.models.UserRepos
import com.example.gazeuslibrary.models.UserReposOwner
import com.example.gazeuslibrary.repository.UserReposRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.junit.Assert.assertEquals
import kotlin.coroutines.CoroutineContext

fun withRobot(lb: UseReposUseCaseRobot.() -> Unit): UseReposUseCaseRobot =
    UseReposUseCaseRobot().apply(lb)

class UseReposUseCaseRobot {

    @MockK
    lateinit var mockRepositoryUser: UserReposRepository

    private var dispatcher: CoroutineContext = Dispatchers.Unconfined + SupervisorJob()
    var userUseCase: UseReposUseCase

    var success: Boolean? = null
    private val reposUser = UserRepos(
        id = 0,
        nodeId = "",
        fullName = "",
        name = "",
        private = false,
        owner  = UserReposOwner("Gazeus")
    )

    private val listRepos = listOf(reposUser)


    init {
        MockKAnnotations.init(this)
        userUseCase = UseReposUseCase(mockRepositoryUser, dispatcher)
    }

    class ActionRobot(private val robot: UseReposUseCaseRobot) {

        infix fun check(lb: ResultRobot.() -> Unit) =
            ResultRobot(robot).apply(lb)

        fun getRepositoryNameUse(nameUser: String) {

            robot.userUseCase.getRepositoryUser(nameUser) { success, data ->

                robot.success = success


            }
        }

    }

    class ResultRobot(private val robot: UseReposUseCaseRobot) {

        fun thatGetRepositoryUserNameSuccess() {
            assertEquals(true, robot.success)
        }

        fun thatGetRepositoryUserNameError() {
            assertEquals(false, robot.success)
        }
    }

    infix fun launch(lb: ActionRobot.() -> Unit) =
        ActionRobot(this).apply(lb)

    fun andWhenGetRepositoryReturnsSuccess() {
        coEvery { mockRepositoryUser.getUserRepository(any()) } returns listRepos
    }

    fun andWhenGetRepositoryRetunsError() {
        coEvery { mockRepositoryUser.getUserRepository(any()) }
    }
}