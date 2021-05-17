package com.example.gazeusapp

import com.example.gazeusapp.repository.RepoUserRepository
import com.example.gazeusapp.usecase.RepoUserUseCase
import com.example.gazeusapp.viewmodel.MainActivityViewModel
import com.example.gazeuslibrary.usecase.Core
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModules {

    private val modules = module {

        single { Core() }
        factory { RepoUserRepository(get()) }
        factory { RepoUserUseCase(get()) }
        viewModel {
            MainActivityViewModel(get())
        }
    }

    fun loadDomainModule() = modules
}