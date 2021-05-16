package com.example.gazeuslibrary.di

import androidx.annotation.Keep
import com.example.gazeuslibrary.usecase.ReposTagUseCase
import com.example.gazeuslibrary.usecase.UseReposUseCase
import org.koin.dsl.module

@Keep
object DomainModule {

    private val domain = module{

        factory { UseReposUseCase(get()) }
        factory { ReposTagUseCase(get()) }
    }

    fun Domainload() = domain
}