package com.example.gazeuslibrary.repository

import com.example.gazeuslibrary.api.UserReposApi

class UserReposRepository(private val userApi: UserReposApi) {

    suspend fun getRepositoryUser(nameUse : String) = userApi.getUserRepo(nameUse)
}