package com.example.gazeuslibrary.remote.koin

import org.koin.core.KoinApplication

class KoinInstance {

    object KoinContext {
        lateinit var koinApplication: KoinApplication
    }
}