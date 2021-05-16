package com.example.gazeuslibrary

import org.koin.core.Koin
import org.koin.core.KoinComponent

interface CustomKoin : KoinComponent {

    override fun getKoin(): Koin = KoinInstance.KoinContext.koinApplication.koin
}