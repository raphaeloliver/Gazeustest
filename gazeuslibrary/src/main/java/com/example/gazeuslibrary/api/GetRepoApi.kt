package com.example.gazeuslibrary.api

import com.example.gazeuslibrary.models.UserRepos
import retrofit2.http.GET
import retrofit2.http.Path

interface ReposTagApi {
    @GET("repos/{owner}/{repo}/tags")
    suspend fun getTagRepo(@Path("owner")  owner : String,
                           @Path("repo") repo : String) : List<ReposTag>
}

interface UserReposApi {
    @GET("users/{username}/repos")
    suspend fun getUserRepo(@Path("username") username : String) : List<UserRepos>
}