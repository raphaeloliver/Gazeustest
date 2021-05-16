package com.example.gazeuslibrary.di

import androidx.annotation.Keep
import com.example.gazeuslibrary.api.ReposTagApi
import com.example.gazeuslibrary.api.UserReposApi
import com.example.gazeuslibrary.remote.RetrofitService
import com.example.gazeuslibrary.repository.ReposTagRepository
import com.example.gazeuslibrary.repository.UserReposRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

@Keep
object DataModule {

    private val data = module {

        factory {
            RetrofitService(androidApplication())
        }

        factory { UserReposRepository(get()) }
        factory { ReposTagRepository(get()) }
        factory { get<RetrofitService>().create(UserReposApi::class.java) }
        factory { get<RetrofitService>().create(ReposTagApi::class.java) }
    }

    fun ModuleLoad() = data
}