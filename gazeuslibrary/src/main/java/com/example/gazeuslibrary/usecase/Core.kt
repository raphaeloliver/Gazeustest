package com.example.gazeuslibrary.usecase

import androidx.annotation.Keep
import com.example.gazeuslibrary.remote.koin.CustomKoin
import com.example.gazeuslibrary.Init
import com.example.gazeuslibrary.api.ReposTag
import com.example.gazeuslibrary.models.UserRepos
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