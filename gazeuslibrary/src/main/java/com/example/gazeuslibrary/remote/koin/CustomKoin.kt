package com.example.gazeuslibrary.remote.koin

import org.koin.core.Koin
import org.koin.core.KoinComponent

interface CustomKoin : KoinComponent {

    override fun getKoin(): Koin = KoinInstance.KoinContext.koinApplication.koin
}