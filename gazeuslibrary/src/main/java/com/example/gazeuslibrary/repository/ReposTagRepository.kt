package com.example.gazeuslibrary.repository

import com.example.gazeuslibrary.api.ReposTagApi

class ReposTagRepository (private val tagApi: ReposTagApi) {

    suspend fun getTagRepoOwner(owner: String , repo : String ) = tagApi.getTagRepo(owner, repo)
}