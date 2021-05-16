package com.example.gazeuslibrary

import com.example.gazeuslibrary.di.DataModule
import com.example.gazeuslibrary.di.DomainModule
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication

fun Init.startKoin() {
    KoinInstance.KoinContext.koinApplication = koinApplication {
        androidContext(getAppContext())
        val list = listOf(DataModule.ModuleLoad() , DomainModule.Domainload())
        modules(list)
    }
}