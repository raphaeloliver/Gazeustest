package com.example.gazeusapp.repository

import com.example.gazeuslibrary.models.UserRepos
import com.example.gazeuslibrary.usecase.Core


class RepoUserRepository(private val coreProvider: Core) {

    fun getRepoUser(nameUser: String,
                    callback: (Boolean, List<UserRepos>?) -> Unit
                        ) = coreProvider.getUserNameRepo(nameUser, callback)
}