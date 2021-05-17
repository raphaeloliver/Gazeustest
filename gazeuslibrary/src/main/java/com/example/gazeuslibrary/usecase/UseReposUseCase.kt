package com.example.gazeuslibrary.usecase

import com.example.gazeuslibrary.models.UserRepos
import com.example.gazeuslibrary.remote.SafetyResponse
import com.example.gazeuslibrary.remote.safeRequest
import com.example.gazeuslibrary.repository.UserReposRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class UseReposUseCase(private val userRepository: UserReposRepository,
                      dispatcher: CoroutineContext = Dispatchers.IO + SupervisorJob()
                  ) {

    private var coroutinesScope = CoroutineScope(dispatcher)

    fun getRepositoryUser(nameUse : String,
                            callback : (code : Boolean, data : List<UserRepos>?) -> Unit){

        coroutinesScope.launch {
            when(val response = safeRequest {
                userRepository.getUserRepository(nameUse)

            }) {
                is SafetyResponse.Success -> callback(true, response.value)
                is SafetyResponse.NetworkError -> callback(false, null)
                is SafetyResponse.GenericError -> callback(false, null)
            }
        }
    }
}