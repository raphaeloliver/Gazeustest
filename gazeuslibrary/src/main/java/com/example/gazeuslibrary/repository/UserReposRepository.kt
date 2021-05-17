package com.example.gazeuslibrary.repository

import com.example.gazeuslibrary.api.UserReposApi

class UserReposRepository(private val userApi: UserReposApi) {

    suspend fun getUserRepository(nameUse : String) = userApi.getUserRepo(nameUse)
}