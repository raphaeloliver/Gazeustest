package com.example.gazeuslibrary.usecase

import com.example.gazeuslibrary.api.ReposTag
import com.example.gazeuslibrary.remote.SafetyResponse
import com.example.gazeuslibrary.remote.safeRequest
import com.example.gazeuslibrary.repository.ReposTagRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ReposTagUseCase(private val tagRepository: ReposTagRepository,
                      dispatcher: CoroutineContext = Dispatchers.IO + SupervisorJob()
)  {

    private var coroutinesScope = CoroutineScope(dispatcher)

    fun getTagRepoOwner(owner: String,
                        repo : String,
                          callback : (code : Boolean, data : List<ReposTag>?) -> Unit) {

        coroutinesScope.launch {

            when(val response = safeRequest {
                tagRepository.getTagRepoOwner(owner, repo)

            }) {

                is SafetyResponse.Success -> callback(true, response.value)
                is SafetyResponse.GenericError -> callback(false , null)
                is SafetyResponse.NetworkError -> callback(false , null)
            }
        }
    }
}