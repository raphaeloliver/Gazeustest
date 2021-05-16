package com.example.gazeuslibrary.remote

import org.koin.core.KoinApplication

class KoinInstance {

    object KoinContext {
        lateinit var koinApplication: KoinApplication
    }
}