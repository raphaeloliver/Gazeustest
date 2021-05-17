package com.example.gazeusapp.repository

import com.example.gazeuslibrary.api.ReposTag
import com.example.gazeuslibrary.usecase.Core


class RepoTagRepository(private val coreProvider: Core) {

    fun getRepoTagName(owner: String ,
                       repo: String,
                        callback: (Boolean, List<ReposTag>?) -> Unit
                        ) = coreProvider.getTagRepo(owner,repo, callback)
}