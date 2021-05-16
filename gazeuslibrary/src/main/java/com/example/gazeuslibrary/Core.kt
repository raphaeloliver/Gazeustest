package com.example.gazeuslibrary

import androidx.annotation.Keep
import com.example.gazeuslibrary.api.ReposTag
import com.example.gazeuslibrary.api.UserRepos
import com.example.gazeuslibrary.usecase.ReposTagUseCase
import com.example.gazeuslibrary.usecase.UseReposUseCase
import org.koin.android.ext.android.inject

@Keep
class Core : Init(), CustomKoin {

    private val userUseCase by inject<UseReposUseCase>()
    private val tagUseCase by inject<ReposTagUseCase>()

    fun getUserNameRepo(
        nameUser: String,
        callBack: (success: Boolean, data: List<UserRepos>?) -> Unit
    ) = userUseCase.getRepositoryUser(nameUser, callBack)

    fun getTagRepo(owner: String,
                   repo : String,
                   callBack: (success: Boolean, data: List<ReposTag>?) -> Unit
    ) = tagUseCase.getTagRepoOwner(owner,repo, callBack)

}