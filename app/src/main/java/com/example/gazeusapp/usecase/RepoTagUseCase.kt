package com.example.gazeusapp.usecase

import com.example.gazeusapp.repository.RepoTagRepository
import com.example.gazeuslibrary.api.ReposTag

class RepoTagUseCase(private val repoTagRepository: RepoTagRepository) {

    operator fun invoke(owner : String ,repo : String,  callBack : (Boolean ,List<ReposTag>? ) -> Unit) {

        repoTagRepository.getRepoTagName(owner, repo){success, owner ->

            callBack(success, owner)
        }
    }
}