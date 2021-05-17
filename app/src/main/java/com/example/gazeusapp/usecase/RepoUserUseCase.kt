package com.example.gazeusapp.usecase

import com.example.gazeusapp.repository.RepoUserRepository
import com.example.gazeuslibrary.models.UserRepos

class RepoUserUseCase(private val repoUserRepository: RepoUserRepository) {

    operator fun invoke(nameUser : String , callBack : (Boolean ,List<UserRepos>? ) -> Unit) {

        repoUserRepository.getRepoUser(nameUser){ success, data ->

            callBack(success, data)
        }
    }
}